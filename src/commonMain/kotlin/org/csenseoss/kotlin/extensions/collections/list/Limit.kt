@file:Suppress("NOTHING_TO_INLINE")
package org.csenseoss.kotlin.extensions.collections.list

import org.csenseoss.kotlin.annotations.numbers.limit.*
import org.csenseoss.kotlin.extensions.primitives.int.*

/**
 * Returns a limited view of this list, by limiting the size of it (if the list is shorter than the limit,
 * then the result will be the lists' length).
 * @receiver [List]<E>
 * @param [size] Int
 * @return [List]<E>
 */

public inline fun <E> List<E>.limitToSize(@IntLimit(from = 0) size: Int): List<E> {
    if (size.isNegativeOrZero) {
        return emptyList()
    }
    return subList(0, size.coerceAtMost(this.size))
}