@file:Suppress("unused", "NOTHING_TO_INLINE")

package csense.kotlin.extensions.primitives.operations

import csense.kotlin.annotations.numbers.*
import csense.kotlin.extensions.primitives.byte.*
import csense.kotlin.units.*
import kotlin.experimental.*
import kotlin.jvm.*


@JvmInline
public value class ByteBitOperations(public val byte: Byte)


public inline val Byte.bitOperations: ByteBitOperations
    get() = ByteBitOperations(this)


@ByteLimit(from = 0x0F, to = 0xFF.toByte())
public inline fun ByteBitOperations.clearLowerNibble(): Byte =
    byte.and(0b1111_0000.toByte())

@ByteLimit(from = 0, to = 0x0F)
public inline fun ByteBitOperations.clearUpperNibble(): Byte =
    byte.and(0b0000_1111)


@ByteLimit(from = 0x0F, to = 0xFF.toByte())
public inline fun ByteBitOperations.shiftLowerNibbleToUpper(): Byte =
    shl(4)

@ByteLimit(from = 0, to = 0x0F)
public inline fun ByteBitOperations.shiftUpperNibbleToLower(): Byte =
    ushr(4)

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
    val upperNibbleWithLowerCleared = upperNibble.bitOperations.shiftLowerNibbleToUpper()
    return byteWithUpperCleared.or(upperNibbleWithLowerCleared) //or works like "add" when bits are not set.
}

public inline fun ByteBitOperations.updateLowerNibble(
    @ByteLimit(from = 0, to = 0x0F) lowerNibble: Byte
): Byte {
    val byteWithUpperCleared = clearLowerNibble()
    val upperNibbleWithLowerCleared = lowerNibble.bitOperations.clearUpperNibble()
    return byteWithUpperCleared.or(upperNibbleWithLowerCleared) //or works like "add" when bits are not set.
}

public inline infix fun ByteBitOperations.ushr(@IntLimit(from = 1, to = 7) shift: Int): Byte =
    byte.toIntBitWise().ushr(shift).toByte()

/**
 * Shifts the bits to the left the given amount of times
 * @receiver ByteBitOperations
 * @param shift [Int] the number of times to shift left
 * @return [Byte] the resulting byte; overflow are discarded
 */

public inline infix fun ByteBitOperations.shl(@IntLimit(from = 1, to = 7) shift: Int): Byte =
    (byte.toIntBitWise() shl shift).toByte()

/**
 * Shifts the bits to the right the given amount of times
 * @receiver ByteBitOperations
 * @param shift [Int] the number of times to shift right
 * @return [Byte] the resulting byte; overflow are discarded
 */
public inline infix fun ByteBitOperations.shr(@IntLimit(from = 1, to = 7) shift: Int): Byte =
    (byte.toIntBitWise() shr shift).toByte()


public inline fun ByteBitOperations.splitIntoNibbles(): NibblePair {
    val lower: Byte = clearUpperNibble()
    val upper: Byte = (this shr 4).bitOperations.clearUpperNibble()
    return NibblePair(upper, lower)
}

public data class NibblePair(
    @ByteLimit(from = 0, to = 0x0F) public val upperNibble: Byte,
    @ByteLimit(from = 0, to = 0x0F) public val lowerNibble: Byte
)

public inline fun ByteBitOperations.toggleBit(bitIndex: Int, setBit: Boolean): Byte = when {
    setBit -> setBit(bitIndex)
    else -> zeroBit(bitIndex)
}

public inline fun ByteBitOperations.zeroBit(bitIndex: Int): Byte {
    val bitMask = 1.shl(bitIndex).inv().toByte()
    return byte.and(bitMask)
}

public inline fun ByteBitOperations.setBit(bitIndex: Int): Byte {
    val bitMask = 1.shl(bitIndex).toByte()
    return byte.or(bitMask)
}