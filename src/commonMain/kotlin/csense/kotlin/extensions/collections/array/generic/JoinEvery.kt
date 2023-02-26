@file:Suppress("unused", "NOTHING_TO_INLINE", "INVISIBLE_MEMBER", "INVISIBLE_REFERENCE")

package csense.kotlin.extensions.collections.array.generic

import csense.kotlin.annotations.numbers.*
import csense.kotlin.extensions.collections.generic.*
import csense.kotlin.extensions.collections.generic.collection.*
import csense.kotlin.extensions.collections.generic.collection.operations.*


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
    return GenericCollections.joinEveryAction(
        itemsBetweenJoins = itemsBetweenJoins,
        toJoinAction = toJoinAction,
        size = size,
        getter = this::get,
        builderType = ::Array
    )
}
