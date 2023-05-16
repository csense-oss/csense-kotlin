@file:Suppress("NOTHING_TO_INLINE")

package csense.kotlin.extensions.collections.iterable

import csense.kotlin.extensions.primitives.int.*


/**
 * Returns a list containing first [count] elements.
 * @returns null if [count] is negative.
 * if count is 0 [emptyList] is returned.
 * if it is above 0 then it will take the available items in this receiver (if any)
 * @see take for more info
 */
public inline fun <T> Iterable<T>.takeOrNull(count: Int): List<T>? = when {
    count.isNegative -> null
    else -> take(count)
}
