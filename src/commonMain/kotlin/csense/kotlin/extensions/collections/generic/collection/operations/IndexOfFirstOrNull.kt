@file:Suppress("UnusedReceiverParameter")

package csense.kotlin.extensions.collections.generic.collection.operations

import csense.kotlin.annotations.numbers.*
import csense.kotlin.extensions.collections.generic.collection.*

public inline fun <T> GenericCollections.indexOfFirstOrNull(
    @IntLimit(from = 0)
    startIndex: Int,
    @IntLimit(from = 0)
    length: Int,
    getElement: (index: Int) -> T,
    predicate: (element: T) -> Boolean,
): Int? {
    return indexOfOrNull(
        byRange = startIndex.coerceAtLeast(0).rangeUntil(length),
        getElement = getElement,
        predicate = predicate
    )
}
