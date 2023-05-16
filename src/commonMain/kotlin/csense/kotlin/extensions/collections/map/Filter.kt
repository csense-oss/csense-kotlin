package csense.kotlin.extensions.collections.map

import csense.kotlin.extensions.collections.*


/**
 * Filters by the given predicate and maps out the key
 * @receiver [Map]<K, V>
 * @param predicate [Function1]<[Map.Entry]<K, V>, Boolean>
 * @return [List]<K>
 */
public inline fun <K, V> Map<K, V>.filterMapKey(crossinline predicate: Predicate<Map.Entry<K, V>>): List<K> =
    filter(predicate).map(Map.Entry<K, V>::key)
