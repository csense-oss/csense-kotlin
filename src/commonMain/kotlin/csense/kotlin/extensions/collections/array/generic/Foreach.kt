@file:Suppress("unused", "NOTHING_TO_INLINE", "INVISIBLE_MEMBER", "INVISIBLE_REFERENCE")


package csense.kotlin.extensions.collections.array.generic

import csense.kotlin.*
import csense.kotlin.extensions.collections.array.shared.*
import csense.kotlin.extensions.collections.generic.collection.*
import csense.kotlin.extensions.collections.generic.collection.operations.*
import kotlin.Function1


/**
 * A foreach, but not taking any result for the given receiver
 * @receiver [Array]<T>
 * @param receiver [Function1]<T, U>
 */
public inline fun <T, U> Array<T>.forEachDiscard(receiver: Function1<T, U>): Unit =
    GenericArray.foreachDiscardResult(count(), this::get, receiver)


//region Generic collection extensions
/**
 * Performs backwards traversal on this list.
 */
public inline fun <T> Array<T>.forEachBackwards(action: FunctionUnit<T>): Unit =
    GenericCollections.forEachBackwards(count(), this::elementAt, action)
//endregion
