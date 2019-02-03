package csense.kotlin.extensions.collections.array

import csense.kotlin.*
import csense.kotlin.extensions.collections.array.generic.*
import csense.kotlin.extensions.collections.generic.*


/**
 * A foreach, but not taking any result for the given receiver
 * @receiver
 * @param receiver (T) -> U
 */
inline fun <U> ShortArray.forEachDiscard(receiver: Function1<Short, U>) =
        ForeachDiscardResult(count(), this::get, receiver)


/**
 * Fills this array with the given value
 */
fun ShortArray.fill(value: Short) =
        fillArray(count(), value, this::set)


//region Generic collection extensions
/**
 * Performs traversal in pairs of 2  (with the first index as well)
 */
inline fun ShortArray.forEach2Indexed(action: Function2IndexedUnit<Short, Short>) =
        GenericCollectionExtensions.forEach2Indexed(count(), ::elementAt, action)

/**
 * Performs traversal in pairs of 2
 */
inline fun ShortArray.forEach2(action: Function2Unit<Short, Short>) =
        GenericCollectionExtensions.forEach2(count(), ::elementAt, action)

/**
 * Performs backwards traversal on this list.
 */
inline fun ShortArray.forEachBackwards(action: FunctionUnit<Short>) =
        GenericCollectionExtensions.forEachBackwards(count(), this::elementAt, action)
//endregion
