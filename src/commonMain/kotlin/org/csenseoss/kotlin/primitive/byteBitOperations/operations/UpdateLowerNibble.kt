@file:Suppress("NOTHING_TO_INLINE")

package org.csenseoss.kotlin.primitive.byteBitOperations.operations

import org.csenseoss.kotlin.annotations.numbers.limit.*
import org.csenseoss.kotlin.primitive.byteBitOperations.ByteBitOperations
import org.csenseoss.kotlin.primitive.byteBitOperations.bits
import kotlin.experimental.*


public inline fun ByteBitOperations.updateLowerNibble(
    @ByteLimit(from = 0, to = 0x0F) lowerNibble: Byte
): Byte {
    val byteWithUpperCleared = clearLowerNibble()
    val upperNibbleWithLowerCleared = lowerNibble.bits.clearUpperNibble()
    return byteWithUpperCleared.or(upperNibbleWithLowerCleared) //or works like "add" when bits are not set.
}
