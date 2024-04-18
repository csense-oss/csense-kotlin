@file:Suppress("UnusedReceiverParameter")

package org.csenseoss.kotlin.extensions.collections.generic.collection.operations

import org.csenseoss.kotlin.extensions.collections.generic.collection.*
import org.csenseoss.kotlin.extensions.ranges.progressions.*

public inline fun <T> GenericCollections.indexOfLastOrNull(
    startFromEndIndex: Int,
    length: Int,
    getElement: (index: Int) -> T,
    predicate: (element: T) -> Boolean
): Int? {
    return indexOfOrNull(
        byRange = (length - startFromEndIndex).lengthTo(endInclusive = 0),
        getElement = getElement,
        predicate = predicate
    )
}