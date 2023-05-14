@file:Suppress("NOTHING_TO_INLINE")

package csense.kotlin.extensions.collections.iterable

import csense.kotlin.*


/**
 * selects the [first] item in the array or if [isEmpty] takes [other]
 * @receiver Array<T>
 * @param other T
 * @return T
 */
public inline fun <T> Iterable<T>.firstOr(other: T): T {
    return firstOrNull() ?: other
}

/**
 * selects the [first] item in the array or if [isEmpty] executes the [creation] for it
 * @receiver Array<T>
 * @param creation Function0R
 * @return T
 */
public inline fun <T> Iterable<T>.firstOr(creation: Function0R<T>): T =
    firstOrNull() ?: creation()
