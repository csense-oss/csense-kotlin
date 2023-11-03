@file:Suppress("NOTHING_TO_INLINE")

package csense.kotlin.extensions.primitives.charSequence

import csense.kotlin.annotations.numbers.*
import csense.kotlin.extensions.primitives.int.*


/**
 *
 * @param char [Char] the character we are searching for
 * @param startIndex [Int] The index of character to start searching at. The search proceeds backward toward the beginning of the string.
 * @param ignoreCase [Boolean] `true` to ignore character case when matching a string. By default `false`.
 * @return [Int]? null if the given character was not found or first found index of it.
 */
@IntLimit(from = 0)
public inline fun CharSequence.indexOfOrNull(
    char: Char,
    @IntLimit(from = 0) startIndex: Int = 0,
    ignoreCase: Boolean = false
): Int? {
    return indexOf(
        char = char,
        startIndex = startIndex,
        ignoreCase = ignoreCase
    ).indexOfExtensions.unwrapUnsafeIndexOf()
}

/**
 *
 * @param string [String] the character we are searching for
 * @param startIndex [Int] The index of character to start searching at. The search proceeds backward toward the beginning of the string.
 * @param ignoreCase [Boolean] `true` to ignore character case when matching a string. By default `false`.
 * @return [Int]? null if the given string was not found or first found index of it.
 */
@IntLimit(from = 0)
public inline fun CharSequence.indexOfOrNull(
    string: String,
    @IntLimit(from = 0) startIndex: Int = 0,
    ignoreCase: Boolean = false
): Int? {
    return indexOf(
        string = string,
        startIndex = startIndex,
        ignoreCase = ignoreCase
    ).indexOfExtensions.unwrapUnsafeIndexOf()
}
