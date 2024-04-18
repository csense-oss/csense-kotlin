@file:Suppress("NOTHING_TO_INLINE")

package org.csenseoss.kotlin.extensions.collections.iterable

import org.csenseoss.kotlin.extensions.collections.iterator.*

public inline fun Iterable<*>.isSingle(): Boolean {
    val itt = iterator()
    if (itt.isAtEnd()) {
        return false
    }
    itt.next()
    return itt.isAtEnd()
}

public inline fun Iterable<*>.isSingleOrEmpty(): Boolean = !anyIndexed { index, _ ->
    index >= 1
}