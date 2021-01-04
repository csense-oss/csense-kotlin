@file:Suppress("unused", "NOTHING_TO_INLINE")

package csense.kotlin.extensions.collections.array

import csense.kotlin.*
import csense.kotlin.annotations.numbers.*
import csense.kotlin.extensions.collections.array.generic.*
import csense.kotlin.extensions.collections.generic.*
import csense.kotlin.extensions.primitives.*
import kotlin.contracts.*

/**
 * A foreach, but not taking any result for the given receiver
 * @receiver [Array]<T>
 * @param receiver [Function1]<T, U>
 */
public inline fun <T, U> Array<T>.forEachDiscard(receiver: Function1<T, U>): Unit =
    GenericArray.foreachDiscardResult(count(), this::get, receiver)


//region Generic collection extensions
/**
 * Performs traversal in pairs of 2  (with the first index as well)
 */
public inline fun <T> Array<T>.forEach2Indexed(action: Function2IndexedUnit<T, T>): Unit =
    GenericCollectionExtensions.forEach2Indexed(count(), ::elementAt, action)

/**
 * Performs traversal in pairs of 2
 */
public inline fun <T> Array<T>.forEach2(action: Function2Unit<T, T>): Unit =
    GenericCollectionExtensions.forEach2(count(), ::elementAt, action)

/**
 * Performs backwards traversal on this list.
 */
public inline fun <T> Array<T>.forEachBackwards(action: FunctionUnit<T>): Unit =
    GenericCollectionExtensions.forEachBackwards(count(), this::elementAt, action)
//endregion


/**
 * Tells if this [Array] is NOT null And NOT empty (size > 0)
 * @receiver [Array]<T>? the nullable Array
 * @return [Boolean] true if the Array is NOT null AND NOT empty
 */
@OptIn(ExperimentalContracts::class)
public inline fun <T> Array<T>?.isNotNullOrEmpty(): Boolean {
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
@Suppress("MissingTestFunction")
@Deprecated(message = "use kotlin std lib instead", level = DeprecationLevel.ERROR)
@OptIn(ExperimentalContracts::class)
public inline fun <T> Array<T>?.isNullOrEmpty(): Boolean {
    contract {
        returns(false) implies (this@isNullOrEmpty != null)
    }
    return this == null || this.isEmpty()
}

@IntLimit(from = 0)
public inline fun <T> Array<out T>.indexOfFirstOrNull(predicate: (T) -> Boolean): Int? =
    indexOfFirst(predicate).indexOfExtensions.unwrapUnsafeIndexOf()


/**
 *
 * @receiver [Collection]<T>
 * @param predicate [Function1]<T, [Boolean]>
 * @return [Int]? null if not found, the index otherwise.
 */
@IntLimit(from = 0)
public inline fun <T> Array<T>.indexOfLastOrNull(predicate: Function1<T, Boolean>): Int? =
    indexOfLast(predicate).indexOfExtensions.unwrapUnsafeIndexOf()


//region joinEvery
public inline fun <reified T> Array<T>.joinEvery(
    @IntLimit(from = 0) itemsBetweenJoins: Int,
    toJoin: T
): Array<T> = joinEveryAction(
    itemsBetweenJoins,
    toJoinAction = { toJoin }
)

/**
 *
 * @receiver Collection<T>
 * @param itemsBetweenJoins Int
 * @param toJoinAction T
 * @return List<T>
 */
public inline fun <reified T> Array<T>.joinEveryAction(
    @IntLimit(from = 0) itemsBetweenJoins: Int,
    crossinline toJoinAction: () -> T
): Array<T> {
    if (itemsBetweenJoins <= 0) {
        return this
    }
    return GenericCollectionExtensions.joinEveryAction(
        itemsBetweenJoins,
        toJoinAction,
        size,
        this::get,
        ::Array
    )
}

//endregion
