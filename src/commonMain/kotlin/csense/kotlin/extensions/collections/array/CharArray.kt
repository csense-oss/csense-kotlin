package csense.kotlin.extensions.collections.array

import csense.kotlin.*
import csense.kotlin.extensions.collections.array.generic.*
import csense.kotlin.extensions.collections.generic.*


/**
 * A foreach, but not taking any result for the given receiver
 * @receiver [CharArray]
 * @param receiver [Function1]<[Char], U>
 */
public inline fun <U> CharArray.forEachDiscard(receiver: Function1<Char, U>): Unit =
    GenericArray.foreachDiscardResult(count(), this::get, receiver)


//region Generic collection extensions
/**
 * Performs backwards traversal on this [CharArray].
 */
public inline fun CharArray.forEachBackwards(action: FunctionUnit<Char>): Unit =
    GenericCollectionExtensions.forEachBackwards(count(), this::elementAt, action)

//endregion
