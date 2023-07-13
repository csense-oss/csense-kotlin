@file:Suppress("NOTHING_TO_INLINE")
package csense.kotlin.extensions.collections.map


/**
 * Tells if the given [key] is not in the map
 * @receiver [Map]<K, V> the map to test for the given key
 * @param key K the key to test existence of
 * @return [Boolean] true if the key is not found / contained
 */
public inline fun <K, V> Map<K, V>.doesNotContainKey(key: K): Boolean =
    !containsKey(key)
