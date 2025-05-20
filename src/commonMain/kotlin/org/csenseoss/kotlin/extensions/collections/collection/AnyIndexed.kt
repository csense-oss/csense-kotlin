package org.csenseoss.kotlin.extensions.collections.collection

import org.csenseoss.kotlin.classes.general.counter.*
import org.csenseoss.kotlin.extensions.collections.*

public fun <E> Collection<E>.anyIndexed(
    predicate: PredicateIndexed<E>,
): Boolean {
    val index: IncrementalCounter = IncrementalCounter(start = 0)
    return any { it: E ->
        predicate(index.valueAndIncrement, it)
    }
}