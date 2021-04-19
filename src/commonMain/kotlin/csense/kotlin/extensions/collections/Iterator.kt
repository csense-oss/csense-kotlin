@file:Suppress("unused", "NOTHING_TO_INLINE")

package csense.kotlin.extensions.collections

import csense.kotlin.*

/**
 * Loops through the iterator and returns the first element matching the given predicate (and then breaks)
 * @receiver [Iterator]<T>
 * @param predicate [Function1]<T, Boolean>
 * @return T? the item first matching the given [predicate] or null if non did match the [predicate]
 */
public inline fun <T> Iterator<T>.findFirst(predicate: Function1<T, Boolean>): T? {
    for (item in this) {
        if (predicate(item)) {
            return item
        }
    }
    return null
}

/**
 * Converts the current iterator to a list by iterating all of the "remaining" elements
 * @receiver Iterator<T>
 * @return List<T> a list of all the items (remaining)
 */
public inline fun <T> Iterator<T>.toList(): List<T> =
    toMutableList()

/**
 * Maps all (remaining) items of this Iterator by the given [transform] function
 * @receiver Iterator<T>
 * @param transform Function1<T, R> transforms a given item from this iterator
 * @return List<R> the transformed list
 */
public inline fun <T, R> Iterator<T>.map(
    transform: (T) -> R
): List<R> = mutableListOf<R>().also { list ->
    this.forEach {
        list += transform(it)
    }
}

/**
 * Converts the current iterator to a list by iterating all of the "remaining" elements
 * @receiver Iterator<T>
 * @return MutableList<T> a mutable list of all the items (remaining)
 */
public inline fun <T> Iterator<T>.toMutableList(

): MutableList<T> = mutableListOf<T>().also { list ->
    this.forEach {
        list += it
    }
}
