@file:Suppress("unused", "NOTHING_TO_INLINE", "INVISIBLE_MEMBER", "INVISIBLE_REFERENCE")

package csense.kotlin.extensions.collections.array.typed.long

import csense.kotlin.*
import csense.kotlin.extensions.collections.generic.collection.*
import csense.kotlin.extensions.collections.generic.collection.operations.*


/**
 * A foreach, but not taking any result for the given receiver
 * @receiver [LongArray]
 * @param receiver [Function1]<[Long], U>
 */
public inline fun <U> LongArray.forEachDiscard(receiver: Function1<Long, U>): Unit =
    GenericCollections.foreachDiscardResult(count(), this::get, receiver)


/**
 * Performs backwards traversal on this [LongArray].
 */
public inline fun LongArray.forEachBackwards(action: FunctionUnit<Long>): Unit =
    GenericCollections.forEachBackwards(count(), this::elementAt, action)