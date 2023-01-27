@file:Suppress("unused", "NOTHING_TO_INLINE", "INVISIBLE_MEMBER", "INVISIBLE_REFERENCE")

package csense.kotlin.extensions.collections.map.mutable

import csense.kotlin.extensions.collections.iterable.*
import csense.kotlin.extensions.nullabillity.*

public inline fun <Key, Value> MutableMap<Key, MutableList<Value>>.appendValues(
    other: Map<Key, Iterable<Value>>
) {
    other.forEach {
        this.getOrPut(it.key, ::mutableListOf).addAll(it.value)
    }
}

public inline fun <Key, Value> MutableMap<Key, List<Value>>.removeOnEmptyValue(key: Key): Boolean {
    val hasContent = get(key).isNotNullOrEmpty()
    return when {
        hasContent -> false
        else -> remove(key).isNotNull
    }
}