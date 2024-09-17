@file:Suppress("unused", "UnusedReceiverParameter")

package org.csenseoss.kotlin.extensions.collections.generic.collection.operations

import csense.kotlin.annotations.numbers.*
import org.csenseoss.kotlin.*
import org.csenseoss.kotlin.extensions.collections.*
import org.csenseoss.kotlin.extensions.collections.generic.collection.*
import org.csenseoss.kotlin.extensions.collections.generic.collection.operations.*


/**
 * A very generic foreach where the access of items is delegated (via [retriever]).
 * @receiver [Generic]
 * @param onEach [Function0]<[Element]>
 * @param length [Int]
 * @param retriever [Function1]<[Int], [Element]>
 * @param startIndex [Int]
 */
public inline fun <Element> GenericCollections.forEach(
    @IntLimit(from = 0) length: Int,
    retriever: Function1<@IntLimit(from = 0) Int, Element>,
    @IntLimit(from = 0) startIndex: Int = 0,
    onEach: Function0<Element>
): Unit = mapEachWith(Unit, length, retriever, startIndex) {
    onEach(it)
}

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