package csense.kotlin.specificExtensions.collections.collection

import csense.kotlin.tests.assertions.*
import kotlin.test.*

class CollectionMappingsTest {
    @Test
    fun collectionItemMappings(){
        val first: Collection<String> = listOf()
        val second: Collection<String> = listOf()
        (first.mappings != second.mappings).assertTrue(message = "should have different mapping(s)")
    }

    class CollectionMappingsItemForEachItemWith {

        @Test
        fun append() {
            TODO()
        }

    }
}