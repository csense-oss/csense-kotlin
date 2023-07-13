@file:Suppress("NOTHING_TO_INLINE")
package csense.kotlin.extensions.collections.list.mutable

import csense.kotlin.annotations.numbers.*
import csense.kotlin.extensions.collections.generic.collectionBounds.operations.*
import csense.kotlin.extensions.primitives.int.*


/**
 * returns true iff all could be removed
 * @receiver [MutableList]<T>
 * @param intRange [IntRange]
 * @return [Boolean]
 */
public inline fun <T> MutableList<T>.removeAll(intRange: IntRange): Boolean {
    //skip negative ranges and ranges that ends on 0.
    // and Ranges that are not inverted ( last is less than first)
    if (intRange.first.isNegative || intRange.last.isNegative || intRange.last < intRange.first) {
        return false
    }
    //if we are larger than size we are out of bounds
    if (intRange.first >= size || intRange.last >= size) {
        return false
    }
    for (i: Int in intRange) {
        this.removeAt(intRange.first)
    }
    return true
}

/**
 * remove an item at a given place and returns that or if not possible returns the given value (or)
 * @receiver [MutableList]<T>
 * @param index [Int]
 * @param default T?
 * @return T?
 */
public inline fun <T> MutableList<T>.removeAtOr(
    @IntLimit(from = 0) index: Int,
    default: T?
): T? = when {
    isIndex.inBoundsEndNotInBounds(index) -> removeAt(index)
    else -> default
}


/**
 * Removes the first entry in this list and returns it (or null if no elements exists)
 * @receiver [MutableList]<T>
 * @return T?
 */
public inline fun <T> MutableList<T>.removeFirstOrNull(): T? =
    removeAtOr(index = 0, default = null)

