@file:Suppress("unused", "NOTHING_TO_INLINE", "INVISIBLE_MEMBER", "INVISIBLE_REFERENCE")

package csense.kotlin.extensions.collections.array.generic

import csense.kotlin.classes.general.*
import csense.kotlin.extensions.collections.*

public inline fun <T> Array<T>.anyIndexed(predicate: PredicateIndexed<T>): Boolean {
    val index = IncrementalCounter(start = 0)
    return any { it: T ->
        predicate(index.valueAndIncrement, it)
    }
}