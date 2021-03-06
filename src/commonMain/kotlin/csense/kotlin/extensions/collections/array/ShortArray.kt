package csense.kotlin.extensions.collections.array

import csense.kotlin.*
import csense.kotlin.extensions.collections.array.generic.*
import csense.kotlin.extensions.collections.generic.*


/**
 * A foreach, but not taking any result for the given receiver
 *
 * @receiver [ShortArray]
 * @param receiver [Function1]<[Short], U>
 */
public inline fun <U> ShortArray.forEachDiscard(receiver: Function1<Short, U>): Unit =
    GenericArray.foreachDiscardResult(count(), this::get, receiver)


//region Generic collection extensions
/**
 * Performs traversal in pairs of 2  (with the first index as well)
 */
@Deprecated("will be removed")
public inline fun ShortArray.forEach2Indexed(action: Function2IndexedUnit<Short, Short>): Unit =
    GenericCollectionExtensions.forEach2Indexed(count(), ::elementAt, action)

/**
 * Performs traversal in pairs of 2
 */
@Deprecated("will be removed")
public inline fun ShortArray.forEach2(action: Function2Unit<Short, Short>): Unit =
    GenericCollectionExtensions.forEach2(count(), ::elementAt, action)

/**
 * Performs backwards traversal on this [ShortArray].
 */
public inline fun ShortArray.forEachBackwards(action: FunctionUnit<Short>): Unit =
    GenericCollectionExtensions.forEachBackwards(count(), this::elementAt, action)
//endregion
