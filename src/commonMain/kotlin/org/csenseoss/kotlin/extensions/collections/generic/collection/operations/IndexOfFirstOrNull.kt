@file:Suppress("UnusedReceiverParameter")

package org.csenseoss.kotlin.extensions.collections.generic.collection.operations

import org.csenseoss.kotlin.annotations.numbers.limit.*
import org.csenseoss.kotlin.extensions.collections.generic.collection.*

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