package csense.kotlin.extensions.primitives

/**
 * Tells if this char is uppercase
 *
 */
actual val Char.isUpperCaseLetter: Boolean
    get() {
        if (this.isWhitespace() || this.isDigit) {
            return false
        }
        return Character.isUpperCase(this)
    }

/**
 * Tells if this char is lowercase
 */
actual val Char.isLowerCaseLetter: Boolean
    get() {
        if (this.isWhitespace() || this.isDigit) {
            return false
        }
        return Character.isLowerCase(this)
    }