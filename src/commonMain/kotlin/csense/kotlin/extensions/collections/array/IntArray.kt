package csense.kotlin.extensions.collections.array

import csense.kotlin.*
import csense.kotlin.extensions.collections.array.generic.*
import csense.kotlin.extensions.collections.generic.*


/**
 * A foreach, but not taking any result for the given receiver
 * @receiver [IntArray]
 * @param receiver [Function1]<[Int], U>
 */
public inline fun <U> IntArray.forEachDiscard(receiver: Function1<Int, U>): Unit =
    GenericArray.foreachDiscardResult(count(), this::get, receiver)


//region Generic collection extensions
/**
 * Performs traversal in pairs of 2  (with the first index as well)
 */
public inline fun IntArray.forEach2Indexed(action: Function2IndexedUnit<Int, Int>): Unit =
    GenericCollectionExtensions.forEach2Indexed(count(), ::elementAt, action)

/**
 * Performs traversal in pairs of 2
 */
public inline fun IntArray.forEach2(action: Function2Unit<Int, Int>): Unit =
    GenericCollectionExtensions.forEach2(count(), ::elementAt, action)

/**
 * Performs backwards traversal on this [IntArray].
 */
public inline fun IntArray.forEachBackwards(action: FunctionUnit<Int>): Unit =
    GenericCollectionExtensions.forEachBackwards(count(), this::elementAt, action)
//endregion
