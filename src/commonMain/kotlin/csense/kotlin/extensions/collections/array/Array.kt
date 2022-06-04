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

public inline fun <T> Array<T>?.isNotNullOrEmpty(): Boolean {
    contract {
        returns(true) implies (this@isNotNullOrEmpty != null)
    }
    return this != null && this.isNotEmpty()
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
        itemsBetweenJoins = itemsBetweenJoins,
        toJoinAction = toJoinAction,
        size = size,
        getter = this::get,
        builderType = ::Array
    )
}

/**
 * Maps each element via [transform] into a [MutableList]
 * @receiver Array<Item>
 * @param transform Function1<Item, Result>
 * @return MutableList<Result>
 */
public inline fun <Item, Result> Array<Item>.mapToMutable(
    transform: (Item) -> Result
): MutableList<Result> = mapEachWith(ArrayList(size)) {
    this += transform(it)
}

/**
 * Apply [map] on each item [with] the given [result]
 * @param result Result the result to apply each [map] to
 * @param map Function2<Result, Item, Unit> the processing of the given item on the [result]
 * @return Result
 */
public inline fun <Item, Result> Array<Item>.mapEachWith(
    result: Result,
    map: Result.(Item) -> Unit
): Result {
    forEach {
        result.map(it)
    }
    return result
}

/**
 * Gets the value at [index] or returns [defaultValue] if out of bounds.
 * @receiver Array<Item>
 * @param index Int
 * @param defaultValue Item
 * @return Item
 */
public inline fun <Item> Array<out Item>.getOr(index: Int, defaultValue: Item & Any): Item & Any {
    return getOrNull(index) ?: defaultValue
}