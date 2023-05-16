package csense.kotlin.extensions.collections.map.mutable

import csense.kotlin.extensions.collections.map.*


/**
 * Creates the sublist iff missing, and appends the given value to the sublist
 * @receiver [MutableMap]<K, [MutableList]<V>>
 * @param key K
 * @param value V
 */
public inline fun <K, V> MutableMap<K, MutableList<V>>.putSubList(key: K, value: V) {
    getOrPut(key, ::mutableListOf).add(value)
}

public inline fun <K, V> MutableMap<K, V>.putIfMissing(key: K, value: V) {
    if (doesNotContainKey(key)) {
        put(key, value)
    }
}

public inline fun <K, V> MutableMap<K, V>.putIfMissingAnd(
    key: K,
    value: V,
    action: MutableMap<K, V>.(key: K, value: V) -> Unit
) {
    if (doesNotContainKey(key)) {
        put(key, value)
        action(key, value)
    }
}

public inline fun <Key, Value> MutableMap<Key, Value>.put(entry: Map.Entry<Key, Value>): Value? {
    return put(entry.key, entry.value)
}
