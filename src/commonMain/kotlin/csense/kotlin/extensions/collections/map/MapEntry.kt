@file:Suppress("unused", "NOTHING_TO_INLINE")

package csense.kotlin.extensions.collections.map


/**
 * Maps the keys from the given [Iterable]
 * @receiver [Iterable]<[Map.Entry]<K, V>>
 * @return [List]<K>
 */
public inline fun <K, V> Iterable<Map.Entry<K, V>>.mapKeys(): List<K> =
    map { it.key }

/**
 * Maps all the values from the given [Iterable]
 * @receiver [Iterable]<[Map.Entry]<K, V>>
 * @return [List]<V>
 */
public inline fun <K, V> Iterable<Map.Entry<K, V>>.mapValues(): List<V> =
    map { it.value }

