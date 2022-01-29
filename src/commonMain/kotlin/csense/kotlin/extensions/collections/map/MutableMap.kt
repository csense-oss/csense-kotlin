@file:Suppress("unused", "NOTHING_TO_INLINE")

package csense.kotlin.extensions.collections.map

import csense.kotlin.*
import kotlinx.coroutines.*
import kotlin.collections.set


/**
 * Removes all values by the given predicate
 * @receiver [MutableMap]<K, V> the map to mutate
 * @param predicate [Function1]<[Map.Entry]<K, V>, [Boolean]> if the predicate returns true then the element will be removed
 *
 */
public inline fun <K, V> MutableMap<K, V>.removeAll(
    crossinline predicate: Function1<Map.Entry<K, V>, Boolean>
) {
    //find all to remove, and get the key
    val toRemove = entries.filter(predicate).mapKeys()
    toRemove.forEach { remove(it) }
}


/**
 * Conveniences for setting subLists iff they are not empty.
 * @receiver [MutableMap]<K, V>
 * @param key K
 * @param value V
 */
public inline fun <K, V : Iterable<*>> MutableMap<K, V>.setIfNotEmpty(key: K, value: V) {
    if (value.any()) {
        this[key] = value
    }
}

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

/**
 * Creates a reversed map where value -> key
 * @return the reversed map
 */
public inline fun <Key, Value> MutableMap<Key, Value>.reverse(): MutableMap<Value, Key> {
    val result = LinkedHashMap<Value, Key>(size)
    entries.forEach {
        result[it.value] = it.key
    }
    return result
}


public inline fun <Key, Value> MutableMap<Key, Value>.remove(key: Key?): Value? {
    return key?.let(::remove)
}

public inline fun <Key, Value> MutableMap<Key, Value>.put(entry: Map.Entry<Key, Value>): Value? {
    return put(entry.key, entry.value)
}