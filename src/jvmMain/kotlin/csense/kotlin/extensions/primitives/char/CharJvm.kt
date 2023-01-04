@file:Suppress("NOTHING_TO_INLINE")

package csense.kotlin.extensions.primitives.char

import kotlin.text.isDigit

/**
 * Tells if this char is uppercase
 * @receiver [Char]
 * @return [Boolean]
 */
public actual inline fun Char.isUpperCaseLetter(): Boolean = when {
    isWhitespace() || isDigit() -> false
    else -> Character.isUpperCase(this)
}

/**
 * Tells if this char is lowercase
 * @receiver [Char]
 * @return [Boolean]
 */
public actual inline fun Char.isLowerCaseLetter(): Boolean = when {
    isWhitespace() || isDigit() -> false
    else -> Character.isLowerCase(this)
}