package org.csenseoss.kotlin.extensions.collections.generic.collectionBounds.operations

import org.csenseoss.kotlin.extensions.collections.generic.collectionBounds.*
import org.csenseoss.kotlin.tests.assertions.*
import org.csenseoss.kotlin.tests.assertions.comparable.*
import org.csenseoss.kotlin.tests.assertions.general.*
import kotlin.contracts.*
import kotlin.test.*

class IsIndexTest {


    class CollectionIsIndex {
        @Test
        fun empty() {
            listOf<String>().isIndex.assertZeroBounds()
        }

        @Test
        fun single() {
            listOf("").isIndex.assertCollectionLength(1)
        }

        @Test
        fun multiple() {
            listOf("a", "b").isIndex.assertCollectionLength(2)
        }
    }

    class CharSequenceIsIndex {
        @Test
        fun empty() {
            "".isIndex.assertZeroBounds()
        }

        @Test
        fun single() {
            "a".isIndex.assertCollectionLength(1)
        }

        @Test
        fun multiple() {
            "ab".isIndex.assertCollectionLength(2)
        }
    }

    class MapIsIndex {
        @Test
        fun empty() {
            mapOf<String, String>().isIndex.assertZeroBounds()
        }

        @Test
        fun single() {
            mapOf("1" to "a").isIndex.assertCollectionLength(1)
        }

        @Test
        fun multiple() {
            mapOf("1" to "a", "2" to "b").isIndex.assertCollectionLength(2)
        }
    }

    class ArrayIsIndex {
        @Test
        fun empty() {
            arrayOf<String>().isIndex.assertZeroBounds()
        }

        @Test
        fun single() {
            arrayOf("asd").isIndex.assertCollectionLength(1)
        }

        @Test
        fun multiple() {
            arrayOf("asd", "123").isIndex.assertCollectionLength(2)
        }
    }

    @Test
    fun booleanArrayIsIndex() {
        booleanArrayOf().isIndex.assertZeroBounds()
        booleanArrayOf(false).isIndex.assertCollectionLength(1)
        booleanArrayOf(true, false).isIndex.assertCollectionLength(2)
    }

    @Test
    fun byteArrayIsIndex() {
        byteArrayOf().isIndex.assertZeroBounds()
        byteArrayOf(0).isIndex.assertCollectionLength(1)
        byteArrayOf(0, 1).isIndex.assertCollectionLength(2)
    }

    @Test
    fun charArrayIsIndex() {
        charArrayOf().isIndex.assertZeroBounds()
        charArrayOf('0').isIndex.assertCollectionLength(1)
        charArrayOf('0', '1').isIndex.assertCollectionLength(2)
    }

    @Test
    fun doubleArrayIsIndex() {
        doubleArrayOf().isIndex.assertZeroBounds()
        doubleArrayOf(0.0).isIndex.assertCollectionLength(1)
        doubleArrayOf(0.0, 0.0).isIndex.assertCollectionLength(2)
    }

    @Test
    fun floatArrayIsIndex() {
        floatArrayOf().isIndex.assertZeroBounds()
        floatArrayOf(0.0f).isIndex.assertCollectionLength(1)
        floatArrayOf(0.0f, 0.0f).isIndex.assertCollectionLength(2)
    }

    @Test
    fun intArrayIsIndex() {
        intArrayOf().isIndex.assertZeroBounds()
        intArrayOf(0).isIndex.assertCollectionLength(1)
        intArrayOf(0, 0).isIndex.assertCollectionLength(2)
    }

    @Test
    fun longArrayIsIndex() {
        longArrayOf().isIndex.assertZeroBounds()
        longArrayOf(0).isIndex.assertCollectionLength(1)
        longArrayOf(0, 0).isIndex.assertCollectionLength(2)
    }

    @Test
    fun shortArrayIsIndex() {
        shortArrayOf().isIndex.assertZeroBounds()
        shortArrayOf(0).isIndex.assertCollectionLength(1)
        shortArrayOf(0, 0).isIndex.assertCollectionLength(2)
    }
}

fun CollectionBounds.assertCollectionLength(length: Int) {
    contract { returns() implies (this@assertCollectionLength is CollectionBounds.CollectionBoundsChecker) }
    assertIs<CollectionBounds.CollectionBoundsChecker>()
    collectionLength.assert(length)
}

fun CollectionBounds.assertZeroBounds() {
    contract { returns() implies (this@assertZeroBounds is CollectionBounds.CollectionBoundsZeroBoundsChecker) }
    assertIs<CollectionBounds.CollectionBoundsZeroBoundsChecker>()
}