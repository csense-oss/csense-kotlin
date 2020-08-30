@file:Suppress("unused", "NOTHING_TO_INLINE")

package csense.kotlin.extensions.collections.list

import csense.kotlin.*
import csense.kotlin.annotations.numbers.*
import csense.kotlin.extensions.collections.*
import csense.kotlin.extensions.primitives.*

/**
 * Finds and removes the first item that matches the given predicate
 * @receiver [MutableList]<T> the list to remove from
 * @param foundAction [Function1]<T, [Boolean]> the predicate
 */
public inline fun <T> MutableList<T>.findAndRemove(crossinline foundAction: Function1<T, Boolean>) {
    val index = this.indexOfFirst(foundAction)
    isIndexValid(index).onTrue { removeAt(index) }
}

/**
 *
 * @receiver [MutableList]<T>
 * @param findAction [Function1]<T, [Boolean]>
 * @return [List]<T>
 */
public inline fun <T> MutableList<T>.findAndRemoveAll(crossinline findAction: Function1<T, Boolean>): List<T> {
    val collection = this.filter(findAction)
    removeAll(collection)
    return collection
}

/**
 * replaces an item at the given position. iff the position is valid ofc.
 * @receiver [MutableList]<T>
 * @param item T
 * @param position [Int]
 */
public inline fun <T> MutableList<T>.replace(
        item: T,
        @IntLimit(from = 0) position: Int
) {
    if (isIndexValid(position)) {
        add(position, item)
        removeAt(position + 1) //the +1 : we just moved all content before the original position.
    }
}

/**
 * Replaces the given "toReplace" with the "WithItem" (if "toReplace" is found in this collection)
 * @receiver [MutableList]<T>
 * @param toReplace T
 * @param withItem T
 */
public inline fun <T> MutableList<T>.replace(toReplace: T, withItem: T): Unit =
        replace(withItem, indexOf(toReplace))


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
    for (i in intRange) {
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
): T? {
    return if (isIndexValid(index)) {
        removeAt(index)
    } else {
        default
    }
}


/**
 * Removes the first entry in this list and returns it (or null if no elements exists)
 * @receiver [MutableList]<T>
 * @return T?
 */
public inline fun <T> MutableList<T>.removeFirst(): T? =
        removeAtOr(0, null)

/**
 * Removes the last element of this list and returns it (or null if there are no elements)
 * @receiver [MutableList]<T>
 * @return T?
 */
public inline fun <T> MutableList<T>.removeLast(): T? =
        removeAtOr(lastIndex, null)

/**
 * Adds all the given elements starting at the given index
 * if the index is out of bounds the function returns false
 * @receiver [MutableList]<T> the list to add the given elements to
 * @param index [Int] the index to insert at (must be in [0; size] of the receiver)
 * @param elements [Iterable]<T> the elements to insert
 * @return [Boolean] true if the add was a success, false if out of bounds
 * @timecomplexity O(n)
 */

public inline fun <T> MutableList<T>.addAll(
        @IntLimit(from = 0) index: Int,
        elements: Iterable<T>
): Boolean {
    if (!isIndexValidForInsert(index)) {
        return false
    }
    elements.forEachIndexed { counter: Int, element: T ->
        add(counter + index, element)
    }
    return true
}