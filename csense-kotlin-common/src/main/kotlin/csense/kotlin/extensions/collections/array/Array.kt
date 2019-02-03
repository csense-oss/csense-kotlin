@file:Suppress("unused", "NOTHING_TO_INLINE")

package csense.kotlin.extensions.collections.array

import csense.kotlin.*
import csense.kotlin.extensions.collections.array.generic.*
import csense.kotlin.extensions.collections.generic.*

/**
 * A foreach, but not taking any result for the given receiver
 * @receiver Array<T>
 * @param receiver (T) -> U
 */
inline fun <T, U> Array<T>.forEachDiscard(receiver: Function1<T, U>) =
        ForeachDiscardResult(count(), this::get, receiver)

/**
 * Fills this array with the given value
 * @receiver Array<T> the array to fill
 * @param value T with this value
 */
fun <T> Array<T>.fill(value: T) =
        fillArray(count(), value, this::set)


//region Generic collection extensions
/**
 * Performs traversal in pairs of 2  (with the first index as well)
 */
inline fun <T> Array<T>.forEach2Indexed(action: Function2IndexedUnit<T, T>) =
        GenericCollectionExtensions.forEach2Indexed(count(), ::elementAt, action)

/**
 * Performs traversal in pairs of 2
 */
inline fun <T> Array<T>.forEach2(action: Function2Unit<T, T>) =
        GenericCollectionExtensions.forEach2(count(), ::elementAt, action)

/**
 * Performs backwards traversal on this list.
 */
inline fun <T> Array<T>.forEachBackwards(action: FunctionUnit<T>) =
        GenericCollectionExtensions.forEachBackwards(count(), this::elementAt, action)
//endregion
