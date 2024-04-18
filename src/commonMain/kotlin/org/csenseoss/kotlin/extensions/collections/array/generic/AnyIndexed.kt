@file:Suppress("unused", "NOTHING_TO_INLINE", "INVISIBLE_MEMBER")

package org.csenseoss.kotlin.extensions.collections.array.generic

import org.csenseoss.kotlin.classes.general.*
import org.csenseoss.kotlin.extensions.collections.*

public inline fun <T> Array<T>.anyIndexed(predicate: PredicateIndexed<T>): Boolean {
    val index = IncrementalCounter(start = 0)
    return any { it: T ->
        predicate(
            /* index = */ index.valueAndIncrement,
            /* item = */  it
        )
    }
}