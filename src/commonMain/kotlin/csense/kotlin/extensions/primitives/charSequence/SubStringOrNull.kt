@file:Suppress("NOTHING_TO_INLINE")

package csense.kotlin.extensions.primitives.charSequence

import csense.kotlin.extensions.collections.generic.collectionBounds.operations.*


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
