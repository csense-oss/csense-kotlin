package csense.kotlin.extensions.collections.list

import csense.kotlin.Function1
import csense.kotlin.extensions.collections.isIndexValid
import csense.kotlin.extensions.primitives.onTrue


inline fun <T> MutableList<T>.findAndRemove(crossinline foundAction: Function1<T, Boolean>) {
    val index = this.indexOfFirst(foundAction)
    isIndexValid(index).onTrue { removeAt(index) }
}


inline fun <T> MutableList<T>.findAndRemoveAll(crossinline findAction: Function1<T, Boolean>): List<T> {
    val collection = this.filter(findAction)
    removeAll(collection)
    return collection
}


fun <T> MutableList<T>.replace(item: T, position: Int) {
    if (isIndexValid(position)) {
        add(position, item)
        removeAt(position + 1) //the +1 : we just moved all content before the original position.
    }
}

/**
 * returns true iff all could be removed
 */
fun <T> MutableList<T>.removeAll(intRange: kotlin.ranges.IntRange): Boolean {
    if (intRange.start >= size || intRange.endInclusive >= size) {
        return false
    }
    intRange.forEach { this.removeAt(intRange.start) }
    return true
}


fun <T> MutableList<T>.removeAtOr(index: Int, default: T?): T? = if (isIndexValid(index)) {
    removeAt(index)
} else {
    default
}


/**
 * Clears the collection and add's the given collection
 */
fun <E> MutableCollection<E>.set(collection: Collection<E>) {
    clear()
    addAll(collection)
}

/**
 * Clears the collection and add's the given element
 */
fun <E> MutableCollection<E>.set(item: E) : Unit {
    clear()
    add(item)
}
