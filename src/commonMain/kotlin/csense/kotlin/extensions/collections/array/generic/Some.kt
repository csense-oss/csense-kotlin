@file:Suppress("unused", "NOTHING_TO_INLINE", "INVISIBLE_MEMBER", "INVISIBLE_REFERENCE")

package csense.kotlin.extensions.collections.array.generic

import csense.kotlin.extensions.collections.*
import csense.kotlin.extensions.collections.generic.collection.*
import csense.kotlin.extensions.collections.generic.collection.operations.*


public inline fun <Item> Array<Item>.satisfyPredicate(
    predicate: Predicate<Item>
): SatisfyPredicateResult {
    return GenericCollections.satisfyPredicate(
        predicate = predicate,
        length = size,
        getElement = ::elementAt
    )
}
