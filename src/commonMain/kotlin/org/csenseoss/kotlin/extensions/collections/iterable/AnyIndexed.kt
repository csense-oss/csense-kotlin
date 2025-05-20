package org.csenseoss.kotlin.extensions.collections.iterable

import org.csenseoss.kotlin.classes.general.counter.*
import org.csenseoss.kotlin.extensions.collections.*

public inline fun <T> Iterable<T>.anyIndexed(predicate: PredicateIndexed<T>): Boolean {
    val indexCounter = IncrementalCounter(start = 0)
    return any { it: T ->
        predicate(indexCounter.valueAndIncrement, it)
    }
}