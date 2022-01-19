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
 * Performs backwards traversal on this [ShortArray].
 */
public inline fun ShortArray.forEachBackwards(action: FunctionUnit<Short>): Unit =
    GenericCollectionExtensions.forEachBackwards(count(), this::elementAt, action)
//endregion
