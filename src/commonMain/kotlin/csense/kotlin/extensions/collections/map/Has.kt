@file:Suppress("NOTHING_TO_INLINE")
package csense.kotlin.extensions.collections.map



/**
 * Determines whenever the given maps contain the same keys (thus their size as well are equal)
 * @receiver [Map]<Key, Value>
 * @param other [Map]<Key, Value> the other [Map] to inspect
 * @return [Boolean] true if they have the same keys (and size) false otherwise
 */
public inline fun <Key, Value> Map<Key, Value>.hasSameKeys(
    other: Map<Key, Value>
): Boolean {
    return this.size == other.size && all {
        other.containsKey(it.key)
    }
}


public inline fun <Key, Value> Map<Key, Value>.hasSameContent(
    other: Map<Key, Value>
): Boolean where Value : Comparable<Value> {
    return hasSameKeys(other) && all {
        it.value == other[it.key]
    }
}

public inline fun <Key, Value> Map<Key, Value>.hasSameContentBy(
    other: Map<Key, Value>,
    compareValue: Function2<Value, Value, Boolean>
): Boolean {
    return hasSameKeys(other) && all {
        compareValue(it.value, other.getValue(it.key))
    }
}