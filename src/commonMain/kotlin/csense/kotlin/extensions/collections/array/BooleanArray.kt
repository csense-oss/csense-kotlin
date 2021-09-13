package csense.kotlin.extensions.collections.array

import csense.kotlin.*
import csense.kotlin.extensions.collections.array.generic.*
import csense.kotlin.extensions.collections.generic.*


/**
 * A foreach, but not taking any result for the given receiver
 * @receiver [BooleanArray]
 * @param receiver [Function1]<[Boolean], U>
 */
public inline fun <U> BooleanArray.forEachDiscard(receiver: Function1<Boolean, U>): Unit =
    GenericArray.foreachDiscardResult(count(), this::get, receiver)


//region Generic collection extensions
/**
 * Performs traversal in pairs of 2  (with the first index as well)
 */
@Deprecated("will be removed")
@Suppress("MissingTestFunction")
public inline fun BooleanArray.forEach2Indexed(action: Function2IndexedUnit<Boolean, Boolean>): Unit =
    GenericCollectionExtensions.forEach2Indexed(count(), ::elementAt, action)

/**
 * Performs traversal in pairs of 2
 */
@Deprecated("will be removed")
@Suppress("MissingTestFunction")
public inline fun BooleanArray.forEach2(action: Function2Unit<Boolean, Boolean>): Unit =
    GenericCollectionExtensions.forEach2(count(), ::elementAt, action)

/**
 * Performs backwards traversal on this [BooleanArray].
 */
public inline fun BooleanArray.forEachBackwards(action: FunctionUnit<Boolean>): Unit =
    GenericCollectionExtensions.forEachBackwards(count(), this::elementAt, action)
//endregion
