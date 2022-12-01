@file:Suppress("MemberVisibilityCanBePrivate")

package csense.kotlin.extensions.collections.generic

import csense.kotlin.annotations.numbers.*
import csense.kotlin.extensions.*
import csense.kotlin.extensions.mapping.*
import csense.kotlin.extensions.primitives.*
import csense.kotlin.extensions.primitives.int.*
import kotlin.jvm.*

public sealed interface CollectionBounds {

    public fun inBoundsEndNotInBounds(index: Int): Boolean

    public fun inBoundsEndInBounds(index: Int): Boolean

    public companion object {
        @Suppress("MissingTestFunction") //plugin bug
        public fun from(collectionLength: Int): CollectionBounds = when {
            collectionLength.isNegative -> CollectionBoundsAlwaysOutOfBounds
            collectionLength.isZero -> CollectionBoundsZeroBoundsChecker
            else -> CollectionBoundsChecker(collectionLength)
        }
    }
}

public fun CollectionBounds(collectionLength: Int): CollectionBounds = CollectionBounds.from(collectionLength)

public fun CollectionBounds.outOfBoundsEndOutOfBounds(index: Int): Boolean {
    return !inBoundsEndNotInBounds(index)
}

public fun CollectionBounds.outOfBoundsEndInBounds(index: Int): Boolean {
    return !inBoundsEndInBounds(index)
}


public fun CollectionBounds.inBounds(index: Int, isEndInBounds: Boolean): Boolean {
    return isEndInBounds.map(
        ifTrue = ::inBoundsEndInBounds,
        ifFalse = ::inBoundsEndNotInBounds
    ).invoke(index)
}

public fun CollectionBounds.outOfBounds(index: Int, isEndOutOfBonds: Boolean): Boolean {
    return isEndOutOfBonds.map(
        ifTrue = ::outOfBoundsEndOutOfBounds,
        ifFalse = ::outOfBoundsEndInBounds
    ).invoke(index)
}

@Suppress("UnusedReceiverParameter")
public fun CollectionBounds.atStart(index: Int): Boolean {
    return index == 0
}

public fun CollectionBounds.atLast(index: Int): Boolean {
    return inBoundsEndNotInBounds(index) && !inBoundsEndNotInBounds(index + 1)
}

public fun CollectionBounds.atAnyEnds(index: Int): Boolean {
    return atStart(index) || atLast(index)
}


public object CollectionBoundsAlwaysOutOfBounds : CollectionBounds {
    override fun inBoundsEndNotInBounds(index: Int): Boolean {
        return false
    }

    override fun inBoundsEndInBounds(index: Int): Boolean {
        return false
    }

}

public object CollectionBoundsZeroBoundsChecker : CollectionBounds {
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


public inline val Collection<*>.isIndex: CollectionBounds
    get() = CollectionBounds(collectionLength = size)

public inline val CharSequence.isIndex: CollectionBounds
    get() = CollectionBounds(collectionLength = length)

public inline val Map<*, *>.isIndex: CollectionBounds
    get() = CollectionBounds(collectionLength = size)

//region array types
public inline val Array<*>.isIndex: CollectionBounds
    get() = CollectionBounds(collectionLength = size)

public inline val BooleanArray.isIndex: CollectionBounds
    get() = CollectionBounds(collectionLength = size)

public inline val ByteArray.isIndex: CollectionBounds
    get() = CollectionBounds(collectionLength = size)

public inline val CharArray.isIndex: CollectionBounds
    get() = CollectionBounds(collectionLength = size)

public inline val DoubleArray.isIndex: CollectionBounds
    get() = CollectionBounds(collectionLength = size)

public inline val FloatArray.isIndex: CollectionBounds
    get() = CollectionBounds(collectionLength = size)

public inline val IntArray.isIndex: CollectionBounds
    get() = CollectionBounds(collectionLength = size)

public inline val LongArray.isIndex: CollectionBounds
    get() = CollectionBounds(collectionLength = size)

public inline val ShortArray.isIndex: CollectionBounds
    get() = CollectionBounds(collectionLength = size)
//endregion
