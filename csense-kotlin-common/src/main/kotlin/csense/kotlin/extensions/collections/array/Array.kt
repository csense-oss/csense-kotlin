@file:Suppress("unused", "NOTHING_TO_INLINE")

package csense.kotlin.extensions.collections.array

/**
 * A foreach, but not taking any result for the given receiver
 * @receiver Array<T>
 * @param receiver (T) -> U
 */
inline fun <T, U> Array<T>.forEachDiscard(crossinline receiver: Function1<T, U>) =
        ForeachDiscardResult(count(), this::get, receiver)

/**
 * Fills this array with the given value
 * @receiver Array<T> the array to fill
 * @param value T with this value
 */
fun <T> Array<T>.fill(value: T) =
        fillArray(count(), value, this::set)