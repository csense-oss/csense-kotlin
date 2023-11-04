@file:Suppress("unused")

package csense.kotlin.extensions.collections.generic.collectionBounds.operations

import csense.kotlin.extensions.collections.generic.collectionBounds.*
import csense.kotlin.tests.assertions.*
import kotlin.test.*

class OutOfBoundsTest {

    class CollectionBoundsOutOfBoundsEndOutOfBounds {

        @Test
        fun alwaysOutOfBounds() {
            CollectionBounds.CollectionBoundsAlwaysOutOfBounds.outOfBoundsEndOutOfBounds(-1).assertTrue()
            CollectionBounds.CollectionBoundsAlwaysOutOfBounds.outOfBoundsEndOutOfBounds(0).assertTrue()
            CollectionBounds.CollectionBoundsAlwaysOutOfBounds.outOfBoundsEndOutOfBounds(1).assertTrue()
        }

        @Test
        fun zeroBoundsChecker() {
            CollectionBounds.CollectionBoundsZeroBoundsChecker.outOfBoundsEndOutOfBounds(-1).assertTrue()
            CollectionBounds.CollectionBoundsZeroBoundsChecker.outOfBoundsEndOutOfBounds(0).assertTrue()
            CollectionBounds.CollectionBoundsZeroBoundsChecker.outOfBoundsEndOutOfBounds(1).assertTrue()
        }

        @Test
        fun boundsChecker() {
            CollectionBounds.CollectionBoundsChecker(collectionLength = 1).outOfBoundsEndOutOfBounds(-1).assertTrue()
            CollectionBounds.CollectionBoundsChecker(collectionLength = 1).outOfBoundsEndOutOfBounds(0).assertFalse()
            CollectionBounds.CollectionBoundsChecker(collectionLength = 1).outOfBoundsEndOutOfBounds(1).assertTrue()
        }

    }

    class CollectionBoundsOutOfBoundsEndInBounds {

        @Test
        fun alwaysOutOfBounds() {
            CollectionBounds.CollectionBoundsAlwaysOutOfBounds.outOfBoundsEndInBounds(-1).assertTrue()
            CollectionBounds.CollectionBoundsAlwaysOutOfBounds.outOfBoundsEndInBounds(0).assertTrue()
            CollectionBounds.CollectionBoundsAlwaysOutOfBounds.outOfBoundsEndInBounds(1).assertTrue()
        }

        @Test
        fun zeroBoundsChecker() {
            CollectionBounds.CollectionBoundsZeroBoundsChecker.outOfBoundsEndInBounds(-1).assertTrue()
            CollectionBounds.CollectionBoundsZeroBoundsChecker.outOfBoundsEndInBounds(0).assertFalse()
            CollectionBounds.CollectionBoundsZeroBoundsChecker.outOfBoundsEndInBounds(1).assertTrue()
        }

        @Test
        fun boundsChecker() {
            CollectionBounds.CollectionBoundsChecker(collectionLength = 1).outOfBoundsEndInBounds(-1).assertTrue()
            CollectionBounds.CollectionBoundsChecker(collectionLength = 1).outOfBoundsEndInBounds(0).assertFalse()
            CollectionBounds.CollectionBoundsChecker(collectionLength = 1).outOfBoundsEndInBounds(1).assertFalse()
            CollectionBounds.CollectionBoundsChecker(collectionLength = 1).outOfBoundsEndInBounds(2).assertTrue()
        }


    }


    class CollectionBoundsOutOfBounds {

        @Test
        fun endOutOfBounds() {
            CollectionBounds.CollectionBoundsAlwaysOutOfBounds.outOfBounds(index = 0, isEndOutOfBonds = true).assertTrue()

            CollectionBounds.CollectionBoundsZeroBoundsChecker.outOfBounds(index = -1, isEndOutOfBonds = true).assertTrue()
            CollectionBounds.CollectionBoundsZeroBoundsChecker.outOfBounds(index = 0, isEndOutOfBonds = true).assertTrue()
            CollectionBounds.CollectionBoundsZeroBoundsChecker.outOfBounds(index = 1, isEndOutOfBonds = true).assertTrue()

            CollectionBounds.CollectionBoundsChecker(collectionLength = 1).outOfBounds(index = -1, isEndOutOfBonds = true).assertTrue()
            CollectionBounds.CollectionBoundsChecker(collectionLength = 1).outOfBounds(index = 0, isEndOutOfBonds = true).assertFalse()
            CollectionBounds.CollectionBoundsChecker(collectionLength = 1).outOfBounds(index = 1, isEndOutOfBonds = true).assertTrue()
            CollectionBounds.CollectionBoundsChecker(collectionLength = 1).outOfBounds(index = 2, isEndOutOfBonds = true).assertTrue()
        }

        @Test
        fun endInBounds() {
            CollectionBounds.CollectionBoundsAlwaysOutOfBounds.outOfBounds(index = 0, isEndOutOfBonds = false).assertTrue()

            CollectionBounds.CollectionBoundsZeroBoundsChecker.outOfBounds(index = -1, isEndOutOfBonds = false).assertTrue()
            CollectionBounds.CollectionBoundsZeroBoundsChecker.outOfBounds(index = 0, isEndOutOfBonds = false).assertFalse()
            CollectionBounds.CollectionBoundsZeroBoundsChecker.outOfBounds(index = 1, isEndOutOfBonds = false).assertTrue()

            CollectionBounds.CollectionBoundsChecker(collectionLength = 1).outOfBounds(index = -1, isEndOutOfBonds = false).assertTrue()
            CollectionBounds.CollectionBoundsChecker(collectionLength = 1).outOfBounds(index = 0, isEndOutOfBonds = false).assertFalse()
            CollectionBounds.CollectionBoundsChecker(collectionLength = 1).outOfBounds(index = 1, isEndOutOfBonds = false).assertFalse()
            CollectionBounds.CollectionBoundsChecker(collectionLength = 1).outOfBounds(index = 2, isEndOutOfBonds = false).assertTrue()
        }
    }
}