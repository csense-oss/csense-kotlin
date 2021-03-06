@file:Suppress("MemberVisibilityCanBePrivate")

package csense.kotlin.extensions.collections.map

import csense.kotlin.classes.*
import csense.kotlin.tests.assertions.*
import kotlin.test.Test


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
            mapped.assertSingle {//TODO update with newer test version
                it.key.assert(toAssert.first)
                it.value.assert(toAssert.second)
            }
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
}

