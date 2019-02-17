@file:Suppress("unused", "NOTHING_TO_INLINE")

package csense.kotlin.extensions.primitives

import csense.kotlin.extensions.*

/**
 *changes the casing of this char to the given casing
 * @receiver Char
 * @param upperCase Boolean
 * @return Char
 */
inline fun Char.toCase(upperCase: Boolean): Char = upperCase.mapLazy(
        ifTrue = this::toUpperCase,
        ifFalse = this::toLowerCase)

/**
 * Tries to convert this char into a decimal value (0 to 9)
 * @receiver Char
 * @return Byte?
 */
fun Char.asDigit(): Byte? {
    val diff = toByte() - charZeroAsByte
    if (diff.isNegative || diff > numberCharsCount) {
        return null
    }
    return diff.toByte()
}

/**
 * Tries to convert this byte into a "hex" value.
 * @receiver Char
 * @return Byte?
 */
fun Char.asHexDigit(): Byte? {
    val asNumber = asDigit()
    if (asNumber != null) {
        return asNumber
    }
    //then its either [A-F] or not a hex
    val thisByte = toLowerCase().toByte() - charAAsByte
    if (thisByte.isNegative || thisByte > hexCharsCount) {
        return null
    }

    return (thisByte + 0x0a).toByte()
}

private const val charZeroAsByte = '0'.toByte()
private const val charAAsByte = 'a'.toByte()

/**
 * The length (0 indexed) of numbers ( 0 until 9)
 */
const val numberCharsCount: Int = 9
/**
 * The length (0 indexed) of chars that represents hex numbers ( a - f)
 */
const val hexCharsCount: Int = 5