package csense.kotlin.extensions.collections

import csense.kotlin.*

/**
 * Loops through the iterator and returns the first element matching the given predicate (and then breaks)
 * @receiver [Iterator]<T>
 * @param predicate [Function1]<T, Boolean>
 * @return T?
 */
public inline fun <T> Iterator<T>.findFirst(predicate: Function1<T, Boolean>): T? {
    for (item in this) {
        if (predicate(item)) {
            return item
        }
    }
    return null
}