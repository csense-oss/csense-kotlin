package csense.kotlin.extensions.collections.map

import csense.kotlin.tests.assertions.*
import kotlin.test.*


class MutableMapKtTest {

    @Test
    fun removeAll() {
        val map = mutableMapOf<String, String>()
        //empty case, nothing should happen here.
        map.removeAll { true }
        map.removeAll { false }
        map.assertSize(0)

        map["a"] = "b"
        map.removeAll { false }
        map.assertSize(1, "should not have removed any elements when no predicates matches")

        map.removeAll { it.key == "a" }
        map.assertSize(0, "should have matching predicate and thus remove element")

        map.putAll(
            listOf(
                "a" to "b",
                "c" to "d",
                "1" to "2",
                "3" to "4"
            )
        )

        map.removeAll { false }
        map.assertSize(4, "should not have removed any elements when no predicates matches")

        map.removeAll { it.value == "4" }
        map.assertSize(3)
        map.removeAll { it.key == "1" }
        map.assertSize(2)
        map.removeAll { true }
        map.assertSize(0)
    }

    @Test
    fun setIfNotEmpty() {
        val map = mutableMapOf<String, List<String>>()
        map.setIfNotEmpty("a", listOf())
        map.assertEmpty()

        map.setIfNotEmpty("a", listOf("a"))
        map.assertSize(1)
        map["a"].assertNotNullApply { assertSize(1) }

        map.setIfNotEmpty("a", listOf("a", "b"))
        map.assertSize(1)
        map["a"].assertNotNullApply { assertSize(2) }

    }

    @Test
    fun putSubList() {
        val map = mutableMapOf<String, MutableList<String>>()
        map.putSubList("a", "a")
        map.assertSize(1)
        map["a"].assertNotNullApply { assertSize(1) }

        map.putSubList("a", "b")
        map.assertSize(1)
        map["a"].assertNotNullApply { assertSize(2) }

        map.putSubList("b", "a")
        map.assertSize(2)
        map["a"].assertNotNullApply { assertSize(2) }
        map["b"].assertNotNullApply { assertSize(1) }

    }

    class MutableMapKVPutIfMissing {
        @Test
        fun empty() {
            mutableMapOf<String, String>().apply {
                putIfMissing("key", "value")
                assertSingle("key" to "value")
            }
        }


        @Test
        fun singleNotThere() {
            mutableMapOf<String, String>("key" to "val").apply {
                putIfMissing("key2", "value")
                assertSize(2)
                assertContains("key" to "val")
                assertContains("key2" to "value")
            }
        }


        @Test
        fun singleThere() {
            mutableMapOf<String, String>(
                "key" to "value"
            ).apply {
                putIfMissing("key", "value2")
                assertSingle("key" to "value")
            }
        }


        @Test
        fun multipleNotThere() {
            mutableMapOf<String, String>(
                "key" to "value",
                "x" to "y"
            ).apply {
                putIfMissing("z", "1")
                assertSize(3)
                assertContains("key" to "value")
                assertContains("x" to "y")
                assertContains("z" to "1")
            }
        }

        @Test
        fun multipleThere() {
            mutableMapOf<String, String>(
                "key" to "value",
                "x" to "y"
            ).apply {
                putIfMissing("x", "1")
                assertSize(2)
                assertContains("key" to "value")
                assertContains("x" to "y")
            }
        }
    }

    class MutableMapKVPutIfMissingAnd {
        @Test
        fun empty() = assertCalled { shouldBeCalled ->
            val map = mutableMapOf<String, String>()
            map.putIfMissingAnd("key", "value") { key, value ->
                this.assertAs(map)
                key.assert("key")
                value.assert("value")
                shouldBeCalled()
            }
            map.assertSingle("key" to "value")
        }


        @Test
        fun singleNotThere() = assertCalled { shouldBeCalled ->
            val map = mutableMapOf<String, String>("key" to "value")
            map.putIfMissingAnd("key2", "value2") { key, value ->
                key.assert("key2")
                value.assert("value2")
                shouldBeCalled()
            }
            map.assertSize(2)
            map.assertContains("key" to "value")
            map.assertContains("key2" to "value2")
        }

        @Test
        fun singleThere() {
            val map = mutableMapOf<String, String>("key" to "value")
            map.putIfMissingAnd("key", "value2") { _, _ ->
                shouldNotBeCalled()
            }
            map.assertSingle("key" to "value")
        }


        @Test
        fun multipleNotThere() = assertCalled { shouldBeCalled ->
            val map = mutableMapOf<String, String>(
                "key" to "value",
                "key2" to "value2"
            )
            map.putIfMissingAnd("key3", "value3") { key, value ->
                key.assert("key3")
                value.assert("value3")
                shouldBeCalled()
            }
            map.assertSize(3)
            map.assertContains("key" to "value")
            map.assertContains("key2" to "value2")
            map.assertContains("key3" to "value3")
        }

        @Test
        fun multipleThere() {
            val map = mutableMapOf<String, String>(
                "key" to "value",
                "key2" to "value2"
            )
            map.putIfMissingAnd("key2", "value3") { _, _ ->
                shouldNotBeCalled()
            }
            map.assertSize(2)
            map.assertContains("key" to "value")
            map.assertContains("key2" to "value2")
        }
    }
}