@file:Suppress("unused", "INVISIBLE_MEMBER", "INVISIBLE_REFERENCE", "NOTHING_TO_INLINE")

package csense.kotlin.extensions.collections.map.mutable

import csense.kotlin.extensions.collections.*
import csense.kotlin.extensions.collections.map.*
import csense.kotlin.specificExtensions.collections.map.*
import kotlin.collections.set


/**
 * Removes all values by the given predicate
 * @receiver [MutableMap]<K, V> the map to mutate
 * @param predicate [Function1]<[Map.Entry]<K, V>, [Boolean]> if the predicate returns true then the element will be removed
 *
 */
public inline fun <K, V> MutableMap<K, V>.removeAll(
    crossinline predicate: Predicate<Map.Entry<K, V>>
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
 * Creates a new reversed map where value -> key
 * @return the reversed map
 */
public inline fun <Key, Value> MutableMap<Key, Value>.reverseKeyValue(): MutableMap<Value, Key> =
    mappings.reverseKeyValue()

public inline fun <Key, Value> MutableMap<Key, Value>.remove(key: Key?): Value? {
    return key?.let(::remove)
}

public inline fun <Key, Value> MutableMap<Key, Value>.put(entry: Map.Entry<Key, Value>): Value? {
    return put(entry.key, entry.value)
}

/**
 * Attempts to move the given entry (if exists) to the back(if the map supports it)
 * @param key the [Key] of the item to move to the back
 */
public inline fun <Key, Value> MutableMap<Key, Value>.moveToBack(key: Key) {
    val value = this[key] ?: return
    remove(key)
    put(key, value)
}
