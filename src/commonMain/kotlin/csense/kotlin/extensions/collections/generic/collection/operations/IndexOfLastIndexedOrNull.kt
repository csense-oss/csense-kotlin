package csense.kotlin.extensions.collections.generic.collection.operations

import csense.kotlin.extensions.collections.*
import csense.kotlin.extensions.collections.generic.collection.*
import csense.kotlin.extensions.ranges.progressions.*

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