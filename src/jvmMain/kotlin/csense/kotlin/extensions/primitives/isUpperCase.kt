package csense.kotlin.extensions.primitives

/**
 * Tells if this char is uppercase
 *
 */
actual val Char.isUpperCase: Boolean
    get() = Character.isUpperCase(this)

/**
 * Tells if this char is lowercase
 */
actual val Char.isLowerCase: Boolean
    get() = Character.isLowerCase(this)