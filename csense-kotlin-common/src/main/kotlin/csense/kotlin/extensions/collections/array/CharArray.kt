package csense.kotlin.extensions.collections.array

import csense.kotlin.*
import csense.kotlin.extensions.collections.array.generic.*
import csense.kotlin.extensions.collections.generic.*


/**
 * A foreach, but not taking any result for the given receiver
 * @receiver ByteArray
 * @param receiver (T) -> U
 */
inline fun <U> CharArray.forEachDiscard(receiver: kotlin.Function1<Char, U>) =
        GenericArray.foreachDiscardResult(count(), this::get, receiver)



//region Generic collection extensions
/**
 * Performs traversal in pairs of 2  (with the first index as well)
 */
inline fun CharArray.forEach2Indexed(action: Function2IndexedUnit<Char, Char>) =
        GenericCollectionExtensions.forEach2Indexed(count(), ::elementAt, action)

/**
 * Performs traversal in pairs of 2
 */
inline fun CharArray.forEach2(action: Function2Unit<Char, Char>) =
        GenericCollectionExtensions.forEach2(count(), ::elementAt, action)

/**
 * Performs backwards traversal on this list.
 */
inline fun CharArray.forEachBackwards(action: FunctionUnit<Char>) =
        GenericCollectionExtensions.forEachBackwards(count(), this::elementAt, action)
//endregion
