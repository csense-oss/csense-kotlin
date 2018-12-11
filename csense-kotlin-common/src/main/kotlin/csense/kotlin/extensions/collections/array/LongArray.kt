package csense.kotlin.extensions.collections.array

import csense.kotlin.*
import csense.kotlin.extensions.collections.array.generic.*
import csense.kotlin.extensions.collections.generic.*


/**
 * A foreach, but not taking any result for the given receiver
 * @receiver
 * @param receiver (T) -> U
 */
inline fun <U> LongArray.forEachDiscard(crossinline receiver: Function1<Long, U>) =
        ForeachDiscardResult(count(), this::get, receiver)

/**
 * Fills this array with the given value
 */
fun LongArray.fill(value: Long) =
        fillArray(count(), value, this::set)


//region Generic collection extensions
/**
 * Performs traversal in pairs of 2  (with the first index as well)
 */
inline fun LongArray.forEach2Indexed(action: Function2IndexedUnit<Long, Long>) =
        GenericCollectionExtensions.forEach2Indexed(count(), ::elementAt, action)

/**
 * Performs traversal in pairs of 2
 */
inline fun LongArray.forEach2(action: Function2Unit<Long, Long>) =
        GenericCollectionExtensions.forEach2(count(), ::elementAt, action)

/**
 * Performs backwards traversal on this list.
 */
inline fun LongArray.forEachBackwards(action: FunctionUnit<Long>) =
        GenericCollectionExtensions.forEachBackwards(count(), this::elementAt, action)
//endregion
