@file:Suppress("unused", "NOTHING_TO_INLINE")

package csense.kotlin.extensions.collections.generic

import csense.kotlin.Function3
import csense.kotlin.extensions.collections.*
import csense.kotlin.extensions.primitives.*


typealias Function2Unit<T, U> = (T, U) -> Unit
typealias Function2IndexedUnit<T, U> = (Int, T, U) -> Unit


/**
 * This is the "internal" generic function ,from which all the instances will be using.
 * @receiver GenericCollectionExtensions
 */
inline fun <T> GenericCollectionExtensions.forEach2(
        length: Int,
        crossinline getter: GenericGetterIndexMethod<T>,
        action: Function2Unit<T, T>) {
    foreach2Indexed(length, getter, { _: Int, first: T, second: T ->
        action(first, second)
    })
}


inline fun <T> GenericCollectionExtensions.foreach2Indexed(
        length: Int,
        crossinline getter: GenericGetterIndexMethod<T>,
        action: Function2IndexedUnit<T, T>) {
    if (canNOTForeach2(length)) {
        return
    }
    for (i in (0 until length step 2)) {
        val first = getter(i)
        val second = getter(i + 1)
        action(i, first, second)
    }
}

inline fun <T, U> GenericCollectionExtensions.mapEach2(
        length: Int,
        crossinline getter: GenericGetterIndexMethod<T>,
        mapper: Function2<T, T, U>): List<U> =
        mapEach2Indexed(length, getter, { _, first, second ->
            mapper(first, second)
        })

inline fun <T, U> GenericCollectionExtensions.mapEach2Indexed(
        length: Int,
        crossinline getter: GenericGetterIndexMethod<T>,
        mapper: Function3<Int, T, T, U>): List<U> {
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

/**
 * Tells if we are able to perform any actions (foreach2) on the given length of a "collection"
 * @receiver GenericCollectionExtensions
 * @param length Int
 * @return Boolean
 */
inline fun GenericCollectionExtensions.canNOTForeach2(length: Int): Boolean {
    return (length <= 0 || length.isOdd)
}