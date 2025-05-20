@file:Suppress("NOTHING_TO_INLINE")

package org.csenseoss.kotlin.extensions.collections.map.linkedHashMap

import kotlin.jvm.*

public inline fun <Key, Value> LinkedHashMap(
    items: List<Map.Entry<Key, Value>>
): LinkedHashMap<Key, Value> = LinkedHashMap<Key, Value>(
    /*initialCapacity =*/ items.size
).apply {
    items.forEach { put(it.key, it.value) }
}

@JvmName("LinkedHashMapPairs")
public inline fun <Key, Value> LinkedHashMap(
    items: List<Pair<Key, Value>>
): LinkedHashMap<Key, Value> = LinkedHashMap<Key, Value>(
    /*initialCapacity =*/ items.size
).apply {
    items.forEach { put(it.first, it.second) }
}