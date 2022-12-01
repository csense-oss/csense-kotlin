@file:Suppress("NOTHING_TO_INLINE")

package csense.kotlin.extensions.collections.iterable


/**
 * Tells if this iterable is empty
 * @receiver [Iterable]<T>
 * @return [Boolean] false if it has "next" true otherwise
 */
public inline fun <T> Iterable<T>.isEmpty(): Boolean = !isNotEmpty()

/**
 * Tells if this iterable is not empty
 * @receiver [Iterable]<T>
 * @return [Boolean] true if it has "next" false otherwise
 */
public inline fun <T> Iterable<T>.isNotEmpty(): Boolean = any()