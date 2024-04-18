package org.csenseoss.kotlin.extensions.collections.generic.collection.operations

import org.csenseoss.kotlin.extensions.collections.*
import org.csenseoss.kotlin.extensions.collections.generic.collection.*
import org.csenseoss.kotlin.extensions.ranges.progressions.*

public inline fun <T> GenericCollections.indexOfLastIndexedOrNull(
    startIndex: Int,
    length: Int,
    getElement: GenericGetterIndexMethod<T>,
    predicate: PredicateIndexed<T>
): Int? {
    return indexOfIndexedOrNull(
        byRange = (length - startIndex).lengthTo(endInclusive = 0),
        getElement = getElement,
        predicate = predicate
    )
}