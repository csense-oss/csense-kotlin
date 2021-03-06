@file:Suppress("unused", "NOTHING_TO_INLINE")

package csense.kotlin.extensions.collections.generic

import csense.kotlin.*
import csense.kotlin.annotations.numbers.*
import csense.kotlin.extensions.collections.*
import csense.kotlin.extensions.primitives.*


public typealias Function2Unit<T, U> = (first: T, second: U) -> Unit
public typealias Function2IndexedUnit<T, U> = (indexOfFirst: @IntLimit(from = 0) Int, first: T, second: U) -> Unit


//region backwards
/**
 *
 * @receiver GenericCollectionExtensions
 * @param length [Int]
 * @param getter [GenericGetterIndexMethod]<T>
 * @param action [FunctionUnit]<T>
 */
public inline fun <T> GenericCollectionExtensions.forEachBackwards(
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
public inline fun <T> GenericCollectionExtensions.forEachBackwardsIndexed(
    @IntLimit(from = 0) length: Int,
    getter: GenericGetterIndexMethod<T>,
    action: Function2Unit<@IntLimit(from = 0) Int, T>
) {
    for (i in (length - 1) downTo 0) {
        action(i, getter(i))
    }
}
//endregion

//region foreach 2
/**
 * This is the "internal" generic function ,from which all the instances will be using.
 * @receiver GenericCollectionExtensions
 * @param length [Int]
 * @param getter [GenericGetterIndexMethod]<T>
 * @param action [Function2Unit]<T, T>
 */
public inline fun <T> GenericCollectionExtensions.forEach2(
    @IntLimit(from = 0) length: Int,
    getter: GenericGetterIndexMethod<T>,
    action: Function2Unit<T, T>
) {
    if (canNOTForeach2(length)) {
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
public inline fun <T> GenericCollectionExtensions.forEach2Indexed(
    @IntLimit(from = 0) length: Int,
    getter: GenericGetterIndexMethod<T>,
    action: Function2IndexedUnit<T, T>
) {
    if (canNOTForeach2(length)) {
        return
    }
    for (i in (0 until length step 2)) {
        val first = getter(i)
        val second = getter(i + 1)
        action(i, first, second)
    }
}
//endregion

//region map 2

/**
 *
 * @receiver GenericCollectionExtensions
 * @param length [Int]
 * @param getter [GenericGetterIndexMethod]<T>
 * @param mapper [Function2]<T, T, U>
 * @return [List]<U>
 */
public inline fun <T, U> GenericCollectionExtensions.mapEach2(
    @IntLimit(from = 0) length: Int,
    getter: GenericGetterIndexMethod<T>,
    mapper: Function2<T, T, U>
): List<U> {
    if (canNOTForeach2(2)) {
        return emptyList()
    }
    return List(length / 2) { counter: Int ->
        val doubleIndex = counter * 2
        val first = getter(doubleIndex)
        val second = getter(doubleIndex + 1)
        mapper(first, second)
    }
}

/**
 *
 * @receiver GenericCollectionExtensions
 * @param length [Int]
 * @param getter [GenericGetterIndexMethod]<T>
 * @param mapper [Function3]<Int, T, T, U>
 * @return [List]<U>
 */
public inline fun <T, U> GenericCollectionExtensions.mapEach2Indexed(
    @IntLimit(from = 0) length: Int,
    getter: GenericGetterIndexMethod<T>,
    mapper: Function3<Int, T, T, U>
): List<U> {
    if (canNOTForeach2(2)) {
        return emptyList()
    }
    return List(length / 2) { counter: Int ->
        val doubleIndex = counter * 2
        val first = getter(doubleIndex)
        val second = getter(doubleIndex + 1)
        mapper(doubleIndex, first, second)
    }
}
//endregion

//region foreach / map 2 helper
/**
 * Tells if we are able to perform any actions (foreach2) on the given length of a "collection"
 * @receiver [GenericCollectionExtensions]
 * @param length [Int]
 * @return [Boolean]
 */
public inline fun GenericCollectionExtensions.canNOTForeach2(length: Int): Boolean {
    return (length <= 0 || length.isOdd)
}
//endregion