@file:Suppress("NOTHING_TO_INLINE")
package csense.kotlin.extensions.collections.list

import csense.kotlin.annotations.numbers.*
import csense.kotlin.extensions.collections.generic.collectionBounds.operations.*


/**
 * Creates a sublist with the given range (including the end)
 * Checks bounds before accessing. returns empty list if out of bounds.
 * @receiver [List]<T>
 * @param intRange [IntRange]
 * @return [List]<T>
 */
public inline fun <T> List<T>.subList(intRange: IntRange): List<T> =
    subListSafe(intRange.first, intRange.last)

/**
 * Creates a sublist from the given [fromIndex] to the end
 * Checks bounds before accessing. returns empty list if out of bounds.
 * @receiver [List]<T>
 * @param fromIndex [Int] where from we should start including elements
 * @return [List]<T>
 */
public inline fun <T> List<T>.subList(
    @IntLimit(from = 0) fromIndex: Int
): List<T> = subListSafe(fromIndex, size)

/**
 * Extracts a sublist or returns an empty list if the requested range is out of bounds.
 * This is a safe alternative to the standard library edition that goes out of bounds/throws exceptions.
 * @receiver [List]<T>
 * @param fromIndex [Int] (inclusive)  [0;size[
 * @param toIndex [Int] (exclusive)    ]fromIndex;size[
 * @return [List]<T>
 */
public inline fun <T> List<T>.subListSafe(
    @IntLimit(from = 0) fromIndex: Int,
    @IntLimit(from = 0) toIndex: Int = size
): List<T> {
    val isAllInBounds: Boolean = isIndex.inBounds(fromIndex, isEndInBounds = false) &&
            isIndex.inBounds(toIndex, isEndInBounds = true) &&
            fromIndex <= toIndex
    return if (isAllInBounds) {
        subList(fromIndex, toIndex)
    } else {
        emptyList()
    }
}

