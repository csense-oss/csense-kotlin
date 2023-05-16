@file:Suppress("unused", "NOTHING_TO_INLINE")


package csense.kotlin.extensions.collections.map

import csense.kotlin.*
import kotlin.contracts.*

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

