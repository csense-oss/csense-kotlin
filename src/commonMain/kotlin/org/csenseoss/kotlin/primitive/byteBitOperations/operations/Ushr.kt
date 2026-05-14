@file:Suppress("NOTHING_TO_INLINE")

package org.csenseoss.kotlin.primitive.byteBitOperations.operations

import org.csenseoss.kotlin.annotations.numbers.limit.*
import org.csenseoss.kotlin.primitive.byteBitOperations.ByteBitOperations
import org.csenseoss.kotlin.extensions.primitives.byte.*


public inline infix fun ByteBitOperations.ushr(@IntLimit(from = 1, to = 7) shift: Int): Byte =
    byte.toIntBitWise().ushr(shift).toByte()

