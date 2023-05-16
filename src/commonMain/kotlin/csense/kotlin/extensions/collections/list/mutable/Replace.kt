@file:Suppress("unused", "NOTHING_TO_INLINE", "INVISIBLE_MEMBER", "INVISIBLE_REFERENCE")

package csense.kotlin.extensions.collections.list.mutable

import csense.kotlin.annotations.numbers.*
import csense.kotlin.extensions.collections.*
import csense.kotlin.extensions.collections.collection.*
import csense.kotlin.extensions.collections.generic.collectionBounds.operations.*


/**
 * replaces an item at the given position. iff the position is valid ofc.
 * @receiver [MutableList]<T>
 * @param item T
 * @param position [Int]
 */
public inline fun <@kotlin.internal.OnlyInputTypes T> MutableList<T>.replace(
    item: T,
    @IntLimit(from = 0) position: Int
) {
    if (isIndex.inBoundsEndNotInBounds(position)) {
        add(index = position, element = item)
        removeAt(index = position + 1) //the +1 : we just moved all content before the original position.
    }
}

/**
 * Replaces the given "toReplace" with the "WithItem" (if "toReplace" is found in this collection)
 * @receiver [MutableList]<T>
 * @param toReplace T
 * @param withItem T
 */
public inline fun <@kotlin.internal.OnlyInputTypes T> MutableList<T>.replace(toReplace: T, withItem: T): Unit =
    replace(item = withItem, position = indexOf(toReplace))

/**
 * Searches for the first element matching the given [predicate] with [replaceWith] or nothing if none matches the [predicate]
 * @receiver [MutableList]<T>
 * @param predicate [Function1]<T, Boolean>
 * @param replaceWith T what to replace the given element with (at the same place)
 * @return [Boolean] true if there was anything matching the [predicate]  or false if none did
 */
public inline fun <@kotlin.internal.OnlyInputTypes reified T> MutableList<T>.replaceFirst(
    replaceWith: T,
    predicate: Predicate<T>
): Boolean {
    val index = indexOfFirstOrNull(predicate) ?: return false
    replace(replaceWith, index)
    return true
}