@file:Suppress("unused", "NOTHING_TO_INLINE")

package csense.kotlin.extensions.primitives

import csense.kotlin.annotations.numbers.ByteLimit
import kotlin.experimental.and


//region Zero, negative, positive

inline val Byte.Companion.zero: Byte
    get() = 0

/**
 * Gets this Byte negative, if it is already negative, returns that.
 * this is also negative Abs.
 */
inline val Byte.negative: Byte
    @ByteLimit(to = 0)
    get() = if (this.isPositiveOrZero) {
        (-this).toByte()
    } else {
        this
    }


/**
 * this Byte positive, if it is already postive, returns that.
 * also known as abs
 */
inline val Byte.positive: Byte
    @ByteLimit(from = 0)
    get() = if (this.isNegative) {
        (this * -1).toByte()
    } else {
        this
    }

/**
 *  if this Byte is not 0 => returns true. false otherwise
 */
inline val Byte.isNotZero: Boolean
    get() = !isZero

/**
 *  if this Byte is 0 => returns true. false otherwise
 */
inline val Byte.isZero: Boolean
    get() = this == Byte.zero

/**
 * Tells if this number is either negative or zero
 */
inline val Byte.isNegativeOrZero: Boolean
    get() = this.isNegative || this.isZero

/**
 * Tells if this number is either positive or zero
 */
inline val Byte.isPositiveOrZero: Boolean
    get() = this.isPositive || this.isZero


/**
 * If this value is less than 0 then its negative
 */
inline val Byte.isNegative: Boolean
    get() = this < 0

/**
 * A value is positive iff its greater than neutral (0)
 */
inline val Byte.isPositive: Boolean
    get() = this > 0


/**
 * if this whole number is even (2,4,6....)
 */
inline val Byte.isEven: Boolean
    get() = this % 2 == 0
/**
 * If this whole number is odd (1,3,5 ...)
 */
inline val Byte.isOdd: Boolean
    get() = !isEven
//endregion

//region bit wise operations
/**
 * Shifts the bits to the left the given amount of times
 * @receiver Byte the value to SHL
 * @param shift Int the amount to shl
 * @return Byte the resulting byte; overflow are discarded
 */

inline infix fun Byte.shl(shift: Int): Byte =
        (this.toInt() shl shift).toByte()

/**
 * Shifts the bits to the right the given amount of times
 * @receiver Byte the value to SHR
 * @param shift Int the amount to shr
 * @return Byte the resulting byte; overflow are discared
 */

inline infix fun Byte.shr(shift: Int): Byte =
        (this.toInt() shr shift).toByte()
//endregion

//region hex converting
/**
 * Converts a given byte to a pair of chars, and then returns the resulting
 * @receiver Byte
 * @param action (upperChar: Char, lowerChar: Char) -> T
 * @return T
 */
inline fun <T> Byte.toChars(
        action: (upperChar: Char, lowerChar: Char) -> T
): T = splitIntoComponents { upperByte, lowerByte ->
    action(hexCharsAsString[upperByte.toInt()], hexCharsAsString[lowerByte.toInt()])
}

/**
 * converts a given byte to a hex string.
 * @receiver Byte
 * @return String
 */

inline fun Byte.toHexString(): String = this.toChars { upperChar, lowerChar ->
    String.createFromChars(charArrayOf(upperChar, lowerChar))
}


/**
 * Splits a byte of 0x2f into "0x02" and into "0x0f"
 * @receiver Byte
 * @param action (upperByte: Byte, lowerByte: Byte) -> T
 * @return T
 */
inline fun <T> Byte.splitIntoComponents(
        action: (upperByte: Byte, lowerByte: Byte) -> T
): T {
    val lower: Byte = this and 0x0f
    val upper: Byte = (this shr 4) and 0x0F.toByte()
    return action(upper, lower)
}

/**
 *
 */
const val hexCharsAsString = "0123456789ABCDEF"
//endregion

