@file:Suppress("unused", "NOTHING_TO_INLINE")

package csense.kotlin.extensions.collections.iterator

import csense.kotlin.extensions.collections.*


/**
 * Loops through the iterator and returns the first element matching the given predicate (and then breaks)
 * @receiver [Iterator]<T>
 * @param predicate [Function1]<T, Boolean>
 * @return T? the item first matching the given [predicate] or null if non did match the [predicate]
 */
public inline fun <T> Iterator<T>.findFirst(predicate: Predicate<T>): T? {
    for (item in this) {
        if (predicate(item)) {
            return item
        }
    }
    return null
}
