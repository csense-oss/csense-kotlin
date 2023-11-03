package csense.kotlin.extensions.collections.generic.collection.operations

import csense.kotlin.extensions.collections.generic.collection.*
import csense.kotlin.tests.assertions.*
import kotlin.test.*

class IndexOfFirstOrNullTest {

    @Test
    fun badRange() {
        GenericCollections.indexOfFirstOrNull(
            startIndex = 10,
            length = 0,
            getElement = { failTest("") },
            predicate = { failTest("") }
        ).assertNull()
    }

    @Test
    fun emptyRange() {
        GenericCollections.indexOfFirstOrNull(
            startIndex = 0,
            length = 0,
            getElement = { failTest("") },
            predicate = { failTest("") }
        ).assertNull()
    }
}

