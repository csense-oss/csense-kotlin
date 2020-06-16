package csense.kotlin.extensions.collections.array

import csense.kotlin.Function1
import csense.kotlin.FunctionUnit
import csense.kotlin.extensions.collections.array.generic.GenericArray
import csense.kotlin.extensions.collections.array.generic.foreachDiscardResult
import csense.kotlin.extensions.collections.generic.*


/**
 * A foreach, but not taking any result for the given receiver
 * @receiver [DoubleArray]
 * @param receiver [Function1]<[Double], U>
 */
inline fun <U> DoubleArray.forEachDiscard(receiver: Function1<Double, U>): Unit =
        GenericArray.foreachDiscardResult(count(), this::get, receiver)



//region Generic collection extensions
/**
 * Performs traversal in pairs of 2  (with the first index as well)
 */
inline fun DoubleArray.forEach2Indexed(action: Function2IndexedUnit<Double, Double>): Unit =
        GenericCollectionExtensions.forEach2Indexed(count(), ::elementAt, action)

/**
 * Performs traversal in pairs of 2
 */
inline fun DoubleArray.forEach2(action: Function2Unit<Double, Double>): Unit =
        GenericCollectionExtensions.forEach2(count(), ::elementAt, action)

/**
 * Performs backwards traversal on this [DoubleArray].
 */
inline fun DoubleArray.forEachBackwards(action: FunctionUnit<Double>): Unit =
        GenericCollectionExtensions.forEachBackwards(count(), this::elementAt, action)
//endregion
