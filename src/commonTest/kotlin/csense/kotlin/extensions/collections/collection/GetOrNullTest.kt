package csense.kotlin.extensions.collections.collection

import csense.kotlin.tests.assertions.*
import kotlin.test.*

class CollectionItemGetOrNull {
    @Test
    fun getOrNullEmpty() {
        val collection: MutableCollection<String> = mutableListOf()
        collection.getOrNull(-1).assertNull()
        collection.getOrNull(0).assertNull("collection is empty.")
        collection.getOrNull(1).assertNull()
    }

    @Test
    fun getOrNullSingle() {
        val collection: MutableCollection<String> = mutableListOf()
        collection.add("test")
        collection.getOrNull(-1).assertNull()
        collection.getOrNull(0).assert("test")
        collection.getOrNull(1).assertNull()
    }

    @Test
    fun getOrNullMultiple() {
        val collection: MutableCollection<String> = mutableListOf()
        collection.add("test")
        collection.add("1")
        collection.add("2")
        collection.add("3")
        collection.getOrNull(3).assert("3")
        collection.getOrNull(4).assertNull()
        collection.getOrNull(2).assert("2")
        collection.getOrNull(1).assert("1")
    }

}
