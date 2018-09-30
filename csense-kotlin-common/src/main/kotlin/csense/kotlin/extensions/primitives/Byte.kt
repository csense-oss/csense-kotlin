@file:Suppress("unused", "NOTHING_TO_INLINE")
package csense.kotlin.extensions.primitives

import kotlin.experimental.*

/**
 * Shifts the bits to the left the given amount of times
 * @receiver Byte the value to SHL
 * @param shift Int the amount to shl
 * @return Byte the resulting byte; overflow are discared
 */
@Suppress("NOTHING_TO_INLINE")
inline infix fun Byte.shl(shift: Int): Byte = (this.toInt() shl shift).toByte()

/**
 * Shifts the bits to the right the given amount of times
 * @receiver Byte the value to SHR
 * @param shift Int the amount to shr
 * @return Byte the resulting byte; overflow are discared
 */
@Suppress("NOTHING_TO_INLINE")
inline infix fun Byte.shr(shift: Int): Byte = (this.toInt() shr shift).toByte()

/**
 * Converts a given byte to a pair of chars, and then returns the resulting
 * @receiver Byte
 * @param action (upperChar: Char, lowerChar: Char) -> T
 * @return T
 */
inline fun <T> Byte.toChars(crossinline action: (upperChar: Char, lowerChar: Char) -> T): T =
        splitIntoComponents { upperByte, lowerByte ->
            action(hexCharsAsString[upperByte.toInt()], hexCharsAsString[lowerByte.toInt()])
        }

/**
 * converts a given byte to a hex string.
 * @receiver Byte
 * @return String
 */
@Suppress("NOTHING_TO_INLINE")
inline fun Byte.toHexString(): String =
        this.toChars { upperChar, lowerChar ->
            String.createFromChars(kotlin.charArrayOf(upperChar, lowerChar))
        }


/**
 * Splits a byte of 0x2f into "0x02" and into "0x0f"
 * @receiver Byte
 * @param action (upperByte: Byte, lowerByte: Byte) -> T
 * @return T
 */
inline fun <T> Byte.splitIntoComponents(crossinline action: (upperByte: Byte, lowerByte: Byte) -> T): T {
    val lower: Byte = this and 0x0f
    val upper: Byte = (this shr 4) and 0x0F.toByte()
    return action(upper, lower)
}

/**
 *
 */
const val hexCharsAsString = "0123456789ABCDEF"