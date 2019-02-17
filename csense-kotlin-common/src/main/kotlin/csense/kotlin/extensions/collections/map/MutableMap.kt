@file:Suppress("unused", "NOTHING_TO_INLINE")

package csense.kotlin.extensions.collections.map


/**
 * Removes all values by the given predicate
 * @receiver MutableMap<K, V> the map to mutate
 * @param predicate Function1<Map.Entry<K, V>, Boolean> if the predicate returns true then the element will be removed
 *
 */
inline fun <K, V> MutableMap<K, V>.removeAll(
        crossinline predicate: Function1<Map.Entry<K, V>, Boolean>
) {
    //find all to remove, and get the key
    val toRemove = entries.filter(predicate).mapKeys()
    toRemove.forEach { remove(it) }
}


/**
 * Conveinces for setting subLists iff they are not empty.
 * @receiver MutableMap<K, V>
 * @param key K
 * @param value V
 */
fun <K, V : Iterable<*>> MutableMap<K, V>.setIfNotEmpty(key: K, value: V) {
    if (value.any()) {
        this[key] = value
    }
}