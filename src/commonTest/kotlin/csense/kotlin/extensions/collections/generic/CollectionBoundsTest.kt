package csense.kotlin.extensions.collections.generic

import csense.kotlin.tests.assertions.*
import kotlin.test.*

class CollectionBoundsTest {
    @Test
    fun collectionBounds() {
        CollectionBounds(collectionLength = -1).assertIs<CollectionBoundsAlwaysOutOfBounds>()
        CollectionBounds(collectionLength = 0).assertIs<CollectionBoundsZeroBoundsChecker>()
        CollectionBounds(collectionLength = 1).assertIs<CollectionBoundsChecker>()
    }

    @Test
    fun companionFrom() {
        CollectionBounds(collectionLength = -1).assertIs<CollectionBoundsAlwaysOutOfBounds>()
        CollectionBounds(collectionLength = 0).assertIs<CollectionBoundsZeroBoundsChecker>()
        CollectionBounds(collectionLength = 1).assertIs<CollectionBoundsChecker>()
    }

    class CollectionBoundsOutOfBoundsEndOutOfBounds {

        @Test
        fun alwaysOutOfBounds() {
            CollectionBoundsAlwaysOutOfBounds.outOfBoundsEndOutOfBounds(-1).assertTrue()
            CollectionBoundsAlwaysOutOfBounds.outOfBoundsEndOutOfBounds(0).assertTrue()
            CollectionBoundsAlwaysOutOfBounds.outOfBoundsEndOutOfBounds(1).assertTrue()
        }

        @Test
        fun zeroBoundsChecker() {
            CollectionBoundsZeroBoundsChecker.outOfBoundsEndOutOfBounds(-1).assertTrue()
            CollectionBoundsZeroBoundsChecker.outOfBoundsEndOutOfBounds(0).assertTrue()
            CollectionBoundsZeroBoundsChecker.outOfBoundsEndOutOfBounds(1).assertTrue()
        }

        @Test
        fun boundsChecker() {
            CollectionBoundsChecker(collectionLength = 1).outOfBoundsEndOutOfBounds(-1).assertTrue()
            CollectionBoundsChecker(collectionLength = 1).outOfBoundsEndOutOfBounds(0).assertFalse()
            CollectionBoundsChecker(collectionLength = 1).outOfBoundsEndOutOfBounds(1).assertTrue()
        }

    }

    class CollectionBoundsOutOfBoundsEndInBounds {

        @Test
        fun alwaysOutOfBounds() {
            CollectionBoundsAlwaysOutOfBounds.outOfBoundsEndInBounds(-1).assertTrue()
            CollectionBoundsAlwaysOutOfBounds.outOfBoundsEndInBounds(0).assertTrue()
            CollectionBoundsAlwaysOutOfBounds.outOfBoundsEndInBounds(1).assertTrue()
        }

        @Test
        fun zeroBoundsChecker() {
            CollectionBoundsZeroBoundsChecker.outOfBoundsEndInBounds(-1).assertTrue()
            CollectionBoundsZeroBoundsChecker.outOfBoundsEndInBounds(0).assertFalse()
            CollectionBoundsZeroBoundsChecker.outOfBoundsEndInBounds(1).assertTrue()
        }

        @Test
        fun boundsChecker() {
            CollectionBoundsChecker(collectionLength = 1).outOfBoundsEndInBounds(-1).assertTrue()
            CollectionBoundsChecker(collectionLength = 1).outOfBoundsEndInBounds(0).assertFalse()
            CollectionBoundsChecker(collectionLength = 1).outOfBoundsEndInBounds(1).assertFalse()
            CollectionBoundsChecker(collectionLength = 1).outOfBoundsEndInBounds(2).assertTrue()
        }


    }

    class CollectionBoundsInBounds {

        @Test
        fun endInBounds() {
            CollectionBoundsAlwaysOutOfBounds.inBounds(index = 0, isEndInBounds = true).assertFalse()

            CollectionBoundsZeroBoundsChecker.inBounds(index = -1, isEndInBounds = true).assertFalse()
            CollectionBoundsZeroBoundsChecker.inBounds(index = 0, isEndInBounds = true).assertTrue()
            CollectionBoundsZeroBoundsChecker.inBounds(index = 1, isEndInBounds = true).assertFalse()

            CollectionBoundsChecker(collectionLength = 1).inBounds(index = -1, isEndInBounds = true).assertFalse()
            CollectionBoundsChecker(collectionLength = 1).inBounds(index = 0, isEndInBounds = true).assertTrue()
            CollectionBoundsChecker(collectionLength = 1).inBounds(index = 1, isEndInBounds = true).assertTrue()
            CollectionBoundsChecker(collectionLength = 1).inBounds(index = 2, isEndInBounds = true).assertFalse()
        }

        @Test
        fun endOutOfBounds() {
            CollectionBoundsAlwaysOutOfBounds.inBounds(index = 0, isEndInBounds = false).assertFalse()

            CollectionBoundsZeroBoundsChecker.inBounds(index = -1, isEndInBounds = false).assertFalse()
            CollectionBoundsZeroBoundsChecker.inBounds(index = 0, isEndInBounds = false).assertFalse()
            CollectionBoundsZeroBoundsChecker.inBounds(index = 1, isEndInBounds = false).assertFalse()

            CollectionBoundsChecker(collectionLength = 1).inBounds(index = -1, isEndInBounds = false).assertFalse()
            CollectionBoundsChecker(collectionLength = 1).inBounds(index = 0, isEndInBounds = false).assertTrue()
            CollectionBoundsChecker(collectionLength = 1).inBounds(index = 1, isEndInBounds = false).assertFalse()
            CollectionBoundsChecker(collectionLength = 1).inBounds(index = 2, isEndInBounds = false).assertFalse()
        }
    }

    class CollectionBoundsOutOfBounds {

        @Test
        fun endOutOfBounds() {
            CollectionBoundsAlwaysOutOfBounds.outOfBounds(index = 0, isEndOutOfBonds = true).assertTrue()

            CollectionBoundsZeroBoundsChecker.outOfBounds(index = -1, isEndOutOfBonds = true).assertTrue()
            CollectionBoundsZeroBoundsChecker.outOfBounds(index = 0, isEndOutOfBonds = true).assertTrue()
            CollectionBoundsZeroBoundsChecker.outOfBounds(index = 1, isEndOutOfBonds = true).assertTrue()

            CollectionBoundsChecker(collectionLength = 1).outOfBounds(index = -1, isEndOutOfBonds = true).assertTrue()
            CollectionBoundsChecker(collectionLength = 1).outOfBounds(index = 0, isEndOutOfBonds = true).assertFalse()
            CollectionBoundsChecker(collectionLength = 1).outOfBounds(index = 1, isEndOutOfBonds = true).assertTrue()
            CollectionBoundsChecker(collectionLength = 1).outOfBounds(index = 2, isEndOutOfBonds = true).assertTrue()
        }

        @Test
        fun endInBounds() {
            CollectionBoundsAlwaysOutOfBounds.outOfBounds(index = 0, isEndOutOfBonds = false).assertTrue()

            CollectionBoundsZeroBoundsChecker.outOfBounds(index = -1, isEndOutOfBonds = false).assertTrue()
            CollectionBoundsZeroBoundsChecker.outOfBounds(index = 0, isEndOutOfBonds = false).assertFalse()
            CollectionBoundsZeroBoundsChecker.outOfBounds(index = 1, isEndOutOfBonds = false).assertTrue()

            CollectionBoundsChecker(collectionLength = 1).outOfBounds(index = -1, isEndOutOfBonds = false).assertTrue()
            CollectionBoundsChecker(collectionLength = 1).outOfBounds(index = 0, isEndOutOfBonds = false).assertFalse()
            CollectionBoundsChecker(collectionLength = 1).outOfBounds(index = 1, isEndOutOfBonds = false).assertFalse()
            CollectionBoundsChecker(collectionLength = 1).outOfBounds(index = 2, isEndOutOfBonds = false).assertTrue()
        }
    }

    class CollectionIsIndex {
        @Test
        fun empty() {
            listOf<String>().isIndex.assertIs<CollectionBoundsZeroBoundsChecker>()
        }

        @Test
        fun single() {
            listOf("").isIndex.assertIsApply<CollectionBoundsChecker> {
                collectionLength.assert(1)
            }
        }

        @Test
        fun multiple() {
            listOf("a", "b").isIndex.assertIsApply<CollectionBoundsChecker> {
                collectionLength.assert(2)
            }
        }
    }

    class CharSequenceIsIndex {
        @Test
        fun empty() {
            "".isIndex.assertIs<CollectionBoundsZeroBoundsChecker>()
        }

        @Test
        fun single() {
            "a".isIndex.assertIsApply<CollectionBoundsChecker> {
                collectionLength.assert(1)
            }
        }

        @Test
        fun multiple() {
            "ab".isIndex.assertIsApply<CollectionBoundsChecker> {
                collectionLength.assert(2)
            }
        }
    }

    class MapIsIndex {
        @Test
        fun empty() {
            mapOf<String, String>().isIndex.assertIs<CollectionBoundsZeroBoundsChecker>()
        }

        @Test
        fun single() {
            mapOf("1" to "a").isIndex.assertIsApply<CollectionBoundsChecker> {
                collectionLength.assert(1)
            }
        }

        @Test
        fun multiple() {
            mapOf("1" to "a", "2" to "b").isIndex.assertIsApply<CollectionBoundsChecker> {
                collectionLength.assert(2)
            }
        }
    }

    class ArrayIsIndex {
        @Test
        fun empty() {
            arrayOf<String>().isIndex.assertIs<CollectionBoundsZeroBoundsChecker>()
        }

        @Test
        fun single() {
            arrayOf("asd").isIndex.assertIsApply<CollectionBoundsChecker> {
                collectionLength.assert(1)
            }
        }

        @Test
        fun multiple() {
            arrayOf("asd", "123").isIndex.assertIsApply<CollectionBoundsChecker> {
                collectionLength.assert(2)
            }
        }
    }

    @Test
    fun booleanArrayIsIndex() {
        booleanArrayOf().isIndex.assertIs<CollectionBoundsZeroBoundsChecker>()
        booleanArrayOf(false).isIndex.assertIsApply<CollectionBoundsChecker> {
            collectionLength.assert(1)
        }
        booleanArrayOf(true, false).isIndex.assertIsApply<CollectionBoundsChecker> {
            collectionLength.assert(2)
        }
    }

    @Test
    fun byteArrayIsIndex() {
        byteArrayOf().isIndex.assertIs<CollectionBoundsZeroBoundsChecker>()
        byteArrayOf(0).isIndex.assertIsApply<CollectionBoundsChecker> {
            collectionLength.assert(1)
        }
        byteArrayOf(0, 1).isIndex.assertIsApply<CollectionBoundsChecker> {
            collectionLength.assert(2)
        }
    }

    @Test
    fun charArrayIsIndex() {
        charArrayOf().isIndex.assertIs<CollectionBoundsZeroBoundsChecker>()
        charArrayOf('0').isIndex.assertIsApply<CollectionBoundsChecker> {
            collectionLength.assert(1)
        }
        charArrayOf('0', '1').isIndex.assertIsApply<CollectionBoundsChecker> {
            collectionLength.assert(2)
        }
    }

    @Test
    fun doubleArrayIsIndex() {
        doubleArrayOf().isIndex.assertIs<CollectionBoundsZeroBoundsChecker>()
        doubleArrayOf(0.0).isIndex.assertIsApply<CollectionBoundsChecker> {
            collectionLength.assert(1)
        }
        doubleArrayOf(0.0, 0.0).isIndex.assertIsApply<CollectionBoundsChecker> {
            collectionLength.assert(2)
        }
    }

    @Test
    fun floatArrayIsIndex() {
        floatArrayOf().isIndex.assertIs<CollectionBoundsZeroBoundsChecker>()
        floatArrayOf(0.0f).isIndex.assertIsApply<CollectionBoundsChecker> {
            collectionLength.assert(1)
        }
        floatArrayOf(0.0f, 0.0f).isIndex.assertIsApply<CollectionBoundsChecker> {
            collectionLength.assert(2)
        }
    }

    @Test
    fun intArrayIsIndex() {
        intArrayOf().isIndex.assertIs<CollectionBoundsZeroBoundsChecker>()
        intArrayOf(0).isIndex.assertIsApply<CollectionBoundsChecker> {
            collectionLength.assert(1)
        }
        intArrayOf(0, 0).isIndex.assertIsApply<CollectionBoundsChecker> {
            collectionLength.assert(2)
        }
    }

    @Test
    fun longArrayIsIndex() {
        longArrayOf().isIndex.assertIs<CollectionBoundsZeroBoundsChecker>()
        longArrayOf(0).isIndex.assertIsApply<CollectionBoundsChecker> {
            collectionLength.assert(1)
        }
        longArrayOf(0, 0).isIndex.assertIsApply<CollectionBoundsChecker> {
            collectionLength.assert(2)
        }
    }

    @Test
    fun shortArrayIsIndex() {
        shortArrayOf().isIndex.assertIs<CollectionBoundsZeroBoundsChecker>()
        shortArrayOf(0).isIndex.assertIsApply<CollectionBoundsChecker> {
            collectionLength.assert(1)
        }
        shortArrayOf(0, 0).isIndex.assertIsApply<CollectionBoundsChecker> {
            collectionLength.assert(2)
        }
    }

    @Test
    fun collectionBoundsAtStart() {

        intArrayOf().isIndex.atStart(0).assertTrue()

        intArrayOf().isIndex.atStart(1).assertFalse()
        intArrayOf().isIndex.atStart(-1).assertFalse()

        intArrayOf(1).isIndex.atStart(0).assertTrue()
        intArrayOf(1).isIndex.atStart(-1).assertFalse()
        intArrayOf(1).isIndex.atStart(1).assertFalse()
        intArrayOf(1).isIndex.atStart(2).assertFalse()
    }

    @Test
    fun collectionBoundsAtLast() {
        intArrayOf().isIndex.atLast(-1).assertFalse()
        intArrayOf().isIndex.atLast(0).assertFalse()
        intArrayOf().isIndex.atLast(1).assertFalse()

        intArrayOf(1).isIndex.atLast(-1).assertFalse()
        intArrayOf(1).isIndex.atLast(0).assertTrue()
        intArrayOf(1).isIndex.atLast(1).assertFalse()
        
        intArrayOf(1,2,3,4).isIndex.atLast(-1).assertFalse()
        intArrayOf(1,2,3,4).isIndex.atLast(0).assertFalse()
        intArrayOf(1,2,3,4).isIndex.atLast(1).assertFalse()
        intArrayOf(1,2,3,4).isIndex.atLast(2).assertFalse()
        intArrayOf(1,2,3,4).isIndex.atLast(3).assertTrue()
        intArrayOf(1,2,3,4).isIndex.atLast(4).assertFalse()
        
    }

    @Test
    fun collectionBoundsAtAnyEnds() {

        intArrayOf().isIndex.atAnyEnds(-1).assertFalse()
        intArrayOf().isIndex.atAnyEnds(0).assertTrue()
        intArrayOf().isIndex.atAnyEnds(1).assertFalse()

        intArrayOf(1).isIndex.atAnyEnds(-1).assertFalse()
        intArrayOf(1).isIndex.atAnyEnds(0).assertTrue()
        intArrayOf(1).isIndex.atAnyEnds(1).assertFalse()
        
        intArrayOf(1,2,3,4).isIndex.atAnyEnds(-1).assertFalse()
        intArrayOf(1,2,3,4).isIndex.atAnyEnds(0).assertTrue()
        intArrayOf(1,2,3,4).isIndex.atAnyEnds(1).assertFalse()
        intArrayOf(1,2,3,4).isIndex.atAnyEnds(2).assertFalse()
        intArrayOf(1,2,3,4).isIndex.atAnyEnds(3).assertTrue()
        intArrayOf(1,2,3,4).isIndex.atAnyEnds(4).assertFalse()
    }

}

class CollectionBoundsAlwaysOutOfBoundsTest {

    @Test
    fun inBoundsEndNotInBounds() {
        CollectionBoundsAlwaysOutOfBounds.inBoundsEndNotInBounds((-1)).assertFalse()
        CollectionBoundsAlwaysOutOfBounds.inBoundsEndNotInBounds(0).assertFalse()
        CollectionBoundsAlwaysOutOfBounds.inBoundsEndNotInBounds(1).assertFalse()
    }

    @Test
    fun inBoundsEndInBounds() {
        CollectionBoundsAlwaysOutOfBounds.inBoundsEndInBounds((-1)).assertFalse()
        CollectionBoundsAlwaysOutOfBounds.inBoundsEndInBounds((0)).assertFalse()
        CollectionBoundsAlwaysOutOfBounds.inBoundsEndInBounds((1)).assertFalse()
    }

}

class CollectionBoundsZeroBoundsCheckerTest {
    @Test
    fun inBoundsEndNotInBounds() {
        CollectionBoundsZeroBoundsChecker.inBoundsEndNotInBounds((-1)).assertFalse()
        CollectionBoundsZeroBoundsChecker.inBoundsEndNotInBounds(0).assertFalse()
        CollectionBoundsZeroBoundsChecker.inBoundsEndNotInBounds(1).assertFalse()
    }

    @Test
    fun inBoundsEndInBounds() {
        CollectionBoundsZeroBoundsChecker.inBoundsEndInBounds((-1)).assertFalse()
        CollectionBoundsZeroBoundsChecker.inBoundsEndInBounds(0).assertTrue()
        CollectionBoundsZeroBoundsChecker.inBoundsEndInBounds(1).assertFalse()
    }
}

class CollectionBoundsCheckerTestTest {
    @Test
    fun inBoundsEndNotInBounds() {
        CollectionBoundsChecker(1).inBoundsEndNotInBounds((-1)).assertFalse()
        CollectionBoundsChecker(1).inBoundsEndNotInBounds(0).assertTrue()
        CollectionBoundsChecker(1).inBoundsEndNotInBounds(1).assertFalse()
        CollectionBoundsChecker(1).inBoundsEndNotInBounds((-50)).assertFalse()
        CollectionBoundsChecker(1).inBoundsEndNotInBounds(42).assertFalse()
    }

    @Test
    fun inBoundsEndInBounds() {
        CollectionBoundsChecker(1).inBoundsEndInBounds((-1)).assertFalse()
        CollectionBoundsChecker(1).inBoundsEndInBounds(0).assertTrue()
        CollectionBoundsChecker(1).inBoundsEndInBounds(1).assertTrue()
        CollectionBoundsChecker(1).inBoundsEndInBounds((-50)).assertFalse()
        CollectionBoundsChecker(1).inBoundsEndInBounds(42).assertFalse()
    }
}