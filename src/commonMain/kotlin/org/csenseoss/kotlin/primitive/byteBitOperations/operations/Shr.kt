@file:Suppress("NOTHING_TO_INLINE")

package org.csenseoss.kotlin.primitive.byteBitOperations.operations

import org.csenseoss.kotlin.annotations.numbers.limit.*
import org.csenseoss.kotlin.primitive.byteBitOperations.ByteBitOperations
import org.csenseoss.kotlin.extensions.primitives.byte.*

/**
 * Shifts the bits to the right the given amount of times
 * @receiver ByteBitOperations
 * @param shift [Int] the number of times to shift right
 * @return [Byte] the resulting byte; overflow are discarded
 */
public inline infix fun ByteBitOperations.shr(@IntLimit(from = 1, to = 7) shift: Int): Byte =
    (byte.toIntBitWise() shr shift).toByte()