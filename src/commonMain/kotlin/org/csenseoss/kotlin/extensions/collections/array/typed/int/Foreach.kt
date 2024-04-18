@file:Suppress("unused", "NOTHING_TO_INLINE", "INVISIBLE_MEMBER")

package org.csenseoss.kotlin.extensions.collections.array.typed.int

import org.csenseoss.kotlin.*
import org.csenseoss.kotlin.extensions.collections.generic.collection.*
import org.csenseoss.kotlin.extensions.collections.generic.collection.operations.*


/**
 * A foreach, but not taking any result for the given receiver
 * @receiver [IntArray]
 * @param receiver [Function1]<[Int], U>
 */
public inline fun <U> IntArray.forEachDiscard(receiver: Function1<Int, U>): Unit =
    GenericCollections.foreachDiscardResult(
        count = count(),
        getter = this::get,
        receiver = receiver
    )


/**
 * Performs backwards traversal on this [IntArray].
 */
public inline fun IntArray.forEachBackwards(action: FunctionUnit<Int>): Unit =
    GenericCollections.forEachBackwards(
        length = count(),
        getter = this::elementAt,
        action = action
    )