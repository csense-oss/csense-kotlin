@file:Suppress("unused", "NOTHING_TO_INLINE", "INVISIBLE_MEMBER", "INVISIBLE_REFERENCE")

package csense.kotlin.extensions.collections.array.generic


/**
 * selects the [first] item in the array or if [isEmpty] takes [other]
 * @receiver Array<T>
 * @param other T
 * @return T
 */
public inline fun <T> Array<out T>.firstOr(other: T): T =
    firstOrNull() ?: other

/**
 * selects the [first] item in the array or if [isEmpty] executes the [creation] for it
 * @receiver Array<T>
 * @param creation Function0R
 * @return T
 */
public inline fun <T> Array<out T>.firstOrBy(creation: csense.kotlin.Function0R<T>): T =
    firstOrNull() ?: creation()
