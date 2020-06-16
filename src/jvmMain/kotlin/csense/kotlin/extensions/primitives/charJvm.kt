package csense.kotlin.extensions.primitives

/**
 * Tells if this char is uppercase
 *
 */
actual fun Char.isUpperCaseLetter(): Boolean {
    if (this.isWhitespace() || this.isDigit()) {
        return false
    }
    return Character.isUpperCase(this)
}

/**
 * Tells if this char is lowercase
 */
actual fun Char.isLowerCaseLetter(): Boolean {
    if (this.isWhitespace() || this.isDigit()) {
        return false
    }
    return Character.isLowerCase(this)
}