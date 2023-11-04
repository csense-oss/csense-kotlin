@file:Suppress("unused", "NOTHING_TO_INLINE", "INVISIBLE_MEMBER", "INVISIBLE_REFERENCE")

package csense.kotlin.extensions.collections.array.typed.double

import csense.kotlin.*
import csense.kotlin.extensions.collections.generic.collection.*
import csense.kotlin.extensions.collections.generic.collection.operations.*


/**
 * A foreach, but not taking any result for the given receiver
 * @receiver [DoubleArray]
 * @param action [Function1]<[Double], U>
 */
public inline fun <U> DoubleArray.forEachDiscard(action: Function1<Double, U>): Unit =
    GenericCollections.foreachDiscardResult(
        count = count(),
        getter = this::get,
        receiver = action
    )

/**
 * Performs backwards traversal on this [DoubleArray].
 */
public inline fun DoubleArray.forEachBackwards(action: FunctionUnit<Double>): Unit =
    GenericCollections.forEachBackwards(
        length = count(),
        getter = this::elementAt,
        action = action
    )
