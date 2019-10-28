package csense.kotlin.extensions.collections.array

import csense.kotlin.*
import csense.kotlin.extensions.collections.array.generic.*
import csense.kotlin.extensions.collections.generic.*


/**
 * A foreach, but not taking any result for the given receiver
 * @receiver
 * @param receiver (T) -> U
 */
inline fun <U> LongArray.forEachDiscard(receiver: Function1<Long, U>): Unit =
        GenericArray.foreachDiscardResult(count(), this::get, receiver)

//region Generic collection extensions
/**
 * Performs traversal in pairs of 2  (with the first index as well)
 */
inline fun LongArray.forEach2Indexed(action: Function2IndexedUnit<Long, Long>): Unit =
        GenericCollectionExtensions.forEach2Indexed(count(), ::elementAt, action)

/**
 * Performs traversal in pairs of 2
 */
inline fun LongArray.forEach2(action: Function2Unit<Long, Long>): Unit =
        GenericCollectionExtensions.forEach2(count(), ::elementAt, action)

/**
 * Performs backwards traversal on this list.
 */
inline fun LongArray.forEachBackwards(action: FunctionUnit<Long>): Unit =
        GenericCollectionExtensions.forEachBackwards(count(), this::elementAt, action)
//endregion
