@file:Suppress("unused", "NOTHING_TO_INLINE", "INVISIBLE_MEMBER")


package org.csenseoss.kotlin.extensions.collections.array.generic

import org.csenseoss.kotlin.*
import org.csenseoss.kotlin.extensions.collections.generic.collection.*
import org.csenseoss.kotlin.extensions.collections.generic.collection.operations.*
import kotlin.Function1


/**
 * A foreach, but not taking any result for the given receiver
 * @receiver [Array]<T>
 * @param action [Function1]<T, U>
 */
public inline fun <T, U> Array<T>.forEachDiscard(action: Function1<T, U>): Unit =
    GenericCollections.foreachDiscardResult(count(), this::get, action)


/**
 * Performs backwards traversal on this list.
 */
public inline fun <T> Array<T>.forEachBackwards(action: FunctionUnit<T>): Unit =
    GenericCollections.forEachBackwards(count(), this::elementAt, action)