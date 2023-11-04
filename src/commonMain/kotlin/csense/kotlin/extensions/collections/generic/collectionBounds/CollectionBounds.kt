@file:Suppress("MemberVisibilityCanBePrivate")

package csense.kotlin.extensions.collections.generic.collectionBounds

import csense.kotlin.annotations.numbers.*
import csense.kotlin.extensions.primitives.int.*
import kotlin.jvm.*

public sealed interface CollectionBounds {

    public fun inBoundsEndNotInBounds(index: Int): Boolean

    public fun inBoundsEndInBounds(index: Int): Boolean

    public data object CollectionBoundsAlwaysOutOfBounds : CollectionBounds {
        override fun inBoundsEndNotInBounds(index: Int): Boolean {
            return false
        }

        override fun inBoundsEndInBounds(index: Int): Boolean {
            return false
        }

    }

    public data object CollectionBoundsZeroBoundsChecker : CollectionBounds {
        override fun inBoundsEndNotInBounds(index: Int): Boolean {
            return false
        }

        override fun inBoundsEndInBounds(index: Int): Boolean {
            return index == 0
        }

    }

    @JvmInline
    public value class CollectionBoundsChecker internal constructor(
        @IntLimit(from = 1) public val collectionLength: Int
    ) : CollectionBounds {

        override fun inBoundsEndNotInBounds(index: Int): Boolean {
            return index < collectionLength && index.isPositiveOrZero
        }

        override fun inBoundsEndInBounds(index: Int): Boolean {
            return index <= collectionLength && index.isPositiveOrZero
        }

    }

}

public fun CollectionBounds(collectionLength: Int): CollectionBounds = when {
    collectionLength.isNegative -> CollectionBounds.CollectionBoundsAlwaysOutOfBounds
    collectionLength.isZero -> CollectionBounds.CollectionBoundsZeroBoundsChecker
    else -> CollectionBounds.CollectionBoundsChecker(collectionLength)
}