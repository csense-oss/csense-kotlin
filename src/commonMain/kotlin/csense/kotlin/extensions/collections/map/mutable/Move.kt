@file:Suppress("NOTHING_TO_INLINE")
package csense.kotlin.extensions.collections.map.mutable


/**
 * Attempts to move the given entry (if exists) to the back(if the map supports it)
 * @param key the [Key] of the item to move to the back
 */
public inline fun <Key, Value> MutableMap<Key, Value>.moveToBack(key: Key) {
    val value = this[key] ?: return
    remove(key)
    put(key, value)
}
