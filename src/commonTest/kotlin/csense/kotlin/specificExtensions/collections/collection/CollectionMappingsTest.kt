package csense.kotlin.specificExtensions.collections.collection

import csense.kotlin.tests.assertions.*
import kotlin.test.*

class CollectionMappingsTest {
    @Test
    fun collectionItemMappings() {
        val first: CollectionMappings<String> = listOf("a").mappings
        val second: CollectionMappings<String> = listOf("b").mappings
        (first != second).assertTrue(message = "should have different mapping(s)")
    }

    class CollectionMappingsItemMapEachItemWith {

        @Test
        fun empty() {
            val empty: CollectionMappings<String> = listOf<String>().mappings
            val result: Int = empty.mapEachItemWith(42) { shouldNotBeCalled() }
            result.assert(42)
        }

        @Test
        fun single(): Unit = assertCalled { shouldBeCalled: () -> Unit ->
            val empty: CollectionMappings<String> = listOf("test").mappings
            val result: String = empty.mapEachItemWith("result") {
                it.assert("test")
                shouldBeCalled()
            }
            result.assert("result")
        }

        @Test
        fun multiple(): Unit = assertCalled(times = 2) { shouldBeCalled: () -> Unit ->
            val empty: CollectionMappings<String> = listOf("test", "1234").mappings
            val result: MutableList<String> = empty.mapEachItemWith(mutableListOf()) { it: String ->
                shouldBeCalled()
                this += it
            }
            result.assertSize(2)
            result.assertContainsInOrder("test", "1234")
        }

    }
}