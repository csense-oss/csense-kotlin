package csense.kotlin.extensions.collections.list.mutable

import csense.kotlin.annotations.numbers.*
import csense.kotlin.extensions.collections.generic.collectionBounds.operations.*


/**
 * Adds all the given elements starting at the given index
 * if the index is out of bounds the function returns false
 * @receiver [MutableList]<T> the list to add the given elements to
 * @param index [Int] the index to insert at (must be in between 0 to (inc) size of the receiver)
 * @param elements [Iterable]<T> the elements to insert
 * @return [Boolean] true if the add was a success, false if out of bounds
 * @timecomplexity O(n)
 */

public inline fun <T> MutableList<T>.addAll(
    @IntLimit(from = 0) index: Int,
    elements: Iterable<T>
): Boolean {
    if (isIndex.outOfBoundsEndInBounds(index)) {
        return false
    }
    elements.forEachIndexed { counter: Int, element: T ->
        add(counter + index, element)
    }
    return true
}
