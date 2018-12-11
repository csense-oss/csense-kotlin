package csense.kotlin.extensions.collections.array

import csense.kotlin.*
import csense.kotlin.extensions.collections.array.generic.*
import csense.kotlin.extensions.collections.generic.*


/**
 * A foreach, but not taking any result for the given receiver
 * @receiver
 * @param receiver (T) -> U
 */
inline fun <U> IntArray.forEachDiscard(crossinline receiver: Function1<Int, U>) =
        ForeachDiscardResult(count(), this::get, receiver)

/**
 * Fills this array with the given value
 */
fun IntArray.fill(value: Int) =
        fillArray(count(), value, this::set)


//region Generic collection extensions
/**
 * Performs traversal in pairs of 2  (with the first index as well)
 */
inline fun IntArray.forEach2Indexed(action: Function2IndexedUnit<Int, Int>) =
        GenericCollectionExtensions.forEach2Indexed(count(), ::elementAt, action)

/**
 * Performs traversal in pairs of 2
 */
inline fun IntArray.forEach2(action: Function2Unit<Int, Int>) =
        GenericCollectionExtensions.forEach2(count(), ::elementAt, action)

/**
 * Performs backwards traversal on this list.
 */
inline fun IntArray.forEachBackwards(action: FunctionUnit<Int>) =
        GenericCollectionExtensions.forEachBackwards(count(), this::elementAt, action)
//endregion
