package csense.kotlin.extensions.collections.generic.collectionBounds.operations

import csense.kotlin.extensions.collections.generic.collectionBounds.*
import csense.kotlin.tests.assertions.*
import kotlin.test.*

class IsIndexTest {


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
}