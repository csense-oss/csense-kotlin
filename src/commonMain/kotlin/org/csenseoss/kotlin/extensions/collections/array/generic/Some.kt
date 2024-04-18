@file:Suppress("unused", "NOTHING_TO_INLINE", "INVISIBLE_MEMBER")

package org.csenseoss.kotlin.extensions.collections.array.generic

import org.csenseoss.kotlin.extensions.collections.*
import org.csenseoss.kotlin.extensions.collections.generic.collection.*
import org.csenseoss.kotlin.extensions.collections.generic.collection.operations.*


public inline fun <Item> Array<Item>.satisfyPredicate(
    predicate: Predicate<Item>
): SatisfyPredicateResult {
    return GenericCollections.satisfyPredicate(
        predicate = predicate,
        length = size,
        getElement = ::elementAt
    )
}