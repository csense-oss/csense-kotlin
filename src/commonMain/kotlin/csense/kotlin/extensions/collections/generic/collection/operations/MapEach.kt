package csense.kotlin.extensions.collections.generic.collection.operations

import csense.kotlin.annotations.numbers.*
import csense.kotlin.extensions.collections.*
import csense.kotlin.extensions.collections.generic.collection.*


/**
 *
 * @receiver GenericCollectionExtensions
 * @param length [Int]
 * @param getter [GenericGetterIndexMethod]<T>
 * @param mapper [Function2]<T, T, U>
 * @return [List]<U>
 */
public inline fun <T, U> GenericCollections.mapEach2(
    @IntLimit(from = 0) length: Int,
    getter: GenericGetterIndexMethod<T>,
    mapper: Function2<T, T, U>
): List<U> {
    if (canNotForeach2(2)) {
        return emptyList()
    }
    return List(size = length / 2) { counter: Int ->
        val doubleIndex: Int = counter * 2
        val first: T = getter(doubleIndex)
        val second: T = getter(doubleIndex + 1)
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
public inline fun <T, U> GenericCollections.mapEach2Indexed(
    @IntLimit(from = 0) length: Int,
    getter: GenericGetterIndexMethod<T>,
    mapper: Function3<Int, T, T, U>
): List<U> {
    if (canNotForeach2(length = 2)) {
        return emptyList()
    }
    return List(size = length / 2) { counter: Int ->
        val doubleIndex: Int = counter * 2
        val first: T = getter(doubleIndex)
        val second: T = getter(doubleIndex + 1)
        mapper(doubleIndex, first, second)
    }
}