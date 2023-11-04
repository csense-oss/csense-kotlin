package csense.kotlin.extensions.collections.generic.collectionBounds

import csense.kotlin.extensions.collections.generic.collectionBounds.operations.*
import csense.kotlin.tests.assertions.*
import kotlin.test.*

class CollectionBoundsTest {

    class CollectionBoundsAlwaysOutOfBoundsTest {

        @Test
        fun inBoundsEndNotInBounds() {
            CollectionBounds.CollectionBoundsAlwaysOutOfBounds.inBoundsEndNotInBounds((-1)).assertFalse()
            CollectionBounds.CollectionBoundsAlwaysOutOfBounds.inBoundsEndNotInBounds(0).assertFalse()
            CollectionBounds.CollectionBoundsAlwaysOutOfBounds.inBoundsEndNotInBounds(1).assertFalse()
        }

        @Test
        fun inBoundsEndInBounds() {
            CollectionBounds.CollectionBoundsAlwaysOutOfBounds.inBoundsEndInBounds((-1)).assertFalse()
            CollectionBounds.CollectionBoundsAlwaysOutOfBounds.inBoundsEndInBounds((0)).assertFalse()
            CollectionBounds.CollectionBoundsAlwaysOutOfBounds.inBoundsEndInBounds((1)).assertFalse()
        }

    }

    class CollectionBoundsZeroBoundsCheckerTest {
        @Test
        fun inBoundsEndNotInBounds() {
            CollectionBounds.CollectionBoundsZeroBoundsChecker.inBoundsEndNotInBounds((-1)).assertFalse()
            CollectionBounds.CollectionBoundsZeroBoundsChecker.inBoundsEndNotInBounds(0).assertFalse()
            CollectionBounds.CollectionBoundsZeroBoundsChecker.inBoundsEndNotInBounds(1).assertFalse()
        }

        @Test
        fun inBoundsEndInBounds() {
            CollectionBounds.CollectionBoundsZeroBoundsChecker.inBoundsEndInBounds((-1)).assertFalse()
            CollectionBounds.CollectionBoundsZeroBoundsChecker.inBoundsEndInBounds(0).assertTrue()
            CollectionBounds.CollectionBoundsZeroBoundsChecker.inBoundsEndInBounds(1).assertFalse()
        }
    }

    class CollectionBoundsCheckerTestTest {
        @Test
        fun inBoundsEndNotInBounds() {
            CollectionBounds.CollectionBoundsChecker(collectionLength = 1).inBoundsEndNotInBounds((-1)).assertFalse()
            CollectionBounds.CollectionBoundsChecker(collectionLength = 1).inBoundsEndNotInBounds(0).assertTrue()
            CollectionBounds.CollectionBoundsChecker(collectionLength = 1).inBoundsEndNotInBounds(1).assertFalse()
            CollectionBounds.CollectionBoundsChecker(collectionLength = 1).inBoundsEndNotInBounds((-50)).assertFalse()
            CollectionBounds.CollectionBoundsChecker(collectionLength = 1).inBoundsEndNotInBounds(42).assertFalse()
        }

        @Test
        fun inBoundsEndInBounds() {
            CollectionBounds.CollectionBoundsChecker(collectionLength = 1).inBoundsEndInBounds((-1)).assertFalse()
            CollectionBounds.CollectionBoundsChecker(collectionLength = 1).inBoundsEndInBounds(0).assertTrue()
            CollectionBounds.CollectionBoundsChecker(collectionLength = 1).inBoundsEndInBounds(1).assertTrue()
            CollectionBounds.CollectionBoundsChecker(collectionLength = 1).inBoundsEndInBounds((-50)).assertFalse()
            CollectionBounds.CollectionBoundsChecker(collectionLength = 1).inBoundsEndInBounds(42).assertFalse()
        }
    }

    @Test
    fun collectionBounds() {
        CollectionBounds(collectionLength = -1).assertIs<CollectionBounds.CollectionBoundsAlwaysOutOfBounds>()
        CollectionBounds(collectionLength = 0).assertIs<CollectionBounds.CollectionBoundsZeroBoundsChecker>()
        CollectionBounds(collectionLength = 1).assertIs<CollectionBounds.CollectionBoundsChecker>()
    }

    @Test
    fun companionFrom() {
        CollectionBounds(collectionLength = -1).assertIs<CollectionBounds.CollectionBoundsAlwaysOutOfBounds>()
        CollectionBounds(collectionLength = 0).assertIs<CollectionBounds.CollectionBoundsZeroBoundsChecker>()
        CollectionBounds(collectionLength = 1).assertIs<CollectionBounds.CollectionBoundsChecker>()
    }


}
