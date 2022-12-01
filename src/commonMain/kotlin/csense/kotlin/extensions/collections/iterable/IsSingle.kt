@file:Suppress("NOTHING_TO_INLINE")

package csense.kotlin.extensions.collections.iterable

import csense.kotlin.extensions.collections.*
import csense.kotlin.extensions.collections.collection.*
import csense.kotlin.extensions.collections.iterator.*

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
