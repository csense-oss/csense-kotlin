@file:Suppress("unused", "NOTHING_TO_INLINE", "INVISIBLE_MEMBER", "INVISIBLE_REFERENCE")

package csense.kotlin.extensions.primitives.charSequence

import csense.kotlin.*
import csense.kotlin.annotations.numbers.*
import csense.kotlin.extensions.collections.*
import csense.kotlin.extensions.collections.generic.collection.*
import csense.kotlin.extensions.collections.generic.collection.operations.*
import csense.kotlin.extensions.collections.generic.collectionBounds.*
import csense.kotlin.extensions.primitives.char.*
import csense.kotlin.general.*

/**
 * is this NOT null or blank, akk it's not null nor a "blank" [CharSequence]
 * @return [Boolean] true if this is not null, and it is not blank , false otherwise
 */
public inline fun CharSequence?.isNotNullOrBlank(): Boolean {
    return !this.isNullOrBlank()
}

@Deprecated(
    "receiver known at compile time to not be null. should use isNotBlank instead",
    level = DeprecationLevel.ERROR
)
public inline fun CharSequence.isNotNullOrBlank(): Nothing = unexpected()

/**
 * is this NOT null or empty, akk it's not null nor an empty [CharSequence] (length = 0)
 *
 * it can be a single space though (which will yield true)
 * @return [Boolean] true if this is not null, and it is not empty (length =0) false otherwise
 */
public inline fun CharSequence?.isNotNullOrEmpty(): Boolean {
    return !this.isNullOrEmpty()
}

@Deprecated(
    "receiver known at compile time to not be null. should use isNotEmpty instead",
    level = DeprecationLevel.ERROR
)
public inline fun CharSequence.isNotNullOrEmpty(): Nothing = unexpected()

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
        if (other[index].isNotEqual(char, ignoreCase)) {
            return@equals false
        }
    }
    return true

}

@kotlin.internal.LowPriorityInOverloadResolution
public inline fun CharSequence.notEquals(other: CharSequence, ignoreCase: Boolean = false): Boolean =
    !equals(other, ignoreCase)

/**
 * Returns a substring of chars from a range of this char sequence starting at the [startIndex]
 * @param startIndex the start index (inclusive).
 * @return [String]? null if [startIndex] is out of bounds otherwise the substring
 */
public inline fun CharSequence.substringOrNull(startIndex: Int): String? {
    if (isIndex.outOfBounds(startIndex, isEndOutOfBonds = true)) {
        return null
    }
    return substring(startIndex)
}

/**
 * Returns a substring of chars from a range of this char sequence starting at the [startIndex] and ending right before the [endIndex].
 * if out of bounds or [endIndex] is less than or equal to [startIndex] null is returned
 * @param startIndex the start index (inclusive).
 * @param endIndex the end index (exclusive). If not specified, the length of the char sequence is used.
 * @return null if out of bounds or [endIndex] is before or equal to [startIndex] otherwise the substring
 */
public inline fun CharSequence.substringOrNull(startIndex: Int, endIndex: Int = length): String? {
    val isOutOfBoundsStart = isIndex.outOfBounds(startIndex, isEndOutOfBonds = true)
    val isOutOfBoundsEnd = isIndex.outOfBounds(endIndex, isEndOutOfBonds = false) || endIndex <= startIndex
    if (isOutOfBoundsStart || isOutOfBoundsEnd) {
        return null
    }
    return substring(startIndex = startIndex, endIndex = endIndex)
}

/**
 * Returns a substring of chars at indices from the specified [range] of this char sequence.
 * if the range is out of bounds (or [IntRange.last] is before [IntRange.first] of [range] ) then null is returned
 * @param range [IntRange]
 * @return [String]? returns null if out of bounds or range is reversed, otherwise returns the substring given the [range]
 */
public inline fun CharSequence.substringOrNull(range: IntRange): String? {
    return substringOrNull(range.first, range.last + 1)
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

    if (isIndex.inBounds(currentStartIndex, isEndInBounds = false)) {
        results += substring(startIndex = currentStartIndex)
    }

    return results
}

public inline fun CharSequence.indexOfFirstOrNull(
    predicate: Predicate<Char>
): Int? = indexOfFirstIndexedOrNull { _, char ->
    predicate(char)
}

public inline fun CharSequence.indexOfFirstIndexedOrNull(predicate: Function2<Int, Char, Boolean>): Int? {
    forEachIndexed { index, char ->
        if (predicate(index, char)) {
            return@indexOfFirstIndexedOrNull index
        }
    }
    return null
}

public inline fun CharSequence.indexOfLastOrNull(
    predicate: Predicate<Char>
): Int? = indexOfLastIndexedOrNull { _, char ->
    predicate(char)
}

public inline fun CharSequence.indexOfLastIndexedOrNull(predicate: Function2<Int, Char, Boolean>): Int? {
    GenericCollections.forEachBackwardsIndexed(length = length, getter = this::get) { index, char ->
        if (predicate(index, char)) {
            return@indexOfLastIndexedOrNull index
        }
    }
    return null
}