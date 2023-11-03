package csense.kotlin.extensions.collections.generic.collection.operations

import csense.kotlin.annotations.numbers.*
import csense.kotlin.extensions.collections.*
import csense.kotlin.extensions.collections.generic.collection.*

@IntLimit(from = 0)
public inline fun <T> GenericCollections.indexOfFirstIndexedOrNull(
    @IntLimit(from = 0) startIndex: Int,
    @IntLimit(from = 0) length: Int,
    getElement: GenericGetterIndexMethod<T>,
    predicate: PredicateIndexed<T>
): Int? {
    return indexOfIndexedOrNull(
        byRange = startIndex.rangeUntil(length),
        getElement = getElement,
        predicate = predicate
    )
}
