@file:Suppress("unused", "NOTHING_TO_INLINE", "INVISIBLE_MEMBER", "INVISIBLE_REFERENCE")

package csense.kotlin.extensions.primitives

import csense.kotlin.Function1
import csense.kotlin.annotations.numbers.*

/**
 * is this NOT null or blank, akk its not null nor a "blank" [CharSequence]
 * @return [Boolean] true if this is not null and it is not blank , false otherwise
 */
public inline fun CharSequence?.isNotNullOrBlank(): Boolean {
    return this != null && this.isNotBlank()
}

/**
 * is this NOT null or empty, akk its not null nor an empty [CharSequence] (length = 0)
 *
 * it can be a single space though (which will yield true)
 * @return [Boolean] true if this is not null and it is not empty (length =0) false otherwise
 */
public inline fun CharSequence?.isNotNullOrEmpty(): Boolean {
    return this != null && this.isNotEmpty()
}


//region indexOfOrNull
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
    return when (val index = indexOf(char, startIndex, ignoreCase)) {
        -1 -> null
        else -> index
    }
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
    return when (val index = indexOf(string, startIndex, ignoreCase)) {
        -1 -> null
        else -> index
    }
}


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

//endregion


@kotlin.internal.LowPriorityInOverloadResolution
public inline fun CharSequence.equals(other: CharSequence, ignoreCase: Boolean = false): Boolean {
    if (length != other.length) {
        return false
    }
    forEachIndexed { index, char ->
        val otherChar = other[index]
        if (!otherChar.equals(char, ignoreCase)) {
            return@equals false
        }
    }
    return true
}

@kotlin.internal.LowPriorityInOverloadResolution
public inline fun CharSequence.notEquals(other: CharSequence, ignoreCase: Boolean = false): Boolean =
    !equals(other, ignoreCase)

/**
 * Returns the [substring] from the given [startIndex]
 * @receiver [CharSequence]
 * @param [startIndex] [Int]
 * @return [String]? null if [startIndex] is invalid otherwise the substring
 */
public inline fun CharSequence.substringFromOrNull(startIndex: Int): String? {
    if (startIndex >= length || startIndex.isNegative) {
        return null
    }
    return substring(startIndex, length)
}

/**
 * Splits this [CharSequence] by the given Chars
 * @receiver [CharSequence]
 * @param delimiters [Set]<[Char]>
 * @return [List]<[String]>
 */
public inline fun CharSequence.split(delimiters: Set<Char>): List<String> = splitBy {
    it in delimiters
}

/**
 * Splits this [CharSequence] by the given [shouldSplit]
 * @receiver [CharSequence]
 * @param shouldSplit [Function1]<[Char], [Boolean]>
 * @return [List]<[String]>
 */
public inline fun CharSequence.splitBy(shouldSplit: Function1<Char, Boolean>): List<String> {
    val isIndexNotAtEnd = { index: Int ->
        index < length
    }
    val results = mutableListOf<String>()
    var currentStartIndex = 0
    forEachIndexed { index, char ->
        if (shouldSplit(char)) {
            val splitLength = index - currentStartIndex
            if (splitLength > 0) {
                results += substring(startIndex = currentStartIndex, endIndex = index)
            }
            currentStartIndex = index + 1 // +1 since we are "taking" the "char" that gets "split"
        }
    }
    if (isIndexNotAtEnd(currentStartIndex)) {
        results += substring(startIndex = currentStartIndex)
    }

    return results
}