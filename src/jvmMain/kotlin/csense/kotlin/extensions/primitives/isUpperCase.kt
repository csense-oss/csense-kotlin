package csense.kotlin.extensions.primitives

/**
 * Tells if this char is uppercase
 *
 */
actual val Char.isUpperCase: Boolean
    get() {
        if (this.isWhitespace() || this.isDigit) {
            return true
        }
        return Character.isUpperCase(this)
    }

/**
 * Tells if this char is lowercase
 */
actual val Char.isLowerCase: Boolean
    get() {
        if (this.isWhitespace() || this.isDigit) {
            return true
        }
        return Character.isLowerCase(this)
    }