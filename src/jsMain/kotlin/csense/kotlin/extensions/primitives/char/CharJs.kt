@file:Suppress("unused", "NOTHING_TO_INLINE")

package csense.kotlin.extensions.primitives.char

import csense.kotlin.extensions.primitives.*

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
    return this in latinLowerCase ||
            this in latinUpperCase ||
            this in latin1SuppUpperCase ||
            this in latin1SuppUpperCase2 ||
            this in latin1SuppLowerCase ||
            this in latin1SuppLowerCase2 ||
            this in europeanLatinAndExtended ||
            this in latinExtendedAdditional ||
            this in greekCoptic ||
            this in geekExtended ||
            this in cyrillic
}

public inline fun Char.isNotLetter(): Boolean =
    !isLetter()

/**
 * tells if this [Char] is uppercase
 * space is lowercase always
 * numbers are always lowercase
 * @receiver [Char]
 * @return [Boolean]
 */
public actual inline fun Char.isUpperCaseLetter(): Boolean {
    if (isNotLetter()) {
        return false
    }
    return uppercaseChar().equals(this, false)
}

/**
 * tells if this [Char] is lowercase
 * numbers are always lowercase
 * @receiver [Char]
 * @return [Boolean]
 */
public actual inline fun Char.isLowerCaseLetter(): Boolean {
    if (isNotLetter()) {
        return false
    }
    return lowercaseChar().equals(this, false)
}