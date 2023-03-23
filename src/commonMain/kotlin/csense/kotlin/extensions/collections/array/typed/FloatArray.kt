@file:Suppress("unused", "NOTHING_TO_INLINE", "INVISIBLE_MEMBER", "INVISIBLE_REFERENCE")
package csense.kotlin.extensions.collections.array.typed

import csense.kotlin.*
import csense.kotlin.extensions.collections.array.shared.*
import csense.kotlin.extensions.collections.generic.collection.*
import csense.kotlin.extensions.collections.generic.collection.operations.*


/**
 * A foreach, but not taking any result for the given receiver
 * @receiver [FloatArray]
 * @param receiver [Function1]<[Float], U>
 */
public inline fun <U> FloatArray.forEachDiscard(receiver: Function1<Float, U>): Unit =
    GenericArray.foreachDiscardResult(count(), this::get, receiver)

//region Generic collection extensions
/**
 * Performs backwards traversal on this [FloatArray].
 */
public inline fun FloatArray.forEachBackwards(action: FunctionUnit<Float>): Unit =
    GenericCollections.forEachBackwards(count(), this::elementAt, action)
//endregion
