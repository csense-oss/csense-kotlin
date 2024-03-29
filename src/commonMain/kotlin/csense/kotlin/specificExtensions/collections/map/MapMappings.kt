@file:Suppress("NOTHING_TO_INLINE")

package csense.kotlin.specificExtensions.collections.map

import kotlin.jvm.*


@JvmInline
public value class MapMappings<Key, Value>(public val map: Map<Key, Value>)

public inline val <Key, Value> Map<Key, Value>.mappings: MapMappings<Key, Value>
    get() = MapMappings(this)

/**
 * Maps each entry with the given [result]
 * @receiver MapMappings<Key, Value>
 * @param result Result
 * @param map Function2<Result, Entry<Key, Value>, Unit>
 * @return Result
 */
public inline fun <Key, Value, Result> MapMappings<Key, Value>.mapEachEntryWith(
    result: Result,
    map: Result.(Map.Entry<Key, Value>) -> Unit
): Result {
    this.map.entries.forEach {
        result.map(it)
    }
    return result
}

public inline fun <Key, Value> MapMappings<Key, Value>.reverseKeyValue(

): MutableMap<Value, Key> = mapEachEntryWith(LinkedHashMap(map.size)) { entry ->
    this[entry.value] = entry.key
}