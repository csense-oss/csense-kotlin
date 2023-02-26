@file:Suppress("UnusedReceiverParameter", "NOTHING_TO_INLINE")

package csense.kotlin.extensions.collections.generic.collection.operations

import csense.kotlin.annotations.numbers.*
import csense.kotlin.extensions.collections.*
import csense.kotlin.extensions.collections.generic.collection.*
import csense.kotlin.extensions.primitives.int.*

/**
 * This is the "internal" generic function ,from which all the instances will be using.
 * @receiver GenericCollectionExtensions
 * @param length [Int]
 * @param getter [GenericGetterIndexMethod]<T>
 * @param action [Function2Unit]<T, T>
 */
public inline fun <T> GenericCollections.forEach2(
    @IntLimit(from = 0) length: Int,
    getter: GenericGetterIndexMethod<T>,
    action: Function2Unit<T, T>
) {
    if (canNotForeach2(length)) {
        return
    }
    for (i in (0 until length step 2)) {
        val first = getter(i)
        val second = getter(i + 1)
        action(first, second)
    }
}

/**
 *
 * @receiver GenericCollectionExtensions
 * @param length [Int]
 * @param getter [GenericGetterIndexMethod]<T>
 * @param action [Function2IndexedUnit]<T, T>
 */
public inline fun <T> GenericCollections.forEach2Indexed(
    @IntLimit(from = 0) length: Int,
    getter: GenericGetterIndexMethod<T>,
    action: Function2IndexedUnit<T, T>
) {
    if (canNotForeach2(length)) {
        return
    }
    for (i in (0 until length step 2)) {
        val first = getter(i)
        val second = getter(i + 1)
        action(i, first, second)
    }
}

/**
 * Tells if we are able to perform any actions (foreach2) on the given length of a "collection"
 * @receiver [GenericCollections]
 * @param length [Int]
 * @return [Boolean]
 */
public inline fun GenericCollections.canNotForeach2(length: Int): Boolean {
    return (length <= 0 || length.isOdd)
}
