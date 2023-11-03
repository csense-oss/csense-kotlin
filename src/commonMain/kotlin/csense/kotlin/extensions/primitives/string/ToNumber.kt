@file:Suppress("NOTHING_TO_INLINE")

package csense.kotlin.extensions.primitives.string

public fun String.toByteOrZero(): Byte =
    toByteOrNull() ?: 0

public fun String.toShortOrZero(): Short =
    toShortOrNull() ?: 0

public fun String.toIntOrZero(): Int =
    toIntOrNull() ?: 0

public fun String.toLongOrZero(): Long =
    toLongOrNull() ?: 0L


