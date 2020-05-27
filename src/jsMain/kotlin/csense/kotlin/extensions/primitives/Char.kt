package csense.kotlin.extensions.primitives

/**
 * Is this char a number ?
 * @receiver Char
 * @return Boolean true if it is in 0 until 9, false otherwise
 */
fun Char.isNumber(): Boolean = this in '0'..'9'