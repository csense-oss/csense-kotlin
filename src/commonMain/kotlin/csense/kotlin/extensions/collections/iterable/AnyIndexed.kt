package csense.kotlin.extensions.collections.iterable

import csense.kotlin.classes.general.*
import csense.kotlin.extensions.collections.*

public inline fun <T> Iterable<T>.anyIndexed(predicate: PredicateIndexed<T>): Boolean {
    val indexCounter = IncrementalCounter(start = 0)
    return any {
        predicate(indexCounter.valueAndIncrement, it)
    }
}