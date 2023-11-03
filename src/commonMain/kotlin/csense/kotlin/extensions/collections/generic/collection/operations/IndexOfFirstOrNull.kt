@file:Suppress("UnusedReceiverParameter")

package csense.kotlin.extensions.collections.generic.collection.operations

import csense.kotlin.extensions.collections.generic.collection.*

public inline fun <T> GenericCollections.indexOfFirstOrNull(
    startIndex: Int,
    length: Int,
    getElement: (index: Int) -> T,
    predicate: (element: T) -> Boolean
): Int? {
    return indexOfOrNull(
        byRange = startIndex.rangeUntil(length),
        getElement = getElement,
        predicate = predicate
    )
}
