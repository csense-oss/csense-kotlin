@file:Suppress("NOTHING_TO_INLINE")

package csense.kotlin.classes.map

/**
 * A simple data class of a [Map.Entry]
 * @param Key
 * @param Value
 * @property key Key
 * @property value Value
 */
public data class MapEntry<Key, Value>(
    override val key: Key,
    override val value: Value
) : Map.Entry<Key, Value>

/**
 *
 * @receiver MutableMap<Key, Value>
 * @param mapEntry MapEntry<Key, Value>
 */
public inline operator fun <Key, Value> MutableMap<Key, Value>.plusAssign(mapEntry: MapEntry<Key, Value>) {
    put(mapEntry.key, mapEntry.value)
}

/**
 *
 * @receiver MutableMap<Key, Value>
 * @param mapEntry MapEntry<Key, Value>
 */
public inline operator fun <Key, Value> MutableMap<Key, Value>.minusAssign(mapEntry: MapEntry<Key, Value>) {
    remove(mapEntry.key)
}

