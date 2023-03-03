@file:Suppress("unused", "NOTHING_TO_INLINE")

package csense.kotlin.extensions.primitives.char

import csense.kotlin.annotations.numbers.*
import csense.kotlin.extensions.mapping.*
import csense.kotlin.extensions.primitives.int.*
import csense.kotlin.extensions.primitives.short.*
import kotlin.experimental.*
import kotlin.jvm.*

/**
 *changes the casing of this char to the given casing
 * @receiver [Char]
 * @param upperCase [Boolean]
 * @return [Char]
 */
public inline fun Char.toCase(upperCase: Boolean): Char = upperCase.mapLazy(
    ifTrue = this::uppercaseChar,
    ifFalse = this::lowercaseChar
)

/**
 * Tries to convert this char into a decimal value (0 to 9)
 * @receiver [Char]
 * @return [Byte]?
 */
@ByteLimit(from = 0, to = 9)
public inline fun Char.asDigit(): Byte? {
    val diff = code - CharExtensions.charZeroAsByte
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
public inline fun Char.asHexDigit(): Byte? {
    val asNumber = asDigit()
    if (asNumber != null) {
        return asNumber
    }
    //then It's either [A-F] or not a hex
    val thisByte: Int = lowercaseChar().code - CharExtensions.charAAsByte
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
public inline fun hexCharsToValue(first: Char, second: Char): Short? {
    val firstToInt = first.asHexDigit()?.toShort() ?: return null
    val secondToInt = second.asHexDigit()?.toShort() ?: return null
    return firstToInt.shl(4) or secondToInt
}


//endregion

/**
 * Tells if this [Char] is uppercase
 */
public inline fun Char.isUpperCaseLetter(): Boolean {
    if (this.isNotLetter()) {
        return false
    }
    return uppercaseChar().equals(other = this, ignoreCase = false)
}

/**
 * Tells if this [Char] is lowercase
 */
public inline fun Char.isLowerCaseLetter(): Boolean {
    if (this.isNotLetter()) {
        return false
    }
    return lowercaseChar().equals(other = this, ignoreCase = false)
}

/**
 * Tells if this is a number / digit (0,1,2,3,4,5,6,7,8,9)
 *
 * true if it is a number, false otherwise
 */
public inline fun Char.isDigit(): Boolean =
    this in '0'..'9'

/**
 * Tells if this is NOT a digit (0 to 9)
 * @receiver [Char] the char to test
 * @return [Boolean] true if it is not a digit, false if it is
 */
public inline fun Char.isNotDigit(): Boolean =
    !isDigit()


@JvmInline
public value class CharExtensions(public val char: Char) {
    public companion object {

        public const val charZeroAsByte: Int = '0'.code

        public const val charAAsByte: Int = 'a'.code

        /**
         * The length (0 indexed) of numbers ( 0 until 9)
         */
        public const val numberCharsCount: Int = 9

        /**
         * The length (0 indexed) of chars that represents hex numbers ( a - f)
         */
        public const val hexCharsCount: Int = 5
    }
}
//validate whenever "digit" in other text systems are more than "0-9" (eg say Chinese)

/**
 * Tells if this Char is different from a whitespace
 * @receiver Char the Char to test
 * @return Boolean True if the char is different from whitespace, false if it is whitespace
 */
public inline fun Char.isNotWhitespace(): Boolean = !isWhitespace()

public inline fun Char.isNotEqual(other: Char, ignoreCase: Boolean = false): Boolean {
    return !equals(other, ignoreCase)
}


/**
 *
 * @receiver [Char]
 * @return [Boolean]
 * see
 * https://en.wikipedia.org/wiki/List_of_Unicode_characters
 */
public inline fun Char.isSymbol(): Boolean {
    return isNotLetter() && isNotDigit()
}

//INCOMPLETE
/**
 *
 * @receiver [Char]
 * @return [Boolean]
 * see
 * https://en.wikipedia.org/wiki/List_of_Unicode_characters
 */
public fun Char.isLetter(): Boolean {
    return CharTable.isCharInTable(this)
}

public inline fun Char.isNotLetter(): Boolean =
    !isLetter()

//TODO move and improve
internal object CharTable {


    //see https://en.wikipedia.org/wiki/List_of_Unicode_characters
    private val latinLowerCase: CharRange = 'a'..'z'
    private val latinUpperCase: CharRange = 'A'..'Z'
    private val latin1SuppUpperCase: CharRange = 'À'..'Ö'
    private val latin1SuppUpperCase2: CharRange = 'Ø'..'Þ'
    private val latin1SuppLowerCase: CharRange = 'ß'..'ö'
    private val latin1SuppLowerCase2: CharRange = 'ø'..'ÿ'
    private val europeanLatinAndExtended: CharRange = 'Ā'..'ɏ'
    private val latinExtendedAdditional: CharRange = 'Ḁ'..'ỿ' //https://en.wikipedia.org/wiki/Latin_Extended_Additional
    private val greekCoptic: CharRange = 'Ͱ'..'Ͽ'
    private val geekExtended: CharRange = 'ἀ'..'῾'
    private val cyrillic: CharRange = 'Ѐ'..'ӿ'

    //https://en.wikipedia.org/wiki/Latin_Extended-C
    private val latinExtendedCBlock: CharRange = 'Ⱡ'..'Ɀ'

    //https://en.wikipedia.org/wiki/Latin_Extended-D
    private val latinExtendedDBlock: CharRange = '꜠'..'ꟿ'

    //https://en.wikipedia.org/wiki/Latin_Extended-E
    private val latinExtendedEBlock: CharRange = 'ꬰ'..'꭫'
////https://en.wikipedia.org/wiki/Latin_Extended-F
//internal val latinExtendedFBlock: CharRange = '𐞀' .. '𐞺'
////https://en.wikipedia.org/wiki/Latin_Extended-G
//internal val latinExtendedGBlock: CharRange = '𝼀' .. '𝼪'

    private val ipaExtensions: CharRange = 'ɐ'..'ʯ'

    internal fun isCharInTable(char: Char): Boolean {
        return char in latinLowerCase ||
                char in latinUpperCase ||
                char in latin1SuppUpperCase ||
                char in latin1SuppUpperCase2 ||
                char in latin1SuppLowerCase ||
                char in latin1SuppLowerCase2 ||
                char in europeanLatinAndExtended ||
                char in latinExtendedAdditional ||
                char in greekCoptic ||
                char in geekExtended ||
                char in cyrillic ||
                char in latinExtendedCBlock ||
                char in latinExtendedDBlock ||
                char in latinExtendedEBlock ||
//            char in latinExtendedFBlock||
//            char in latinExtendedGBlock||
                char in ipaExtensions
    }
}