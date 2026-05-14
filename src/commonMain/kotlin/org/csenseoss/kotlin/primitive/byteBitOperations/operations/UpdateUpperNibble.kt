@file:Suppress("NOTHING_TO_INLINE")

package org.csenseoss.kotlin.primitive.byteBitOperations.operations

import org.csenseoss.kotlin.annotations.numbers.limit.*
import org.csenseoss.kotlin.primitive.byteBitOperations.ByteBitOperations
import org.csenseoss.kotlin.primitive.byteBitOperations.bits
import kotlin.experimental.*


/**
 *
 * @receiver ByteBitOperations
 * @param upperNibble [Byte] only the first 4 bits are used (the least significant bits)
 * @return Byte
 */
public inline fun ByteBitOperations.updateUpperNibble(
    @ByteLimit(from = 0x00, to = 0x0F) upperNibble: Byte
): Byte {
    val byteWithUpperCleared = clearUpperNibble()
    val upperNibbleWithLowerCleared = upperNibble.bits.shiftLowerNibbleToUpper()
    return byteWithUpperCleared.or(upperNibbleWithLowerCleared) //or works like "add" when bits are not set.
}