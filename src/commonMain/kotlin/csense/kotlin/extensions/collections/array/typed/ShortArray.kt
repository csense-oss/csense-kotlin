@file:Suppress("unused", "NOTHING_TO_INLINE", "INVISIBLE_MEMBER", "INVISIBLE_REFERENCE")

package csense.kotlin.extensions.collections.array.typed

import csense.kotlin.*
import csense.kotlin.extensions.collections.array.shared.*
import csense.kotlin.extensions.collections.generic.collection.*
import csense.kotlin.extensions.collections.generic.collection.operations.*


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
    GenericCollections.forEachBackwards(count(), this::elementAt, action)
//endregion
