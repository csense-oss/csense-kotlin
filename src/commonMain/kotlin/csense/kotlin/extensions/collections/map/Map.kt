@file:Suppress("unused", "NOTHING_TO_INLINE")


package csense.kotlin.extensions.collections.map

import csense.kotlin.*
import csense.kotlin.classes.*
import csense.kotlin.extensions.collections.generic.*
import csense.kotlin.specificExtensions.collections.map.*
import kotlin.contracts.*
import kotlin.experimental.*


/**
 * Iterates over all entries in the map, with the current index.
 * Missing from the standard library
 * @receiver [Map]<K, V>
 * @param action ([Map.Entry]<K, V>, [Int]) -> [Unit]
 */
public inline fun <K, V> Map<K, V>.forEachIndexed(action: (entry: Map.Entry<K, V>, index: Int) -> Unit) {
    var i = 0
    forEach {
        action(it, i)
        i += 1
    }
}

/**
 * Performs backwards traversal on each entry
 * @receiver [List]<T>
 * @param action [FunctionUnit]<T>
 */
public inline fun <K, V> Map<K, V>.foreachBackwards(action: FunctionUnit<Map.Entry<K, V>>): Unit =
    GenericCollectionExtensions.forEachBackwards(size, this.entries::elementAt, action)

/**
 * Filters by the given predicate and maps out the key
 * @receiver [Map]<K, V>
 * @param predicate [Function1]<[Map.Entry]<K, V>, Boolean>
 * @return [List]<K>
 */
public inline fun <K, V> Map<K, V>.filterMapKey(crossinline predicate: Function1<Map.Entry<K, V>, Boolean>): List<K> =
    filter(predicate).map(Map.Entry<K, V>::key)

/**
 * maps the ability to get an entry and use it safely or do something else.
 * @receiver [Map]<K, V>
 * @param key K
 * @param onKeyFound [FunctionUnit]<V> if the key is there / found then performs the given action with the value
 * @param onKeyNotFound [EmptyFunction] if the key is not there, then this function gets invoked.
 */
public inline fun <K, V> Map<K, V>.useValueOr(
    key: K,
    onKeyFound: FunctionUnit<V>,
    onKeyNotFound: EmptyFunction
) {
    contract {
        callsInPlace(onKeyFound, InvocationKind.AT_MOST_ONCE)
        callsInPlace(onKeyNotFound, InvocationKind.AT_MOST_ONCE)
    }
    val value = this[key]
    if (value != null) {
        onKeyFound(value)
    } else {
        onKeyNotFound()
    }
}

/**
 * Tells if the given [key] is not in the map
 * @receiver [Map]<K, V> the map to test for the given key
 * @param key K the key to test existence of
 * @return [Boolean] true if the key is not found / contained
 */
public inline fun <K, V> Map<K, V>.doesNotContainKey(key: K): Boolean =
    !containsKey(key)

/**
 * creates a new map by mapping this map
 * @receiver [Map]<OrgKey, OrgValue>
 * @param mapEntry [Function1]<[Map.Entry]<OrgKey, OrgValue>, [MapEntry]<NewKey, NewValue>>
 * @return [Map]<NewKey, NewValue>
 */
@OptIn(ExperimentalTypeInference::class)
@OverloadResolutionByLambdaReturnType
public inline fun <OrgKey, OrgValue, NewKey, NewValue> Map<OrgKey, OrgValue>.toMapViaMapEntry(
    mapEntry: Function1<Map.Entry<OrgKey, OrgValue>, MapEntry<NewKey, NewValue>>
): Map<NewKey, NewValue> = mappings.mapEachEntryWith(LinkedHashMap(size)) { entry: Map.Entry<OrgKey, OrgValue> ->
    this += mapEntry(entry)
}

/**
 *
 * Notice the [Pair.first] is the new key and [Pair.second] is the value
 * @receiver [Map]<OrgKey, OrgValue>
 * @param mapEntryToPair [Function1]<[Map.Entry]<OrgKey, OrgValue>, [Pair]<NewKey, NewValue>>
 * @return [Map]<NewKey, NewValue>
 */
@OptIn(ExperimentalTypeInference::class)
@OverloadResolutionByLambdaReturnType
public inline fun <OrgKey, OrgValue, NewKey, NewValue> Map<OrgKey, OrgValue>.toMapViaKeyValuePair(
    mapEntryToPair: Function1<Map.Entry<OrgKey, OrgValue>, Pair<NewKey, NewValue>>
): Map<NewKey, NewValue> = mappings.mapEachEntryWith(LinkedHashMap(size)) { entry: Map.Entry<OrgKey, OrgValue> ->
    this += mapEntryToPair(entry)
}

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

/**
 * Creates a reversed map where value -> key
 * @return the reversed map
 */
public inline fun <Key, Value> Map<Key, Value>.reverseKeyValue(): Map<Value, Key> = mappings.reverseKeyValue()
