package csense.kotlin.extensions.primitives

/**
 * Tells if this char is uppercase
 * @receiver [Char]
 * @return [Boolean]
 */
actual fun Char.isUpperCaseLetter(): Boolean {
    if (this.isWhitespace() || this.isDigit()) {
        return false
    }
    return Character.isUpperCase(this)
}

/**
 * Tells if this char is lowercase
 * @receiver [Char]
 * @return [Boolean]
 */
actual fun Char.isLowerCaseLetter(): Boolean {
    if (this.isWhitespace() || this.isDigit()) {
        return false
    }
    return Character.isLowerCase(this)
}