@file:Suppress("UnusedReceiverParameter")

package csense.kotlin.extensions.collections.generic.collection.operations

import csense.kotlin.extensions.collections.*
import csense.kotlin.extensions.collections.generic.collection.*
import csense.kotlin.extensions.mapping.*

//TODO BETTER NAME!?!?!

public enum class CollectionPredicate {
    None,
    Some,
    All
}


public fun <T> GenericCollections.satisfyPredicate(
    predicate: Predicate<T>,
    items: List<T>
): CollectionPredicate {
    var result: CollectionPredicate? = null
    items.forEach {
        result = result.combine(predicate(it))
        if (result == CollectionPredicate.Some) {
            return@satisfyPredicate CollectionPredicate.Some
        }
    }
    return result ?: CollectionPredicate.None
}


public fun CollectionPredicate?.combine(predicateResult: Boolean): CollectionPredicate = when (this) {
    null -> predicateResult.map(ifTrue = CollectionPredicate.All, ifFalse = CollectionPredicate.None)
    CollectionPredicate.Some -> CollectionPredicate.Some
    CollectionPredicate.None -> predicateResult.map(
        ifTrue = CollectionPredicate.Some,
        ifFalse = CollectionPredicate.None
    )

    CollectionPredicate.All -> predicateResult.map(
        ifTrue = CollectionPredicate.All,
        ifFalse = CollectionPredicate.Some
    )
}