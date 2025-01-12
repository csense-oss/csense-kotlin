package org.csenseoss.kotlin.extensions.collections.map

import org.csenseoss.kotlin.classes.map.*
import org.csenseoss.kotlin.extensions.collections.map.mutable.*
import org.csenseoss.kotlin.tests.assertions.*
import org.csenseoss.kotlin.tests.assertions.collections.iterable.*
import org.csenseoss.kotlin.tests.assertions.collections.map.*
import org.csenseoss.kotlin.tests.assertions.comparable.*
import org.csenseoss.kotlin.tests.assertions.general.*
import kotlin.test.*


class MutableMapKtTest {

    @Test
    fun removeAll() {
        val map: MutableMap<String, String> = mutableMapOf<String, String>()
        //empty case, nothing should happen here.
        map.removeAll { true }
        map.removeAll { false }
        map.assertEmpty()

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
        map.assertEmpty()
    }

    @Test
    fun setIfNotEmpty() {
        val map = mutableMapOf<String, List<String>>()
        map.setIfNotEmpty("a", listOf())
        map.assertEmpty()

        map.setIfNotEmpty("a", listOf("a"))
        map.assertSize(1)
        map["a"].assertSize(1)

        map.setIfNotEmpty("a", listOf("a", "b"))
        map.assertSize(1)
        map["a"].assertSize(2)

    }

    @Test
    fun putSubList() {
        val map = mutableMapOf<String, MutableList<String>>()
        map.putSubList("a", "a")
        map.assertSize(1)
        map["a"].assertSize(1)

        map.putSubList("a", "b")
        map.assertSize(1)
        map["a"].assertSize(2)

        map.putSubList("b", "a")
        map.assertSize(2)
        map["a"].assertSize(2)
        map["b"].assertSize(1)

    }

    class MutableMapKVPutIfMissing {
        @Test
        fun empty() {
            mutableMapOf<String, String>().apply {
                putIfMissing("key", "value")
                assert("key" to "value")
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
                assert("key" to "value")
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
        fun empty() = assertCalled { shouldBeCalled: () -> Unit ->
            val map = mutableMapOf<String, String>()
            map.putIfMissingAnd("key", "value") { key, value ->
                this.assertByEquals(map)
                key.assert("key")
                value.assert("value")
                shouldBeCalled()
            }
            map.assert("key" to "value")
        }


        @Test
        fun singleNotThere() = assertCalled { shouldBeCalled: () -> Unit ->
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
            map.assert("key" to "value")
        }


        @Test
        fun multipleNotThere() = assertCalled { shouldBeCalled: () -> Unit ->
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

    class ReverseKeyValue {

        @Test
        fun empty() {
            val empty = mutableMapOf<String, Int>().reverseKeyValue()
            empty.assertEmpty()
        }


        @Test
        fun single() {
            val single = mutableMapOf("abc" to 42).reverseKeyValue()
            single.assertIs<MutableMap<Int, String>>()
            single.assert(42 to "abc")
        }


        @Test
        fun multiple() {
            val multiple: Map<Int, String> = mutableMapOf("abc" to 42, "1234" to 500).reverseKeyValue()
            multiple.assert(42 to "abc", 500 to "1234")
        }

    }

    class MutableMapKeyValueRemove {
        class MutableMapKeyValueRemove {

            @Test
            fun emptyNull() {
                mutableMapOf<String, String>().apply {
                    remove(null).assertNull()
                    assertEmpty()
                }
            }

            @Test
            fun emptyValue() {
                mutableMapOf<String, String>().apply {
                    remove("abc").assertNull()
                    assertEmpty()
                }
            }


            @Test
            fun singleNull() {
                mutableMapOf("a" to "1").apply {
                    remove(null).assertNull()
                    assertSize(1)
                    assertContains("a" to "1")
                }
            }

            @Test
            fun singleNotFound() {
                mutableMapOf("a" to "1").apply {
                    remove("abc").assertNull()
                    assertSize(1)
                    assertContains("a" to "1")
                }
            }


            @Test
            fun singleFound() {
                mutableMapOf("a" to "1").apply {
                    remove("a").assert("1")
                    assertEmpty()
                }
            }


            @Test
            fun multipleNull() {
                mutableMapOf("a" to "1", "b" to "2").apply {
                    remove(null).assertNull()
                    assertSize(2)
                    assertContains("a" to "1")
                    assertContains("b" to "2")
                }
            }

            @Test
            fun multipleNotFound() {
                mutableMapOf("a" to "1", "b" to "2").apply {
                    remove("q").assertNull()
                    assertSize(2)
                    assertContains("a" to "1")
                    assertContains("b" to "2")
                }
            }

            @Test
            fun multipleFound() {
                mutableMapOf("a" to "1", "b" to "2").apply {
                    remove("b").assert("2")
                    assert("a" to "1")
                }
            }

        }

    }

    class MutableMapKeyValuePut {
        @Test
        fun empty() {
            val map: MutableMap<String, String> = mutableMapOf<String, String>()
            map.put(MapEntry("key", "value")).assertNull()
            map.assert("key" to "value")
        }


        @Test
        fun singleNonColliding() {
            val map: MutableMap<String, String> = mutableMapOf(
                "k1" to "v1"
            )
            map.put(MapEntry("key", "value")).assertNull()
            map.assert("k1" to "v1", "key" to "value")
        }


        @Test
        fun singleColliding() {
            val map = mutableMapOf<String, String>(
                "key" to "v1"
            )
            map.put(MapEntry("key", "v2")).assert("v1")
            map.assert("key" to "v2")
        }


        @Test
        fun multipleNoColliding() {
            val map = mutableMapOf<String, String>(
                "k1" to "v1",
                "k2" to "v2"
            )
            map.put(MapEntry("key", "value")).assertNull()
            map.assert("k1" to "v1", "k2" to "v2", "key" to "value")
        }

        @Test
        fun multipleSomeColliding() {
            val map = mutableMapOf<String, String>(
                "key" to "v1",
                "1234" to "abc",
                "zxc" to "qwerty"
            )
            map.put(MapEntry("key", "v2")).assert("v1")
            map.put(MapEntry("zxc", "wuub")).assert("qwerty")

            map.assert("key" to "v2", "1234" to "abc", "zxc" to "wuub")
        }
    }
}