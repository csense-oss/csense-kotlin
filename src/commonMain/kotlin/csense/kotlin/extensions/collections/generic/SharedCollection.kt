package csense.kotlin.extensions.collections.generic

import csense.kotlin.*
import csense.kotlin.annotations.numbers.*
import csense.kotlin.extensions.collections.*


/**
 * Joins the item from the given [toJoinAction] between [itemsBetweenJoins] into a single [List].
 * @param itemsBetweenJoins [Int] how many items there should be between a join. a join can not be the first or last item in the result
 * @param toJoinAction [T] the action producing what to join in between the items
 * @param size [Int] the size of the "getter"'s collection
 * @param getter [GenericGetterIndexMethod]<[T]> the collection to get the starting items from
 * @param builderType
 * @return [List]<T> the resulting list by joining the starting items with the [toJoinAction]
 */
public inline fun <reified T, reified U> GenericCollectionExtensions.joinEveryAction(
        @IntLimit(from = 1) itemsBetweenJoins: Int,
        crossinline toJoinAction: Function0R<T>,
        size: Int,
        crossinline getter: GenericGetterIndexMethod<T>,
        crossinline builderType: Function2<Int, Function1<Int, T>, U>
): U {
    val numberOfJoins = (size - 1).div(itemsBetweenJoins)
    if (numberOfJoins <= 0) {
        return builderType(size) { getter(it) }
    }
    val newSize = size + numberOfJoins
    var getterIndex = 0
    return builderType(newSize) { newIndex: Int ->
        //(newIndex + 1): 0 rem anything is 0;itemBetweenJoins + 1: count the join item itself
        val isJoin = (newIndex + 1).rem(itemsBetweenJoins + 1) == 0
        if (isJoin) {
            toJoinAction()
        } else {
            getter(getterIndex).also { getterIndex += 1 }
        }
    }
}
