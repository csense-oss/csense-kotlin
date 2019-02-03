@file:Suppress("unused", "NOTHING_TO_INLINE")

package csense.kotlin.extensions.collections.map

import csense.kotlin.*
import csense.kotlin.extensions.collections.generic.*
import kotlin.Function1


/**
 * Iterates all entries in the this map, given the current index.
 * Missing from the std lib
 * @receiver Map<K, V>
 * @param action (Map.Entry<K, V>, Int) -> Unit
 */
fun <K, V> Map<K, V>.forEachIndexed(action: (Map.Entry<K, V>, Int) -> Unit) {
    var i = 0
    forEach {
        action(it, i)
        i += 1
    }
}


//region Generic collection extensions
/**
 * Performs traversal in pairs of 2  (with the first index as well)
 * @receiver List<T>
 * @param action Function2IndexedUnit<T, T>
 */
inline fun <K, V> Map<K, V>.foreach2Indexed(action: Function2IndexedUnit<Map.Entry<K, V>, Map.Entry<K, V>>) =
        GenericCollectionExtensions.forEach2Indexed(size, this.entries::elementAt, action)

/**
 * Performs traversal in pairs of 2
 * @receiver List<T>
 * @param action Function2Unit<T, T>
 */
inline fun <K, V> Map<K, V>.foreach2(action: Function2Unit<Map.Entry<K, V>, Map.Entry<K, V>>) =
        GenericCollectionExtensions.forEach2(size, this.entries::elementAt, action)

/**
 * Performs backwards traversal on this
 * @receiver List<T>
 * @param action FunctionUnit<T>
 */
inline fun <K, V> Map<K, V>.foreachBackwards(action: FunctionUnit<Map.Entry<K, V>>) =
        GenericCollectionExtensions.forEachBackwards(size, this.entries::elementAt, action)
//endregion

/**
 * Filters by the given predicate and maps out the key
 * @receiver Map<K, V>
 * @param predicate Function1<Map.Entry<K, V>, Boolean>
 * @return List<K>
 */
inline fun <K, V> Map<K, V>.filterMapKey(crossinline predicate: Function1<Map.Entry<K, V>, Boolean>): List<K> =
        filter(predicate).map(Map.Entry<K, V>::key)

/**
 * maps the abililty to get an entry and use it safely or do something else.
 * @receiver Map<K, V>
 * @param key K
 * @param onKeyFound FunctionUnit<V> if the key is there / found then performs the given action with the value
 * @param onKeyNotFound EmptyFunction if the key is not there, then this function gets invoked.
 */
inline fun <K, V> Map<K, V>.useValueOr(
        key: K,
        onKeyFound: FunctionUnit<V>,
        onKeyNotFound: EmptyFunction) {
    val value = this[key]
    if (value != null) {
        onKeyFound(value)
    } else {
        onKeyNotFound()
    }
}
