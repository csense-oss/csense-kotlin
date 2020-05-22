@file:Suppress("NOTHING_TO_INLINE", "unused")

package csense.kotlin.extensions.primitives

import csense.kotlin.annotations.numbers.*

/**
 * is this NOT null or blank, akk its not null nor a "blank" charsequence
 * @return Boolean true if this is not null and it is not blank , false otherwise
 */
inline fun CharSequence?.isNotNullOrBlank(): Boolean {
    return this != null && this.isNotBlank()
}

/**
 * is this NOT null or empty, akk its not null nor an empty charsequence (length = 0)
 * it can be a single space though (which will yield true)
 * @return Boolean true if this is not null and it is not empty (length =0) false otherwise
 */
inline fun CharSequence?.isNotNullOrEmpty(): Boolean {
    return this != null && this.isNotEmpty()
}


//region indexOfOrNull
/**
 *
 * @param char Char the charater we are searching for
 * @param startIndex The index of character to start searching at. The search proceeds backward toward the beginning of the string.
 * @param ignoreCase `true` to ignore character case when matching a string. By default `false`.
 * @return Int? null if the given character was not found or first found index of it.
 */
@IntLimit(from = 0)
inline fun CharSequence.indexOfOrNull(char: Char, startIndex: Int = 0, ignoreCase: Boolean = false): Int? {
    return when (val index = indexOf(char, startIndex, ignoreCase)) {
        -1 -> null
        else -> index
    }
}

/**
 *
 * @param string String the character we are searching for
 * @param startIndex The index of character to start searching at. The search proceeds backward toward the beginning of the string.
 * @param ignoreCase `true` to ignore character case when matching a string. By default `false`.
 * @return Int? null if the given string was not found or first found index of it.
 */
@IntLimit(from = 0)
inline fun CharSequence.indexOfOrNull(string: String, startIndex: Int = 0, ignoreCase: Boolean = false): Int? {
    return when (val index = indexOf(string, startIndex, ignoreCase)) {
        -1 -> null
        else -> index
    }
}


/**
 *
 * @param char Char the character to search for
 * @param startIndex The index of character to start searching at. The search proceeds backward toward the beginning of the string.
 * @param ignoreCase `true` to ignore character case when matching a string. By default `false`.
 * @return Int? null if the given character was not found or the latest index of it.
 */
@IntLimit(from = 0)
inline fun CharSequence.lastIndexOfOrNull(char: Char, startIndex: Int = lastIndex, ignoreCase: Boolean = false): Int? {
    return when (val index = lastIndexOf(char, startIndex, ignoreCase)) {
        -1 -> null
        else -> index
    }
}

/**
 *
 * @param string String the string to search for
 * @param startIndex The index of character to start searching at. The search proceeds backward toward the beginning of the string.
 * @param ignoreCase `true` to ignore character case when matching a string. By default `false`.
 * @return Int? null if the given string was not found or the latest index of it.
 */
@IntLimit(from = 0)
inline fun CharSequence.lastIndexOfOrNull(string: String, startIndex: Int = lastIndex, ignoreCase: Boolean = false): Int? {
    return when (val index = lastIndexOf(string, startIndex, ignoreCase)) {
        -1 -> null
        else -> index
    }
}

//endregion
