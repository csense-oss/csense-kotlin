@file:Suppress("unused", "UnusedReceiverParameter")

package csense.kotlin.extensions.collections.generic.collection.operations

import csense.kotlin.*
import csense.kotlin.annotations.numbers.*
import csense.kotlin.extensions.collections.*
import csense.kotlin.extensions.collections.generic.collection.*


/**
 *
 * @receiver GenericCollectionExtensions
 * @param length [Int]
 * @param getter [GenericGetterIndexMethod]<T>
 * @param action [FunctionUnit]<T>
 */
public inline fun <T> GenericCollections.forEachBackwards(
    @IntLimit(from = 0) length: Int,
    getter: GenericGetterIndexMethod<T>,
    action: FunctionUnit<T>
) {
    for (i in (length - 1) downTo 0) {
        action(getter(i))
    }
}

/**
 *
 * @receiver GenericCollectionExtensions
 * @param length [Int]
 * @param getter [GenericGetterIndexMethod]<T>
 * @param action [FunctionUnit]<T>
 */
public inline fun <T> GenericCollections.forEachBackwardsIndexed(
    @IntLimit(from = 0) length: Int,
    getter: GenericGetterIndexMethod<T>,
    action: (index: @IntLimit(from = 0) Int, element: T) -> Unit
) {
    for (i in (length - 1) downTo 0) {
        action(i, getter(i))
    }
}
