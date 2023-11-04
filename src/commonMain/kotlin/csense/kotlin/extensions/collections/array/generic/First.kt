@file:Suppress("unused", "NOTHING_TO_INLINE", "INVISIBLE_MEMBER", "INVISIBLE_REFERENCE")

package csense.kotlin.extensions.collections.array.generic

import csense.kotlin.*


/**
 * selects the [first] item in the array or if [isEmpty] takes [defaultValue]
 * @receiver Array<T>
 * @param defaultValue T
 * @return T
 */
public inline fun <T> Array<out T>.firstOr(defaultValue: T): T =
    firstOrNull() ?: defaultValue

/**
 * selects the [first] item in the array or if [isEmpty] executes the [defaultValue] for it
 * @receiver Array<T>
 * @param defaultValue Function0R
 * @return T
 */
public inline fun <T> Array<out T>.firstOrBy(defaultValue: Function0R<T>): T =
    firstOrNull() ?: defaultValue()
