package csense.kotlin.extensions.collections.map.mutable


/**
 * Conveniences for setting subLists iff they are not empty.
 * @receiver [MutableMap]<K, V>
 * @param key K
 * @param value V
 */
public inline fun <K, V : Iterable<*>> MutableMap<K, V>.setIfNotEmpty(key: K, value: V) {
    if (value.none()) {
        return
    }
    this[key] = value
}
