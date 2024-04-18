@file:Suppress("unused", "NOTHING_TO_INLINE", "INVISIBLE_MEMBER")

package org.csenseoss.kotlin.extensions.collections.array.typed.short

import org.csenseoss.kotlin.*
import org.csenseoss.kotlin.extensions.collections.generic.collection.*
import org.csenseoss.kotlin.extensions.collections.generic.collection.operations.*


/**
 * A foreach, but not taking any result for the given receiver
 *
 * @receiver [ShortArray]
 * @param action [Function1]<[Short], U>
 */
public inline fun <U> ShortArray.forEachDiscard(action: Function1<Short, U>): Unit =
    GenericCollections.foreachDiscardResult(
        count = count(),
        getter = this::get,
        receiver = action
    )

/**
 * Performs backwards traversal on this [ShortArray].
 */
public inline fun ShortArray.forEachBackwards(action: FunctionUnit<Short>): Unit =
    GenericCollections.forEachBackwards(
        length = count(),
        getter = this::elementAt,
        action = action
    )