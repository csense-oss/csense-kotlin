package csense.kotlin.extensions.collections.array

import csense.kotlin.*
import csense.kotlin.extensions.collections.array.generic.*
import csense.kotlin.extensions.collections.generic.*


/**
 * A foreach, but not taking any result for the given receiver
 * @receiver Array<T>
 * @param receiver (T) -> U
 */
inline fun <U> BooleanArray.forEachDiscard(receiver: Function1<Boolean, U>) =
        ForeachDiscardResult(count(), this::get, receiver)

/**
 *
 * @receiver BooleanArray
 * @param value Boolean
 */
fun BooleanArray.fill(value: Boolean) =
        fillArray(count(), value, this::set)

//region Generic collection extensions
/**
 * Performs traversal in pairs of 2  (with the first index as well)
 */
inline fun BooleanArray.forEach2Indexed(action: Function2IndexedUnit<Boolean, Boolean>) =
        GenericCollectionExtensions.forEach2Indexed(count(), ::elementAt, action)

/**
 * Performs traversal in pairs of 2
 */
inline fun BooleanArray.forEach2(action: Function2Unit<Boolean, Boolean>) =
        GenericCollectionExtensions.forEach2(count(), ::elementAt, action)

/**
 * Performs backwards traversal on this list.
 */
inline fun BooleanArray.forEachBackwards(action: FunctionUnit<Boolean>) =
        GenericCollectionExtensions.forEachBackwards(count(), this::elementAt, action)
//endregion
