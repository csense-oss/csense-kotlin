@file:Suppress("MemberVisibilityCanBePrivate")

package csense.kotlin.extensions.collections.map

import csense.kotlin.classes.*
import csense.kotlin.extensions.*
import csense.kotlin.tests.assertions.*
import kotlin.test.*


class MapKtTest {

    val emptyMap = mapOf<String, String>()
    val singleMap = mapOf("a" to "b")

    @Test
    fun forEachIndexed() {
        emptyMap.forEachIndexed { _, _: Int ->
            failTest("should not be called on empty")
        }
        singleMap.forEachIndexed { entry: Map.Entry<String, String>, index: Int ->
            entry.key.assert("a")
            entry.value.assert("b")
            index.assert(0)
        }
    }

    @Test
    fun filterMapKey() {
        emptyMap.forEachIndexed { _, _ ->
            failTest("should not be called on empty")
        }
        singleMap.forEachIndexed { entry: Map.Entry<String, String>, index: Int ->
            entry.key.assert("a")
            entry.value.assert("b")
            index.assert(0)
        }
    }

    @Test
    fun useValueOr() {
        var notFoundCounter = 0
        emptyMap.useValueOr("notThere", { failTest("Should not get called") }, { notFoundCounter += 1 })
        notFoundCounter.assert(1)
        var foundCounter = 0
        singleMap.useValueOr("a", { foundCounter += 1 }, { failTest("should not get called") })
        foundCounter.assert(1)
        notFoundCounter = 0
        singleMap.useValueOr("b", { failTest() }, { notFoundCounter += 1 })
        notFoundCounter.assert(1)
    }

    class MapKVDoesNotContainKey {
        @Test
        fun empty() {
            val map = mapOf<String, String>()
            map.doesNotContainKey("1").assertTrue()
        }

        @Test
        fun single() {
            val map = mapOf("1234" to "")
            map.doesNotContainKey("1").assertTrue()
            map.doesNotContainKey("1234").assertFalse()
            map.doesNotContainKey("12345").assertTrue()
        }

        @Test
        fun multiple() {
            val map = mapOf(123 to "", 444 to "")
            map.doesNotContainKey(1).assertTrue()
            map.doesNotContainKey(2).assertTrue()
            map.doesNotContainKey(123).assertFalse()
            map.doesNotContainKey(444).assertFalse()
            map.doesNotContainKey(555).assertTrue()
        }

    }

    class MapOrgKeyOrgValueToMapMapEntry {
        @Test
        fun empty() {
            mapOf<String, String>().toMapViaMapEntry<String, String, String, String>(mapEntry = {
                shouldNotBeCalled()
            })
        }

        @Test
        fun single() {
            val toAssert = MapEntry("1234", "abc")
            val mapped = mapOf("test" to "1234").toMapViaMapEntry {
                toAssert
            }
            mapped.assertSingle {
                it.key.assert(toAssert.key)
                it.value.assert(toAssert.value)
            }
        }

        @Test
        fun multiple() {
            val mapped = mapOf("a" to 1, "b" to 2).toMapViaMapEntry {
                MapEntry(it.key, it.value + 10)
            }
            mapped.assertSize(2)
            mapped.containsKey("a").assertTrue()
            mapped.containsKey("b").assertTrue()

            mapped["a"].assertNotNullAndEquals(11)
            mapped["b"].assertNotNullAndEquals(12)
        }
    }

    class MapOrgKeyOrgValueToMapMapEntryToPair {
        @Test
        fun empty() {
            mapOf<String, String>().toMapViaKeyValuePair<String, String, String, String>(mapEntryToPair = {
                shouldNotBeCalled()
            })
        }

        @Test
        fun single() {
            val toAssert = "12345" to "abc"
            val mapped = mapOf("test" to "1234").toMapViaKeyValuePair {
                toAssert
            }
            mapped.assertSingle(toAssert)
        }

        @Test
        fun multiple() {
            val mapped = mapOf("a" to 1, "b" to 2).toMapViaKeyValuePair {
                it.key to it.value + 20
            }
            mapped.assertSize(2)
            mapped.containsKey("a").assertTrue()
            mapped.containsKey("b").assertTrue()

            mapped["a"].assertNotNullAndEquals(21)
            mapped["b"].assertNotNullAndEquals(22)
        }
    }

    class MapOrgKeyOrgValueToMapViaMapEntry {
        @Test
        fun empty() {
            mapOf<Int, Int>().toMapViaMapEntry<Int, Int, Int, Int> { shouldNotBeCalled() }
        }

        @Test
        fun single() {
            mapOf(0 to 1).toMapViaMapEntry {
                MapEntry(it.key, it.value)
            }.assertSingle {
                it.key.assert(0)
                it.value.assert(1)
            }
            mapOf(0 to 1).toMapViaMapEntry {
                MapEntry(10, 20)
            }.assertSingle {
                it.key.assert(10)
                it.value.assert(20)
            }

        }

        @Test
        fun multipleDirect() {
            val result = mapOf(0 to 1, 10 to 20).toMapViaMapEntry {
                MapEntry(it.key, it.value)
            }

            result.assertSize(2)
            result.assertContainsKeyAnd(0) { value ->
                value.assert(1)
            }
            result.assertContainsKeyAnd(10) { value ->
                value.assert(20)
            }
        }

        @Test
        fun multipleSameKey() {
            val result = mapOf(0 to 1, 10 to 20).toMapViaMapEntry {
                MapEntry(0, 30)
            }

            result.assertSingle {
                it.key.assert(0)
                it.value.assert(30)
            }
        }

    }

    class MapOrgKeyOrgValueToMapViaKeyValuePair {
        @Test
        fun empty() {
            mapOf<Int, Int>().toMapViaKeyValuePair<Int, Int, Int, Int> { shouldNotBeCalled() }
        }

        @Test
        fun single() {
            mapOf(0 to 1).toMapViaKeyValuePair {
                it.key to it.value
            }.assertSingle {
                it.key.assert(0)
                it.value.assert(1)
            }
            mapOf(0 to 1).toMapViaKeyValuePair {
                10 to 20
            }.assertSingle {
                it.key.assert(10)
                it.value.assert(20)
            }

        }

        @Test
        fun multipleSameKey() {
            val result = mapOf(0 to 1, 10 to 20).toMapViaKeyValuePair {
                0 to 10
            }

            result.assertSingle {
                it.key.assert(0)
                it.value.assert(10)
            }
        }
    }

    class MapKVForeachBackwards {

        @Test
        fun empty() {
            mapOf<String, String>().foreachBackwards { shouldNotBeCalled() }
        }

        @Test
        fun single() = assertCalled { shouldBeCalled ->
            mapOf("test" to "1234").foreachBackwards {
                it.key.assert("test")
                it.value.assert("1234")
                shouldBeCalled()
            }
        }

        @Test
        fun multiple() = assertCalled(times = 2) { shouldBeCalled ->
            var haveCalled = false
            mapOf("first" to 0, "last" to 1).foreachBackwards {
                it.key.assert(haveCalled.map("first", "last"))
                it.value.assert(haveCalled.map(0, 1))
                haveCalled = true
                shouldBeCalled()
            }
        }
    }
}

