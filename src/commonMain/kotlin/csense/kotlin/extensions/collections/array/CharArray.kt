package csense.kotlin.extensions.collections.array

import csense.kotlin.*
import csense.kotlin.extensions.collections.array.generic.*
import csense.kotlin.extensions.collections.generic.*


/**
 * A foreach, but not taking any result for the given receiver
 * @receiver [CharArray]
 * @param receiver [Function1]<[Char], U>
 */
public inline fun <U> CharArray.forEachDiscard(receiver: Function1<Char, U>): Unit =
    GenericArray.foreachDiscardResult(count(), this::get, receiver)


//region Generic collection extensions
/**
 * Performs traversal in pairs of 2  (with the first index as well)
 */
@Deprecated("will be removed")
@Suppress("MissingTestFunction")
public inline fun CharArray.forEach2Indexed(action: Function2IndexedUnit<Char, Char>): Unit =
    GenericCollectionExtensions.forEach2Indexed(count(), ::elementAt, action)

/**
 * Performs traversal in pairs of 2
 */
@Deprecated("will be removed")
@Suppress("MissingTestFunction")
public inline fun CharArray.forEach2(action: Function2Unit<Char, Char>): Unit =
    GenericCollectionExtensions.forEach2(count(), ::elementAt, action)

/**
 * Performs backwards traversal on this [CharArray].
 */
public inline fun CharArray.forEachBackwards(action: FunctionUnit<Char>): Unit =
    GenericCollectionExtensions.forEachBackwards(count(), this::elementAt, action)

//endregion
