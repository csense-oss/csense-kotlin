package csense.kotlin.extensions.collections.generic.collection.operations

import csense.kotlin.extensions.collections.generic.collection.*
import csense.kotlin.tests.assertions.*
import kotlin.test.*

class IndexOfLastOrNullTest {

    @Test
    fun badRange() {
        GenericCollections.indexOfLastOrNull(
            startFromEndIndex = 10,
            length = 0,
            getElement = { failTest("") },
            predicate = { failTest("") }
        ).assertNull()
    }

    @Test
    fun emptyRange() {
        GenericCollections.indexOfLastOrNull(
            startFromEndIndex = 0,
            length = 0,
            getElement = { failTest("") },
            predicate = { failTest("") }
        ).assertNull()
    }
}

