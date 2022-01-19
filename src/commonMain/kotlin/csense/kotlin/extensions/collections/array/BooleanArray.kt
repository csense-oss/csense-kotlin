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
 * Performs backwards traversal on this [BooleanArray].
 */
public inline fun BooleanArray.forEachBackwards(action: FunctionUnit<Boolean>): Unit =
    GenericCollectionExtensions.forEachBackwards(count(), this::elementAt, action)
//endregion
