@file:Suppress("NOTHING_TO_INLINE")

package csense.kotlin.extensions.primitives.charSequence

import csense.kotlin.annotations.numbers.*


/**
 *
 * @param char [Char] the character to search for
 * @param startIndex [Int] The index of character to start searching at. The search proceeds backward toward the beginning of the string.
 * @param ignoreCase [Boolean] `true` to ignore character case when matching a string. By default `false`.
 * @return [Int]? null if the given character was not found or the latest index of it.
 */
@IntLimit(from = 0)
public inline fun CharSequence.lastIndexOfOrNull(
    char: Char,
    @IntLimit(from = 0) startIndex: Int = lastIndex,
    ignoreCase: Boolean = false
): Int? {
    return when (val index = lastIndexOf(char, startIndex, ignoreCase)) {
        -1 -> null
        else -> index
    }
}

/**
 *
 * @param string [String] the string to search for
 * @param startIndex [Int] The index of character to start searching at. The search proceeds backward toward the beginning of the string.
 * @param ignoreCase [Boolean] `true` to ignore character case when matching a string. By default `false`.
 * @return [Int]? null if the given string was not found or the latest index of it.
 */
@IntLimit(from = 0)
public inline fun CharSequence.lastIndexOfOrNull(
    string: String,
    @IntLimit(from = 0) startIndex: Int = lastIndex,
    ignoreCase: Boolean = false
): Int? {
    return when (val index = lastIndexOf(string, startIndex, ignoreCase)) {
        -1 -> null
        else -> index
    }
}
