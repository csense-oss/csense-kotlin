@file:Suppress("unused", "NOTHING_TO_INLINE")

package csense.kotlin.extensions.collections.array

import csense.kotlin.Function1
import csense.kotlin.FunctionUnit
import csense.kotlin.extensions.collections.array.generic.GenericArray
import csense.kotlin.extensions.collections.array.generic.foreachDiscardResult
import csense.kotlin.extensions.collections.generic.*
import kotlin.contracts.*

/**
 * A foreach, but not taking any result for the given receiver
 * @receiver [Array]<T>
 * @param receiver [Function1]<T, U>
 */
inline fun <T, U> Array<T>.forEachDiscard(receiver: Function1<T, U>) =
        GenericArray.foreachDiscardResult(count(), this::get, receiver)


//region Generic collection extensions
/**
 * Performs traversal in pairs of 2  (with the first index as well)
 */
inline fun <T> Array<T>.forEach2Indexed(action: Function2IndexedUnit<T, T>) =
        GenericCollectionExtensions.forEach2Indexed(count(), ::elementAt, action)

/**
 * Performs traversal in pairs of 2
 */
inline fun <T> Array<T>.forEach2(action: Function2Unit<T, T>) =
        GenericCollectionExtensions.forEach2(count(), ::elementAt, action)

/**
 * Performs backwards traversal on this list.
 */
inline fun <T> Array<T>.forEachBackwards(action: FunctionUnit<T>) =
        GenericCollectionExtensions.forEachBackwards(count(), this::elementAt, action)
//endregion


/**
 * Tells if this [Array] is NOT null And NOT empty (size > 0)
 * @receiver [Array]<T>? the nullable Array
 * @return [Boolean] true if the Array is NOT null AND NOT empty
 */
@OptIn(ExperimentalContracts::class)
inline fun <T> Array<T>?.isNotNullOrEmpty(): Boolean {
    contract {
        returns(true) implies (this@isNotNullOrEmpty != null)
    }
    return this != null && this.isNotEmpty()
}

/**
 * Tells if this [Array] is null or empty (size = 0)
 * @receiver [Array]<T>? the nullable Array
 * @return [Boolean] true if the Array is null or empty
 */
@OptIn(ExperimentalContracts::class)
inline fun <T> Array<T>?.isNullOrEmpty(): Boolean {
    contract {
        returns(false) implies (this@isNullOrEmpty != null)
    }
    return this == null || this.isEmpty()
}