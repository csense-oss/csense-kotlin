package org.csenseoss.kotlin.extensions.collections.map

import org.csenseoss.kotlin.*
import org.csenseoss.kotlin.classes.general.*
import org.csenseoss.kotlin.extensions.collections.generic.collection.*
import org.csenseoss.kotlin.extensions.collections.generic.collection.operations.*


/**
 * Iterates over all entries in the map, with the current index.
 * Missing from the standard library
 * @receiver [Map]<K, V>
 * @param action ([Map.Entry]<K, V>, [Int]) -> [Unit]
 */
public inline fun <K, V> Map<K, V>.forEachIndexed(action: (entry: Map.Entry<K, V>, index: Int) -> Unit) {
    val counter = IncrementalCounter(start = 0)
    forEach {
        action(it, counter.valueAndIncrement)
    }
}

/**
 * Performs backwards traversal on each entry
 * @receiver [List]<T>
 * @param action [FunctionUnit]<T>
 */
public inline fun <K, V> Map<K, V>.foreachBackwards(action: FunctionUnit<Map.Entry<K, V>>): Unit =
    GenericCollections.forEachBackwards(size, this.entries::elementAt, action)