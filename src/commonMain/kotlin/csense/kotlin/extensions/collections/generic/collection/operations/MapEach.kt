@file:Suppress("UnusedReceiverParameter")

package csense.kotlin.extensions.collections.generic.collection.operations

import csense.kotlin.annotations.numbers.*
import csense.kotlin.extensions.collections.*
import csense.kotlin.extensions.collections.generic.collection.*
import csense.kotlin.patterns.generic.*



public inline fun <ElementIn, ElementOut> GenericCollections.map(
    @IntLimit(from = 0) length: Int,
    retriever: Function1<@IntLimit(from = 0) Int, ElementIn>,
    @IntLimit(from = 0) startIndex: Int = 0,
    mapper: Function1<ElementIn, ElementOut>
): List<ElementOut> = mapEachWith(ArrayList(length), length, retriever, startIndex) {
    this += mapper(it)
}


/**
 * A very generic "mapEachWith" where access is delegated (via [retriever])
 * @receiver Generic
 * @param result Result
 * @param length Int
 * @param retriever Function1<Int, Element>
 * @param startIndex Int
 * @param append Function2<Result, Element, Unit>
 * @return Result
 */
public inline fun <Element, Result> GenericCollections.mapEachWith(
    result: Result,
    @IntLimit(from = 0) length: Int,
    retriever: Function1<@IntLimit(from = 0) Int, Element>,
    @IntLimit(from = 0) startIndex: Int = 0,
    append: Result.(Element) -> Unit
): Result {
    for (i: Int in startIndex until length) {
        result.append(retriever(i))
    }
    return result
}


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