package csense.kotlin.specificExtensions.collections.collection

import csense.kotlin.tests.assertions.*
import kotlin.test.*

class CollectionMappingsTest {
    @Test
    fun collectionItemMappings() {
        val first = listOf("a").mappings
        val second = listOf("b").mappings
        (first != second).assertTrue(message = "should have different mapping(s)")
    }

    class CollectionMappingsItemMapEachItemWith {

        @Test
        fun empty() {
            val empty: CollectionMappings<String> = listOf<String>().mappings
            val result = empty.mapEachItemWith(42) { shouldNotBeCalled() }
            result.assert(42)
        }

        @Test
        fun single() = assertCalled { shouldBeCalled ->
            val empty: CollectionMappings<String> = listOf("test").mappings
            val result = empty.mapEachItemWith("result") {
                it.assert("test")
                shouldBeCalled()
            }
            result.assert("result")
        }

        @Test
        fun multiple() = assertCalled(times = 2) { shouldBeCalled ->
            val empty: CollectionMappings<String> = listOf("test", "1234").mappings
            val result = empty.mapEachItemWith(mutableListOf<String>()) {
                shouldBeCalled()
                this += it
            }
            result.assertSize(2)
            result.assertContainsInOrder("test", "1234")
        }

    }
}