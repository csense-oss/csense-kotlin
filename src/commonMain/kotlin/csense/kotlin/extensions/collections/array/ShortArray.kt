package csense.kotlin.extensions.collections.array

import csense.kotlin.Function1
import csense.kotlin.FunctionUnit
import csense.kotlin.extensions.collections.array.generic.GenericArray
import csense.kotlin.extensions.collections.array.generic.foreachDiscardResult
import csense.kotlin.extensions.collections.generic.*


/**
 * A foreach, but not taking any result for the given receiver
 *
 * @receiver [ShortArray]
 * @param receiver [Function1]<[Short], U>
 */
inline fun <U> ShortArray.forEachDiscard(receiver: Function1<Short, U>): Unit =
        GenericArray.foreachDiscardResult(count(), this::get, receiver)


//region Generic collection extensions
/**
 * Performs traversal in pairs of 2  (with the first index as well)
 */
inline fun ShortArray.forEach2Indexed(action: Function2IndexedUnit<Short, Short>): Unit =
        GenericCollectionExtensions.forEach2Indexed(count(), ::elementAt, action)

/**
 * Performs traversal in pairs of 2
 */
inline fun ShortArray.forEach2(action: Function2Unit<Short, Short>): Unit =
        GenericCollectionExtensions.forEach2(count(), ::elementAt, action)

/**
 * Performs backwards traversal on this [ShortArray].
 */
inline fun ShortArray.forEachBackwards(action: FunctionUnit<Short>): Unit =
        GenericCollectionExtensions.forEachBackwards(count(), this::elementAt, action)
//endregion
