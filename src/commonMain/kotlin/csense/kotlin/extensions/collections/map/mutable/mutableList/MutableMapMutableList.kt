@file:Suppress("unused", "NOTHING_TO_INLINE", "INVISIBLE_MEMBER")

package csense.kotlin.extensions.collections.map.mutable.mutableList

import csense.kotlin.extensions.collections.iterable.*
import csense.kotlin.extensions.nullabillity.*

public inline fun <Key, Value> MutableMap<Key, MutableList<Value>>.appendValues(
    other: Map<Key, Iterable<Value>>
) {
    other.forEach { it: Map.Entry<Key, Iterable<Value>> ->
        this.getOrPut(key = it.key, defaultValue = ::mutableListOf).addAll(it.value)
    }
}

public inline fun <Key, Value> MutableMap<Key, List<Value>>.removeOnEmptyValue(key: Key): Boolean {
    val hasSubContent: Boolean = get(key).isNotNullOrEmpty()
    return when {
        hasSubContent -> false
        else -> remove(key).isNotNull
    }
}