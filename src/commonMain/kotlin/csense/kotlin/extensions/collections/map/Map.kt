@file:Suppress("unused", "NOTHING_TO_INLINE")

package csense.kotlin.extensions.collections.map

import csense.kotlin.*
import csense.kotlin.classes.*
import csense.kotlin.extensions.collections.generic.*
import kotlin.experimental.*


/**
 * Iterates all entries in the this map, given the current index.
 * Missing from the standard library
 * @receiver [Map]<K, V>
 * @param action ([Map.Entry]<K, V>, [Int]) -> [Unit]
 */
public inline fun <K, V> Map<K, V>.forEachIndexed(action: (Map.Entry<K, V>, Int) -> Unit) {
    var i = 0
    forEach {
        action(it, i)
        i += 1
    }
}


//region Generic collection extensions
/**
 * Performs traversal in pairs of 2  (with the first index as well)
 * @receiver [List]<T>
 * @param action [Function2IndexedUnit]<T, T>
 */
@Deprecated("will be removed")
@Suppress("MissingTestFunction")
public inline fun <K, V> Map<K, V>.foreach2Indexed(action: Function2IndexedUnit<Map.Entry<K, V>, Map.Entry<K, V>>): Unit =
    GenericCollectionExtensions.forEach2Indexed(size, this.entries::elementAt, action)

/**
 * Performs traversal in pairs of 2
 * @receiver [List]<T>
 * @param action [Function2Unit]<T, T>
 */
@Deprecated("will be removed")
@Suppress("MissingTestFunction")
public inline fun <K, V> Map<K, V>.foreach2(action: Function2Unit<Map.Entry<K, V>, Map.Entry<K, V>>): Unit =
    GenericCollectionExtensions.forEach2(size, this.entries::elementAt, action)

/**
 * Performs backwards traversal on this
 * @receiver [List]<T>
 * @param action [FunctionUnit]<T>
 */
public inline fun <K, V> Map<K, V>.foreachBackwards(action: FunctionUnit<Map.Entry<K, V>>): Unit =
    GenericCollectionExtensions.forEachBackwards(size, this.entries::elementAt, action)
//endregion

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
@OptIn(ExperimentalStdlibApi::class, ExperimentalTypeInference::class)
@OverloadResolutionByLambdaReturnType
public inline fun <OrgKey, OrgValue, NewKey, NewValue> Map<OrgKey, OrgValue>.toMapViaMapEntry(
    mapEntry: Function1<Map.Entry<OrgKey, OrgValue>, MapEntry<NewKey, NewValue>>
): Map<NewKey, NewValue> {
    val entriesToMap = entries
    return buildMap(capacity = entriesToMap.size) {
        entriesToMap.forEach {
            this += mapEntry(it)
        }
    }
}

/**
 *
 * Notice the [Pair.first] is the new key and [Pair.second] is the value
 * @receiver [Map]<OrgKey, OrgValue>
 * @param mapEntryToPair [Function1]<[Map.Entry]<OrgKey, OrgValue>, [Pair]<NewKey, NewValue>>
 * @return [Map]<NewKey, NewValue>
 */
@OptIn(ExperimentalStdlibApi::class, ExperimentalTypeInference::class)
@OverloadResolutionByLambdaReturnType
public inline fun <OrgKey, OrgValue, NewKey, NewValue> Map<OrgKey, OrgValue>.toMapViaKeyValuePair(
    mapEntryToPair: Function1<Map.Entry<OrgKey, OrgValue>, Pair<NewKey, NewValue>>
): Map<NewKey, NewValue> {
    val entriesToMap = entries
    return buildMap(capacity = entriesToMap.size) {
        entriesToMap.forEach {
            this += mapEntryToPair(it)
        }
    }
}
