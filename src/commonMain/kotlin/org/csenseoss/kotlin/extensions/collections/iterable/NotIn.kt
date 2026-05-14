@file:Suppress("NOTHING_TO_INLINE")

package org.csenseoss.kotlin.extensions.collections.iterable

/**
 * A named version of the "!in" operator
 * @param iterable [Iterable]<[T]> the collection to search for [this]
 * @return true if found, false otherwise
 */
public inline fun <T : Comparable<T>> T.notIn(
    iterable: Iterable<T>
): Boolean {
    return !iterable.contains(this)
}