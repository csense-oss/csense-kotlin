@file:Suppress("NOTHING_TO_INLINE")

package csense.kotlin.extensions.collections.map.linkedHashMap

import csense.kotlin.classes.map.*
import kotlin.jvm.*

public inline fun <Key, Value> LinkedHashMap(
    items: List<MapEntry<Key, Value>>
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