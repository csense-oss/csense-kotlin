@file:Suppress("unused", "NOTHING_TO_INLINE", "INVISIBLE_MEMBER", "INVISIBLE_REFERENCE")

package csense.kotlin.extensions.collections.array.typed.float

import csense.kotlin.*
import csense.kotlin.extensions.collections.generic.collection.*
import csense.kotlin.extensions.collections.generic.collection.operations.*


/**
 * A foreach, but not taking any result for the given receiver
 * @receiver [FloatArray]
 * @param action [Function1]<[Float], U>
 */
public inline fun <U> FloatArray.forEachDiscard(action: Function1<Float, U>): Unit =
    GenericCollections.foreachDiscardResult(
        count = count(),
        getter = this::get,
        receiver = action
    )

/**
 * Performs backwards traversal on this [FloatArray].
 */
public inline fun FloatArray.forEachBackwards(action: FunctionUnit<Float>): Unit =
    GenericCollections.forEachBackwards(
        length = count(),
        getter = this::elementAt,
        action = action
    )
