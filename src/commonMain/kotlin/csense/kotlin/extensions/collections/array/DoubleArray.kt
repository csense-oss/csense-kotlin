package csense.kotlin.extensions.collections.array

import csense.kotlin.*
import csense.kotlin.extensions.collections.array.generic.*
import csense.kotlin.extensions.collections.generic.*


/**
 * A foreach, but not taking any result for the given receiver
 * @receiver [DoubleArray]
 * @param receiver [Function1]<[Double], U>
 */
public inline fun <U> DoubleArray.forEachDiscard(receiver: Function1<Double, U>): Unit =
    GenericArray.foreachDiscardResult(count(), this::get, receiver)

/**
 * Performs backwards traversal on this [DoubleArray].
 */
public inline fun DoubleArray.forEachBackwards(action: FunctionUnit<Double>): Unit =
    GenericCollectionExtensions.forEachBackwards(count(), this::elementAt, action)
