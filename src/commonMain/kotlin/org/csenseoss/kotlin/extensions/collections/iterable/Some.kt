@file:Suppress("NOTHING_TO_INLINE")

package org.csenseoss.kotlin.extensions.collections.iterable

import org.csenseoss.kotlin.extensions.collections.*
import org.csenseoss.kotlin.extensions.collections.generic.collection.*
import org.csenseoss.kotlin.extensions.collections.generic.collection.operations.*

public inline fun <Item> Iterable<Item>.some(predicate: Predicate<Item>): SatisfyPredicateResult {
    //TODO THIS IS VERY SUBOPTIMAL
    return GenericCollections.satisfyPredicate(
        predicate = predicate,
        length = count(),
        getElement = ::elementAt
    )
}