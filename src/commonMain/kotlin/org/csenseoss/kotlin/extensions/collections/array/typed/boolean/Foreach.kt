@file:Suppress("unused", "NOTHING_TO_INLINE", "INVISIBLE_MEMBER")

package org.csenseoss.kotlin.extensions.collections.array.typed.boolean

import org.csenseoss.kotlin.*
import org.csenseoss.kotlin.extensions.collections.generic.collection.*
import org.csenseoss.kotlin.extensions.collections.generic.collection.operations.*


/**
 * A foreach, but not taking any result for the given receiver
 * @receiver [BooleanArray]
 * @param action [Function1]<[Boolean], U>
 */
public inline fun <U> BooleanArray.forEachDiscard(action: Function1<Boolean, U>): Unit =
    GenericCollections.foreachDiscardResult(
        count = count(),
        getter = this::get,
        receiver = action
    )


/**
 * Performs backwards traversal on this [BooleanArray].
 */
public inline fun BooleanArray.forEachBackwards(action: FunctionUnit<Boolean>): Unit =
    GenericCollections.forEachBackwards(
        length = count(),
        getter = this::elementAt,
        action = action
    )