package csense.kotlin.extensions.collections.generic

import csense.kotlin.extensions.collections.generic.collectionBounds.*
import csense.kotlin.extensions.collections.generic.collectionBounds.operations.*
import csense.kotlin.tests.assertions.*
import kotlin.test.*

class CollectionBoundsTest {
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

    class CollectionIsIndex {
        @Test
        fun empty() {
            listOf<String>().isIndex.assertIs<CollectionBounds.CollectionBoundsZeroBoundsChecker>()
        }

        @Test
        fun single() {
            listOf("").isIndex.assertIsApply<CollectionBounds.CollectionBoundsChecker> {
                collectionLength.assert(1)
            }
        }

        @Test
        fun multiple() {
            listOf("a", "b").isIndex.assertIsApply<CollectionBounds.CollectionBoundsChecker> {
                collectionLength.assert(2)
            }
        }
    }

    class CharSequenceIsIndex {
        @Test
        fun empty() {
            "".isIndex.assertIs<CollectionBounds.CollectionBoundsZeroBoundsChecker>()
        }

        @Test
        fun single() {
            "a".isIndex.assertIsApply<CollectionBounds.CollectionBoundsChecker> {
                collectionLength.assert(1)
            }
        }

        @Test
        fun multiple() {
            "ab".isIndex.assertIsApply<CollectionBounds.CollectionBoundsChecker> {
                collectionLength.assert(2)
            }
        }
    }

    class MapIsIndex {
        @Test
        fun empty() {
            mapOf<String, String>().isIndex.assertIs<CollectionBounds.CollectionBoundsZeroBoundsChecker>()
        }

        @Test
        fun single() {
            mapOf("1" to "a").isIndex.assertIsApply<CollectionBounds.CollectionBoundsChecker> {
                collectionLength.assert(1)
            }
        }

        @Test
        fun multiple() {
            mapOf("1" to "a", "2" to "b").isIndex.assertIsApply<CollectionBounds.CollectionBoundsChecker> {
                collectionLength.assert(2)
            }
        }
    }

    class ArrayIsIndex {
        @Test
        fun empty() {
            arrayOf<String>().isIndex.assertIs<CollectionBounds.CollectionBoundsZeroBoundsChecker>()
        }

        @Test
        fun single() {
            arrayOf("asd").isIndex.assertIsApply<CollectionBounds.CollectionBoundsChecker> {
                collectionLength.assert(1)
            }
        }

        @Test
        fun multiple() {
            arrayOf("asd", "123").isIndex.assertIsApply<CollectionBounds.CollectionBoundsChecker> {
                collectionLength.assert(2)
            }
        }
    }

    @Test
    fun booleanArrayIsIndex() {
        booleanArrayOf().isIndex.assertIs<CollectionBounds.CollectionBoundsZeroBoundsChecker>()
        booleanArrayOf(false).isIndex.assertIsApply<CollectionBounds.CollectionBoundsChecker> {
            collectionLength.assert(1)
        }
        booleanArrayOf(true, false).isIndex.assertIsApply<CollectionBounds.CollectionBoundsChecker> {
            collectionLength.assert(2)
        }
    }

    @Test
    fun byteArrayIsIndex() {
        byteArrayOf().isIndex.assertIs<CollectionBounds.CollectionBoundsZeroBoundsChecker>()
        byteArrayOf(0).isIndex.assertIsApply<CollectionBounds.CollectionBoundsChecker> {
            collectionLength.assert(1)
        }
        byteArrayOf(0, 1).isIndex.assertIsApply<CollectionBounds.CollectionBoundsChecker> {
            collectionLength.assert(2)
        }
    }

    @Test
    fun charArrayIsIndex() {
        charArrayOf().isIndex.assertIs<CollectionBounds.CollectionBoundsZeroBoundsChecker>()
        charArrayOf('0').isIndex.assertIsApply<CollectionBounds.CollectionBoundsChecker> {
            collectionLength.assert(1)
        }
        charArrayOf('0', '1').isIndex.assertIsApply<CollectionBounds.CollectionBoundsChecker> {
            collectionLength.assert(2)
        }
    }

    @Test
    fun doubleArrayIsIndex() {
        doubleArrayOf().isIndex.assertIs<CollectionBounds.CollectionBoundsZeroBoundsChecker>()
        doubleArrayOf(0.0).isIndex.assertIsApply<CollectionBounds.CollectionBoundsChecker> {
            collectionLength.assert(1)
        }
        doubleArrayOf(0.0, 0.0).isIndex.assertIsApply<CollectionBounds.CollectionBoundsChecker> {
            collectionLength.assert(2)
        }
    }

    @Test
    fun floatArrayIsIndex() {
        floatArrayOf().isIndex.assertIs<CollectionBounds.CollectionBoundsZeroBoundsChecker>()
        floatArrayOf(0.0f).isIndex.assertIsApply<CollectionBounds.CollectionBoundsChecker> {
            collectionLength.assert(1)
        }
        floatArrayOf(0.0f, 0.0f).isIndex.assertIsApply<CollectionBounds.CollectionBoundsChecker> {
            collectionLength.assert(2)
        }
    }

    @Test
    fun intArrayIsIndex() {
        intArrayOf().isIndex.assertIs<CollectionBounds.CollectionBoundsZeroBoundsChecker>()
        intArrayOf(0).isIndex.assertIsApply<CollectionBounds.CollectionBoundsChecker> {
            collectionLength.assert(1)
        }
        intArrayOf(0, 0).isIndex.assertIsApply<CollectionBounds.CollectionBoundsChecker> {
            collectionLength.assert(2)
        }
    }

    @Test
    fun longArrayIsIndex() {
        longArrayOf().isIndex.assertIs<CollectionBounds.CollectionBoundsZeroBoundsChecker>()
        longArrayOf(0).isIndex.assertIsApply<CollectionBounds.CollectionBoundsChecker> {
            collectionLength.assert(1)
        }
        longArrayOf(0, 0).isIndex.assertIsApply<CollectionBounds.CollectionBoundsChecker> {
            collectionLength.assert(2)
        }
    }

    @Test
    fun shortArrayIsIndex() {
        shortArrayOf().isIndex.assertIs<CollectionBounds.CollectionBoundsZeroBoundsChecker>()
        shortArrayOf(0).isIndex.assertIsApply<CollectionBounds.CollectionBoundsChecker> {
            collectionLength.assert(1)
        }
        shortArrayOf(0, 0).isIndex.assertIsApply<CollectionBounds.CollectionBoundsChecker> {
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
        CollectionBounds.CollectionBoundsChecker(1).inBoundsEndNotInBounds((-1)).assertFalse()
        CollectionBounds.CollectionBoundsChecker(1).inBoundsEndNotInBounds(0).assertTrue()
        CollectionBounds.CollectionBoundsChecker(1).inBoundsEndNotInBounds(1).assertFalse()
        CollectionBounds.CollectionBoundsChecker(1).inBoundsEndNotInBounds((-50)).assertFalse()
        CollectionBounds.CollectionBoundsChecker(1).inBoundsEndNotInBounds(42).assertFalse()
    }

    @Test
    fun inBoundsEndInBounds() {
        CollectionBounds.CollectionBoundsChecker(1).inBoundsEndInBounds((-1)).assertFalse()
        CollectionBounds.CollectionBoundsChecker(1).inBoundsEndInBounds(0).assertTrue()
        CollectionBounds.CollectionBoundsChecker(1).inBoundsEndInBounds(1).assertTrue()
        CollectionBounds.CollectionBoundsChecker(1).inBoundsEndInBounds((-50)).assertFalse()
        CollectionBounds.CollectionBoundsChecker(1).inBoundsEndInBounds(42).assertFalse()
    }
}