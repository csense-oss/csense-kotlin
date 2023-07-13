@file:Suppress("NOTHING_TO_INLINE")

package csense.kotlin.extensions.collections.list

import csense.kotlin.extensions.mapping.*


/**
 * Combines the inner list of the other with this.
 * The length will always match the longest of the receiver and the other parameter.
 * @receiver [List]<[List]<T>>
 * @param other [List]<[List]<T>>
 * @return [List]<[List]<T>>
 */
public inline fun <T> List<List<T>>.combine(
    other: List<List<T>>
): List<List<T>> {
    val isOtherLarger: Boolean = other.size > this.size
    val largest: List<List<T>> = isOtherLarger.map(ifTrue = other, ifFalse = this)
    val smallest: List<List<T>> = isOtherLarger.map(ifTrue = this, ifFalse = other)
    return largest.mapIndexed { index: Int, list: List<T> ->
        list + smallest.getOrNull(index).orEmpty()
    }
}
