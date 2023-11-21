@file:Suppress("unused", "NOTHING_TO_INLINE", "INVISIBLE_MEMBER")


package csense.kotlin.extensions.collections.array.generic

public inline fun Array<*>.isSingle(): Boolean =
    size == 1

public inline fun Array<*>.isSingleOrEmpty(): Boolean =
    isSingle() || isEmpty()

