package csense.kotlin.extensions.collections

import csense.kotlin.extensions.primitives.forEach
import csense.kotlin.test.assertions.assertFalse
import csense.kotlin.test.assertions.assertNotNullAndEquals
import csense.kotlin.test.assertions.assertNull
import csense.kotlin.test.assertions.assertTrue
import kotlin.test.Test

class CollectionTest {

    @Test
    fun isIndexValid() {
        val collection: MutableCollection<String> = mutableListOf()
        collection.isIndexValid(0).assertFalse()
        collection.isIndexValid(-1).assertFalse()
        collection.add("test")
        collection.isIndexValid(0).assertTrue()
        collection.isIndexValid(-1).assertFalse()

        10.forEach {
            collection.add("")
        }
        for (i in 0 until 11) {
            collection.isIndexValid(i).assertTrue()
        }
        collection.isIndexValid(12).assertFalse()

    }

    @Test
    fun isIndexValidForInsert() {
        val collection: MutableCollection<String> = mutableListOf()
        collection.isIndexValidForInsert(0).assertTrue()
        collection.isIndexValidForInsert(-1).assertFalse()
        collection.add("test")
        collection.isIndexValidForInsert(0).assertTrue()
        collection.isIndexValidForInsert(1).assertTrue()
        collection.isIndexValidForInsert(2).assertFalse()
        collection.isIndexValidForInsert(12).assertFalse()
    }

    @Test
    fun getSafe() {
        val collection: MutableCollection<String> = mutableListOf()
        collection.getSafe(-1).assertNull()
        collection.getSafe(0).assertNull("collection is empty.")
        collection.getSafe(1).assertNull()
        collection.add("test")
        collection.getSafe(-1).assertNull()
        collection.getSafe(0).assertNotNullAndEquals("test")
        collection.getSafe(1).assertNull()
        collection.add("1")
        collection.add("2")
        collection.add("3")
        collection.getSafe(3).assertNotNullAndEquals("3")
        collection.getSafe(4).assertNull()
        collection.getSafe(2).assertNotNullAndEquals("2")
        collection.getSafe(1).assertNotNullAndEquals("1")


    }


}