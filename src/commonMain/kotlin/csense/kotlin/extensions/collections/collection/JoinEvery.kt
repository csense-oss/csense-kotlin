@file:Suppress("unused", "NOTHING_TO_INLINE", "INVISIBLE_MEMBER", "INVISIBLE_REFERENCE")

package csense.kotlin.extensions.collections.collection

import csense.kotlin.annotations.numbers.*
import csense.kotlin.extensions.collections.generic.*


/**
 * Joins [toJoin] between [itemsBetweenJoin] into a single [List].
 * @receiver [Collection]<[Item]> The items to insert the joins between
 * @param itemsBetweenJoin [Int] how many items there should be between a join. a join can not be the first or last item in the result
 * @param toJoin [Item] what to join in between the items
 * @return [List]<Item>
 */
public inline fun <Item> Collection<Item>.joinEvery(
    @IntLimit(from = 1) itemsBetweenJoin: Int,
    toJoin: Item
): List<Item> = joinEveryAction(
    itemsBetweenJoin,
    toJoinAction = { toJoin }
)


/**
 * Joins the item from the given [toJoinAction] between [itemsBetweenJoin] into a single [List].
 * @receiver [Collection]<[Item]> The items to insert the joins between
 * @param itemsBetweenJoin [Int] how many items there should be between a join. a join can not be the first or last item in the result
 * @param toJoinAction [Item] the action producing what to join in between the items
 * @return [List]<Item>
 */
public inline fun <Item> Collection<Item>.joinEveryAction(
    @IntLimit(from = 1) itemsBetweenJoin: Int,
    crossinline toJoinAction: () -> Item
): List<Item> {
    if (itemsBetweenJoin <= 0) {
        return this.toList()
    }
    return GenericCollectionExtensions.joinEveryAction(
        itemsBetweenJoins = itemsBetweenJoin,
        toJoinAction = toJoinAction,
        size = size,
        getter = this::elementAt,
        builderType = ::List
    )
}
