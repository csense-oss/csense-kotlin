package csense.kotlin.extensions.collections.array

import csense.kotlin.*
import csense.kotlin.extensions.collections.array.generic.*
import csense.kotlin.extensions.collections.generic.*


/**
 * A foreach, but not taking any result for the given receiver
 * @receiver [FloatArray]
 * @param receiver [Function1]<[Float], U>
 */
public inline fun <U> FloatArray.forEachDiscard(receiver: Function1<Float, U>): Unit =
    GenericArray.foreachDiscardResult(count(), this::get, receiver)

//region Generic collection extensions
/**
 * Performs traversal in pairs of 2  (with the first index as well)
 */
@Deprecated("will be removed")
public inline fun FloatArray.forEach2Indexed(action: Function2IndexedUnit<Float, Float>): Unit =
    GenericCollectionExtensions.forEach2Indexed(count(), ::elementAt, action)

/**
 * Performs traversal in pairs of 2
 */
@Deprecated("will be removed")
public inline fun FloatArray.forEach2(action: Function2Unit<Float, Float>): Unit =
    GenericCollectionExtensions.forEach2(count(), ::elementAt, action)

/**
 * Performs backwards traversal on this [FloatArray].
 */
public inline fun FloatArray.forEachBackwards(action: FunctionUnit<Float>): Unit =
    GenericCollectionExtensions.forEachBackwards(count(), this::elementAt, action)
//endregion
