@file:Suppress("NOTHING_TO_INLINE")

package csense.kotlin.extensions.collections.iterable

import csense.kotlin.extensions.collections.*
import csense.kotlin.extensions.collections.generic.collection.*
import csense.kotlin.extensions.collections.generic.collection.operations.*

public inline fun <Item> Iterable<Item>.some(predicate: Predicate<Item>): SatisfyPredicateResult {
    //TODO THIS IS VERY SUBOPTIMAL
    return GenericCollections.satisfyPredicate(
        predicate = predicate,
        length = count(),
        getElement = ::elementAt
    )
}

