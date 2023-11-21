@file:Suppress("unused", "INVISIBLE_MEMBER", "NOTHING_TO_INLINE")

package csense.kotlin.extensions.collections.map.mutable

import csense.kotlin.specificExtensions.collections.map.*


/**
 * Creates a new reversed map where value -> key
 * @return the reversed map
 */
public inline fun <Key, Value> MutableMap<Key, Value>.reverseKeyValue(): MutableMap<Value, Key> =
    mappings.reverseKeyValue()

