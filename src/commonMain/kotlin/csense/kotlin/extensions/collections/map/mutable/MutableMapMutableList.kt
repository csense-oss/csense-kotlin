@file:Suppress("unused", "NOTHING_TO_INLINE", "INVISIBLE_MEMBER", "INVISIBLE_REFERENCE")

package csense.kotlin.extensions.collections.map.mutable

import csense.kotlin.extensions.primitives.boolean.*

public inline fun <Key, Value> MutableMap<Key, MutableList<Value>>.appendValues(
    other: Map<Key, Iterable<Value>>
) {
    other.forEach {
        this.getOrPut(it.key, ::mutableListOf).addAll(it.value)
    }
}

public inline fun <Key, Value> MutableMap<Key, List<Value>>.removeOnEmptyValue(key: Key): Boolean {
    val isEmptyValue = get(key)?.isEmpty() ?: return false
    return isEmptyValue.ifTrue { remove(key) }
}