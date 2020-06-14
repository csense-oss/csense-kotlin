package csense.kotlin.extensions.primitives

/**
 *
 *
 * @receiver Char
 * @return Boolean
 *
 * see
 * https://en.wikipedia.org/wiki/List_of_Unicode_characters
 */
inline fun Char.isSymbol(): Boolean {
    return isNotLetter() && isNotDigit()
}

//INCOMPLETE
/**
 *
 * @receiver Char
 * see
 * https://en.wikipedia.org/wiki/List_of_Unicode_characters
 */
fun Char.isLetter(): Boolean {
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

inline fun Char.isNotLetter(): Boolean =
        !isLetter()

/**
 * tells if this char is uppercase
 * space is lowercase always
 * numbers are always lowercase
 */
actual fun Char.isUpperCaseLetter(): Boolean {
    if (isNotLetter()) {
        return false
    }
    return toUpperCase().equals(this, false)
}

/**
 * tells if this char is lowercase
// * space is lowercase always
// * numbers are always lowercase
 */
actual fun Char.isLowerCaseLetter(): Boolean {
    if (isNotLetter()) {
        return false
    }
    return toLowerCase().equals(this, false)
}