@file:Suppress("unused", "NOTHING_TO_INLINE")

package csense.kotlin.extensions.primitives

import csense.kotlin.annotations.numbers.ByteLimit
import csense.kotlin.extensions.mapLazy
import kotlin.experimental.or

/**
 *changes the casing of this char to the given casing
 * @receiver [Char]
 * @param upperCase [Boolean]
 * @return [Char]
 */
inline fun Char.toCase(upperCase: Boolean): Char = upperCase.mapLazy(
        ifTrue = this::toUpperCase,
        ifFalse = this::toLowerCase)

/**
 * Tries to convert this char into a decimal value (0 to 9)
 * @receiver [Char]
 * @return [Byte]?
 */
@ByteLimit(from = 0, to = 9)
inline fun Char.asDigit(): Byte? {
    val diff = toByte() - CharExtensions.charZeroAsByte
    if (diff.isNegative || diff > CharExtensions.numberCharsCount) {
        return null
    }
    return diff.toByte()
}

//region Hex related functions
/**
 * Tries to convert this byte into a "hex" value.
 * TODO UBYTE
 * @receiver [Char]
 * @return [Byte]?
 */
inline fun Char.asHexDigit(): Byte? {
    val asNumber = asDigit()
    if (asNumber != null) {
        return asNumber
    }
    //then its either [A-F] or not a hex
    val thisByte = toLowerCase().toByte() - CharExtensions.charAAsByte
    if (thisByte.isNegative || thisByte > CharExtensions.hexCharsCount) {
        return null
    }
    
    return (thisByte + 0x0a).toByte()
}

/**
 * Converts 2 part of a hex chars (eg "f", and e") will be converted into the combined value ("0xFE") and converted into a short with that value.
 * TODO UBYTE
 * @param first [Char]
 * @param second [Char]
 * @return [Short]? the potential valid hex value.
 */
inline fun hexCharsToValue(first: Char, second: Char): Short? {
    val firstToInt = first.asHexDigit()?.toShort() ?: return null
    val secondToInt = second.asHexDigit()?.toShort() ?: return null
    return firstToInt.shl(4) or secondToInt
}


//endregion

/**
 * Tells if this [Char] is uppercase
 * for native use this implementation
 *  toUpperCase().equals(this, false) unless you know a better way (with no alloc)
 */
expect inline fun Char.isUpperCaseLetter(): Boolean
//get() = toUpperCase().equals(this, false)

/**
 * Tells if this [Char] is lowercase
 * for native use this implementation
 *  toLowerCase().equals(this, false) unless you know a better way (with no alloc)
 */
expect inline fun Char.isLowerCaseLetter(): Boolean
//get() = toLowerCase().equals(this, false)

/**
 * Tells if this is a number / digit (0,1,2,3,4,5,6,7,8,9)
 *
 * true if it is a number, false otherwise
 */
inline fun Char.isDigit(): Boolean =
        this in '0'..'9'

/**
 * Tells if this is NOT a digit (0 to 9)
 * @receiver [Char] the char to test
 * @return [Boolean] true if it is not a digit, false if it is
 */
inline fun Char.isNotDigit(): Boolean =
        !isDigit()


inline class CharExtensions(val char: Char) {
    companion object {
        
        const val charZeroAsByte = '0'.toByte()
        
        const val charAAsByte = 'a'.toByte()
        
        /**
         * The length (0 indexed) of numbers ( 0 until 9)
         */
        const val numberCharsCount: Int = 9
        
        /**
         * The length (0 indexed) of chars that represents hex numbers ( a - f)
         */
        const val hexCharsCount: Int = 5
    }
}
//validate whenever "digit" in other text systems are more than "0-9" (eg say Chinese)
