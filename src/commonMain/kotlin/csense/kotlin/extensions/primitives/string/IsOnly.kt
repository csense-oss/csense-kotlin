@file:Suppress("NOTHING_TO_INLINE")

package csense.kotlin.extensions.primitives.string

import csense.kotlin.extensions.primitives.char.*
import kotlin.text.isDigit


/**
 * Tells if this [String] solely consists of uppercase characters
 * @receiver [String]
 * @return [Boolean] true if all chars are upper case (if empty, returns false)
 * @timecomplexity O(n)
 */
public inline fun String.isOnlyUpperCaseLetters(
    ignoreNoneLetters: Boolean = false
): Boolean = when {
    isEmpty() -> false
    ignoreNoneLetters -> none(Char::isLowerCaseLetter)
    else -> all(Char::isUpperCaseLetter)
}


/**
 * Tells if this [String] solely consists of lowercase characters
 * @receiver [String]
 * @return [Boolean] true if either the string is empty or all chars are lowercase
 * @timecomplexity O(n)
 */
public inline fun String.isOnlyLowerCaseLetters(
    ignoreNoneLetters: Boolean = false
): Boolean = when {
    isEmpty() -> false
    ignoreNoneLetters -> none(Char::isUpperCaseLetter)
    else -> all(Char::isLowerCaseLetter)
}

/**
 *
 * @receiver String
 * @return Boolean
 */
public inline fun String.isOnlyDigits(): Boolean = when {
    isEmpty() -> false
    else -> all(Char::isDigit)
}
