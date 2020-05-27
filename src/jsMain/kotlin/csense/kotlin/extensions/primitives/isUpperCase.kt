package csense.kotlin.extensions.primitives

/**
 * tells if this char is uppercase
// * space is lowercase always
// * numbers are always lowercase
 */
actual val Char.isUpperCase: Boolean
    get() {
//        if (this.isWhitespace() || this.isNumber()) {
//            return true
//        }
        return toUpperCase().equals(this, false)
    }

/**
 * tells if this char is lowercase
// * space is lowercase always
// * numbers are always lowercase
 */
actual val Char.isLowerCase: Boolean
    get() {
//        if (this.isWhitespace()) {
//            return true
//        }
        return toLowerCase().equals(this, false)
    }