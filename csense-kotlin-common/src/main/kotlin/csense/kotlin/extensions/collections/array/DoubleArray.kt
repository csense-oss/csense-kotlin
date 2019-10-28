package csense.kotlin.extensions.collections.array

import csense.kotlin.*
import csense.kotlin.extensions.collections.array.generic.*
import csense.kotlin.extensions.collections.generic.*


/**
 * A foreach, but not taking any result for the given receiver
 * @receiver
 * @param receiver (T) -> U
 */
inline fun <U> DoubleArray.forEachDiscard(receiver: Function1<Double, U>) =
        GenericArray.foreachDiscardResult(count(), this::get, receiver)



//region Generic collection extensions
/**
 * Performs traversal in pairs of 2  (with the first index as well)
 */
inline fun DoubleArray.forEach2Indexed(action: Function2IndexedUnit<Double, Double>) =
        GenericCollectionExtensions.forEach2Indexed(count(), ::elementAt, action)

/**
 * Performs traversal in pairs of 2
 */
inline fun DoubleArray.forEach2(action: Function2Unit<Double, Double>) =
        GenericCollectionExtensions.forEach2(count(), ::elementAt, action)

/**
 * Performs backwards traversal on this list.
 */
inline fun DoubleArray.forEachBackwards(action: FunctionUnit<Double>) =
        GenericCollectionExtensions.forEachBackwards(count(), this::elementAt, action)
//endregion
