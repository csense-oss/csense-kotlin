@file:Suppress("unused", "NOTHING_TO_INLINE", "INVISIBLE_MEMBER", "INVISIBLE_REFERENCE")

package csense.kotlin.extensions.primitives.number

import kotlin.internal.*
import csense.kotlin.extensions.mapping.*
import csense.kotlin.extensions.primitives.byte.*
import csense.kotlin.extensions.primitives.double.*
import csense.kotlin.extensions.primitives.float.*
import csense.kotlin.extensions.primitives.int.*
import csense.kotlin.extensions.primitives.long.*
import csense.kotlin.extensions.primitives.short.*


public inline fun Number.nullOnZero(): Number? = isZero.map(
    ifTrue = null,
    ifFalse = this
)

@LowPriorityInOverloadResolution
public inline val Number.isZero: Boolean
    get() = when (this) {
        is Byte -> isZero
        is Short -> isZero
        is Int -> isZero
        is Long -> isZero
        is Float -> isZero
        is Double -> isZero
        //TODO fallback!?
        else -> toDouble().isZero
    }

public inline operator fun Number.compareTo(other: Number): Int = when (this) {
    is Byte -> this.compareTo(other)
    is Short -> this.compareTo(other)
    is Int -> this.compareTo(other)
    is Long -> this.compareTo(other)
    is Float -> this.compareTo(other)
    is Double -> this.compareTo(other)
    else -> toDouble().compareTo(other)
}

public inline operator fun Byte.compareTo(other: Number): Int = when (other) {
    is Byte -> this.compareTo(other)
    is Short -> this.compareTo(other)
    is Int -> this.compareTo(other)
    is Long -> this.compareTo(other)
    is Float -> this.compareTo(other)
    is Double -> this.compareTo(other)
    //TODO bad fallback? hmm
    else -> this.compareTo(other.toDouble())
}

public inline operator fun Short.compareTo(other: Number): Int = when (other) {
    is Byte -> this.compareTo(other)
    is Short -> this.compareTo(other)
    is Int -> this.compareTo(other)
    is Long -> this.compareTo(other)
    is Float -> this.compareTo(other)
    is Double -> this.compareTo(other)
    //TODO bad fallback? hmm
    else -> this.compareTo(other.toDouble())
}

public inline operator fun Int.compareTo(other: Number): Int = when (other) {
    is Byte -> this.compareTo(other)
    is Short -> this.compareTo(other)
    is Int -> this.compareTo(other)
    is Long -> this.compareTo(other)
    is Float -> this.compareTo(other)
    is Double -> this.compareTo(other)
    //TODO bad fallback? hmm
    else -> this.compareTo(other.toDouble())
}

public inline operator fun Long.compareTo(other: Number): Int = when (other) {
    is Byte -> this.compareTo(other)
    is Short -> this.compareTo(other)
    is Int -> this.compareTo(other)
    is Long -> this.compareTo(other)
    is Float -> this.compareTo(other)
    is Double -> this.compareTo(other)
    //TODO bad fallback? hmm
    else -> this.compareTo(other.toDouble())
}

public inline operator fun Float.compareTo(other: Number): Int = when (other) {
    is Byte -> this.compareTo(other)
    is Short -> this.compareTo(other)
    is Int -> this.compareTo(other)
    is Long -> this.compareTo(other)
    is Float -> this.compareTo(other)
    is Double -> this.compareTo(other)
    //TODO bad fallback? hmm
    else -> this.compareTo(other.toDouble())
}

public inline operator fun Double.compareTo(other: Number): Int = when (other) {
    is Byte -> this.compareTo(other)
    is Short -> this.compareTo(other)
    is Int -> this.compareTo(other)
    is Long -> this.compareTo(other)
    is Float -> this.compareTo(other)
    is Double -> this.compareTo(other)
    //TODO bad fallback? hmm
    else -> this.compareTo(other.toDouble())
}