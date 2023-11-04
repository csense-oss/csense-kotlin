@file:Suppress("unused")

package csense.kotlin.extensions.collections.generic.collectionBounds.operations

import csense.kotlin.extensions.collections.generic.collectionBounds.*
import csense.kotlin.tests.assertions.*
import kotlin.test.*

class InBoundsTest {


    class CollectionBoundsInBounds {

        @Test
        fun endInBounds() {
            CollectionBounds.CollectionBoundsAlwaysOutOfBounds.inBounds(index = 0, isEndInBounds = true).assertFalse()

            CollectionBounds.CollectionBoundsZeroBoundsChecker.inBounds(index = -1, isEndInBounds = true).assertFalse()
            CollectionBounds.CollectionBoundsZeroBoundsChecker.inBounds(index = 0, isEndInBounds = true).assertTrue()
            CollectionBounds.CollectionBoundsZeroBoundsChecker.inBounds(index = 1, isEndInBounds = true).assertFalse()

            CollectionBounds.CollectionBoundsChecker(collectionLength = 1).inBounds(index = -1, isEndInBounds = true).assertFalse()
            CollectionBounds.CollectionBoundsChecker(collectionLength = 1).inBounds(index = 0, isEndInBounds = true).assertTrue()
            CollectionBounds.CollectionBoundsChecker(collectionLength = 1).inBounds(index = 1, isEndInBounds = true).assertTrue()
            CollectionBounds.CollectionBoundsChecker(collectionLength = 1).inBounds(index = 2, isEndInBounds = true).assertFalse()
        }

        @Test
        fun endOutOfBounds() {
            CollectionBounds.CollectionBoundsAlwaysOutOfBounds.inBounds(index = 0, isEndInBounds = false).assertFalse()

            CollectionBounds.CollectionBoundsZeroBoundsChecker.inBounds(index = -1, isEndInBounds = false).assertFalse()
            CollectionBounds.CollectionBoundsZeroBoundsChecker.inBounds(index = 0, isEndInBounds = false).assertFalse()
            CollectionBounds.CollectionBoundsZeroBoundsChecker.inBounds(index = 1, isEndInBounds = false).assertFalse()

            CollectionBounds.CollectionBoundsChecker(collectionLength = 1).inBounds(index = -1, isEndInBounds = false).assertFalse()
            CollectionBounds.CollectionBoundsChecker(collectionLength = 1).inBounds(index = 0, isEndInBounds = false).assertTrue()
            CollectionBounds.CollectionBoundsChecker(collectionLength = 1).inBounds(index = 1, isEndInBounds = false).assertFalse()
            CollectionBounds.CollectionBoundsChecker(collectionLength = 1).inBounds(index = 2, isEndInBounds = false).assertFalse()
        }
    }
}