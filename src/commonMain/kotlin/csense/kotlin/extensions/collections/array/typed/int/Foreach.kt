@file:Suppress("unused", "NOTHING_TO_INLINE", "INVISIBLE_MEMBER", "INVISIBLE_REFERENCE")

package csense.kotlin.extensions.collections.array.typed.int

import csense.kotlin.*
import csense.kotlin.extensions.collections.generic.collection.*
import csense.kotlin.extensions.collections.generic.collection.operations.*


/**
 * A foreach, but not taking any result for the given receiver
 * @receiver [IntArray]
 * @param receiver [Function1]<[Int], U>
 */
public inline fun <U> IntArray.forEachDiscard(receiver: Function1<Int, U>): Unit =
    GenericCollections.foreachDiscardResult(count(), this::get, receiver)


/**
 * Performs backwards traversal on this [IntArray].
 */
public inline fun IntArray.forEachBackwards(action: FunctionUnit<Int>): Unit =
    GenericCollections.forEachBackwards(count(), this::elementAt, action)