package csense.kotlin.extensions.primitives

/**
 * tells if this char is uppercase
 * Sub optimal (jvm and js can do better)
 */
actual val Char.isUpperCase: Boolean
    get() = toUpperCase().equals(this, false)

/**
 * tells if this char is lowercase
 * Sub optimal (jvm and js can do better)
 */
actual val Char.isLowerCase: Boolean
    get() = toLowerCase().equals(this, false)