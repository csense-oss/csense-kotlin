@file:Suppress("MemberVisibilityCanBePrivate")

package csense.kotlin.extensions.collections.map

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
}

