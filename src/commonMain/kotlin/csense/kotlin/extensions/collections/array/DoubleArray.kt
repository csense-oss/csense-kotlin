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


//region Generic collection extensions
/**
 * Performs traversal in pairs of 2  (with the first index as well)
 */
@Deprecated("will be removed")
public inline fun DoubleArray.forEach2Indexed(action: Function2IndexedUnit<Double, Double>): Unit =
    GenericCollectionExtensions.forEach2Indexed(count(), ::elementAt, action)

/**
 * Performs traversal in pairs of 2
 */
@Deprecated("will be removed")
public inline fun DoubleArray.forEach2(action: Function2Unit<Double, Double>): Unit =
    GenericCollectionExtensions.forEach2(count(), ::elementAt, action)

/**
 * Performs backwards traversal on this [DoubleArray].
 */
public inline fun DoubleArray.forEachBackwards(action: FunctionUnit<Double>): Unit =
    GenericCollectionExtensions.forEachBackwards(count(), this::elementAt, action)
//endregion
