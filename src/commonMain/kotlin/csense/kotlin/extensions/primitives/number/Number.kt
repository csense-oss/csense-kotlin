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
        else -> false
    }