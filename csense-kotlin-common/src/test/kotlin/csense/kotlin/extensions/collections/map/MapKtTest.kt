package csense.kotlin.extensions.collections.map

import csense.kotlin.test.assertions.*
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
}