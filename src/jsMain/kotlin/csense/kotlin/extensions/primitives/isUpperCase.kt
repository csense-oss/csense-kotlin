package csense.kotlin.extensions.primitives

/**
 * tells if this char is uppercase
// * space is lowercase always
// * numbers are always lowercase
 */
actual val Char.isUpperCaseLetter: Boolean
    get() {

        return toUpperCase().equals(this, false)
    }

/**
 * tells if this char is lowercase
// * space is lowercase always
// * numbers are always lowercase
 */
actual val Char.isLowerCaseLetter: Boolean
    get() {
//        if (this.isWhitespace()) {
//            return true
//        }
        return toLowerCase().equals(this, false)
    }