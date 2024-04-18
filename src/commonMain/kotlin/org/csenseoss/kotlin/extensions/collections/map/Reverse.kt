@file:Suppress("NOTHING_TO_INLINE")
package org.csenseoss.kotlin.extensions.collections.map

import org.csenseoss.kotlin.specificExtensions.collections.map.*


/**
 * Creates a reversed map where value -> key
 * @return the reversed map
 */
public inline fun <Key, Value> Map<Key, Value>.reverseKeyValue(): Map<Value, Key> = mappings.reverseKeyValue()