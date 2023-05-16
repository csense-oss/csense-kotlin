package csense.kotlin.extensions.collections.list.mutable

import csense.kotlin.extensions.collections.generic.collectionBounds.operations.*
import csense.kotlin.extensions.primitives.boolean.*


/**
 * Finds and removes the first item that matches the given predicate
 * @receiver [MutableList]<T> the list to remove from
 * @param foundAction [Function1]<T, [Boolean]> the predicate
 */
public inline fun <T> MutableList<T>.findAndRemove(crossinline foundAction: Function1<T, Boolean>) {
    val index = this.indexOfFirst(foundAction)
    isIndex.inBoundsEndNotInBounds(index).onTrue { removeAt(index) }
}

/**
 *
 * @receiver [MutableList]<T>
 * @param findAction [Function1]<T, [Boolean]>
 * @return [List]<T>
 */
public inline fun <T> MutableList<T>.findAndRemoveAll(crossinline findAction: Function1<T, Boolean>): List<T> {
    val collection = this.filter(findAction)
    @Suppress("ConvertArgumentToSet")
    removeAll(collection)
    return collection
}
