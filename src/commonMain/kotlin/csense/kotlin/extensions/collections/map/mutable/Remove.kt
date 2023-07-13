@file:Suppress("NOTHING_TO_INLINE")
package csense.kotlin.extensions.collections.map.mutable

import csense.kotlin.extensions.collections.*
import csense.kotlin.extensions.collections.iterable.mapEntry.*


/**
 * Removes all values by the given predicate
 * @receiver [MutableMap]<K, V> the map to mutate
 * @param predicate [Function1]<[Map.Entry]<K, V>, [Boolean]> if the predicate returns true then the element will be removed
 *
 */
public inline fun <K, V> MutableMap<K, V>.removeAll(
    crossinline predicate: Predicate<Map.Entry<K, V>>
) {
    val toRemove: List<K> = entries.filter(predicate).mapKeys()
    toRemove.forEach(::remove)
}


public inline fun <Key, Value> MutableMap<Key, Value>.remove(key: Key?): Value? {
    return key?.let(::remove)
}
