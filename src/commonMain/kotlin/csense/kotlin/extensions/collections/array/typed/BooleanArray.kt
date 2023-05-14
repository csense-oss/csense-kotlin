@file:Suppress("unused", "NOTHING_TO_INLINE", "INVISIBLE_MEMBER", "INVISIBLE_REFERENCE")

package csense.kotlin.extensions.collections.array.typed

import csense.kotlin.*
import csense.kotlin.extensions.collections.array.shared.*
import csense.kotlin.extensions.collections.generic.collection.*
import csense.kotlin.extensions.collections.generic.collection.operations.*


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
    GenericCollections.forEachBackwards(count(), this::elementAt, action)
//endregion
