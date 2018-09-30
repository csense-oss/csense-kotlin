@file:Suppress("unused", "NOTHING_TO_INLINE")
package csense.kotlin.extensions.collections.array


inline fun <T, U> Array<T>.forEachDiscard(receiver: (T) -> U) {
    forEach {
        receiver(it)
    }
}

/**
 * Fills this array with the given value
 */
fun <T> Array<T>.fill(value: T) =
        fillArray(count(), value, this::set)