@file:Suppress("unused", "NOTHING_TO_INLINE")

package csense.kotlin.extensions.collections.list

import csense.kotlin.Function1
import csense.kotlin.annotations.numbers.IntLimit
import csense.kotlin.extensions.collections.isIndexValid
import csense.kotlin.extensions.primitives.isNegative
import csense.kotlin.extensions.primitives.onTrue

/**
 * Finds and removes the first item that matches the given predicate
 * @receiver MutableList<T> the list to remove from
 * @param foundAction Function1<T, Boolean> the predicate
 */
inline fun <T> MutableList<T>.findAndRemove(crossinline foundAction: Function1<T, Boolean>) {
    val index = this.indexOfFirst(foundAction)
    isIndexValid(index).onTrue { removeAt(index) }
}

/**
 *
 * @receiver MutableList<T>
 * @param findAction Function1<T, Boolean>
 * @return List<T>
 */
inline fun <T> MutableList<T>.findAndRemoveAll(crossinline findAction: Function1<T, Boolean>): List<T> {
    val collection = this.filter(findAction)
    removeAll(collection)
    return collection
}

/**
 * replaces an item at the given position. iff the position is valid ofc.
 * @receiver MutableList<T>
 * @param item T
 * @param position Int
 */
fun <T> MutableList<T>.replace(item: T,
                               @IntLimit(from = 0) position: Int) {
    if (isIndexValid(position)) {
        add(position, item)
        removeAt(position + 1) //the +1 : we just moved all content before the original position.
    }
}

/**
 * Replaces the given "toReplace" with the "WithItem" (if "toReplace" is found in this collection)
 * @receiver MutableList<T>
 * @param toReplace T
 * @param withItem T
 */
fun <T> MutableList<T>.replace(toReplace: T, withItem: T) =
        replace(withItem, indexOf(toReplace))


/**
 * returns true iff all could be removed
 * @receiver MutableList<T>
 * @param intRange kotlin.ranges.IntRange
 * @return Boolean
 */
fun <T> MutableList<T>.removeAll(intRange: IntRange): Boolean {
    //skip negative ranges and ranges that ends on 0.
    // and Ranges that are not inverted ( last is less than first)
    if (intRange.first.isNegative || intRange.last.isNegative || intRange.last < intRange.first) {
        return false
    }
    //if we are larger than size we are out of bounds
    if (intRange.first >= size || intRange.last >= size) {
        return false
    }
    for (i in intRange) {
        this.removeAt(intRange.first)
    }
    return true
}

/**
 * remove an item at a given place and returns that or if not possible returns the given value (or)
 * @receiver MutableList<T>
 * @param index Int
 * @param default T?
 * @return T?
 */
fun <T> MutableList<T>.removeAtOr(@IntLimit(from = 0) index: Int,
                                  default: T?): T? = if (isIndexValid(index)) {
    removeAt(index)
} else {
    default
}

