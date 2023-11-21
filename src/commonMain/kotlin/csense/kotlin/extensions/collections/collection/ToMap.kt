@file:Suppress("unused", "NOTHING_TO_INLINE", "INVISIBLE_MEMBER")

package csense.kotlin.extensions.collections.collection

import csense.kotlin.specificExtensions.collections.collection.*


/**
 * Maps this [Collection] to a [Map] with the given [keyMapper]
 * If multiple elements map to the same key, the last one "wins"
 * @receiver Collection<Item>
 * @param keyMapper Function1<Item, Key> extracts a key from a given entry
 * @return Map<Key, Item>
 */
public inline fun <Item, Key> Collection<Item>.toMapFlat(
    keyMapper: Function1<Item, Key>
): Map<Key, Item> = toMutableMapFlat(keyMapper)

/**
 * Maps this [Collection] to a [MutableMap] with the given [keyMapper]
 * If multiple elements map to the same key, the last one "wins"
 * @receiver Collection<Item>
 * @param keyMapper Function1<Item, Key> extracts a key from a given entry
 * @return Map<Key, Item>
 */
public inline fun <Item, Key> Collection<Item>.toMutableMapFlat(
    keyMapper: Function1<Item, Key>
): MutableMap<Key, Item> = toMutableMapFlat(keyMapper, valueMapper = { it })


/**
 *
 * If multiple elements map to the same key, the last one "wins"
 *
 */
public inline fun <Item, Key, Value> Collection<Item>.toMapFlat(
    keyMapper: Function1<Item, Key>,
    valueMapper: Function1<Item, Value>
): Map<Key, Value> = toMutableMapFlat(keyMapper, valueMapper)

/**
 *
 * If multiple elements map to the same key, the last one "wins"
 *
 */
public inline fun <Item, Key, Value> Collection<Item>.toMutableMapFlat(
    keyMapper: Function1<Item, Key>,
    valueMapper: Function1<Item, Value>
): MutableMap<Key, Value> = mappings.mapEachItemWith(LinkedHashMap(size)) {
    this[keyMapper(it)] = valueMapper(it)
}

/**
 * Maps this [Collection] to a [Map] with the given [keyMapper]
 * @receiver Collection<Item>
 * @param keyMapper Function1<Item, Key> extracts a key from a given entry
 * @return Map<Key, Item>
 */
public inline fun <Item, Key> Collection<Item>.toMap(
    keyMapper: Function1<Item, Key>
): Map<Key, List<Item>> = toMutableMap(keyMapper)

/**
 * Maps this [Collection] to a [MutableMap] with the given [keyMapper]
 * @receiver Collection<Item>
 * @param keyMapper Function1<Item, Key> extracts a key from a given entry
 * @return Map<Key, Item>
 */
public inline fun <Item, Key> Collection<Item>.toMutableMap(
    keyMapper: Function1<Item, Key>
): MutableMap<Key, MutableList<Item>> = toMutableMap(keyMapper, valueMapper = { it })

/**
 * Converts this collection to a [Map] via the given mapper functions
 * supports multiple values for a given key (hench the result is key -> list<Value>)
 * @receiver Collection<Item>
 * @param keyMapper Function1<Item, Key>
 * @param valueMapper Function1<Item, Value>
 * @return Map<Key, List<Value>>
 */
public inline fun <Item, Key, Value> Collection<Item>.toMap(
    keyMapper: Function1<Item, Key>,
    valueMapper: Function1<Item, Value>
): Map<Key, List<Value>> = toMutableMap(keyMapper, valueMapper)

/**
 * Converts this collection to a [MutableMap] via the given mapper functions
 * supports multiple values for a given key (hench the result is key -> list<Value>)
 * @receiver Collection<Item>
 * @param keyMapper Function1<Item, Key>
 * @param valueMapper Function1<Item, Value>
 * @return Map<Key, MutableList<Value>>
 */
public inline fun <Item, Key, Value> Collection<Item>.toMutableMap(
    keyMapper: Function1<Item, Key>,
    valueMapper: Function1<Item, Value>
): MutableMap<Key, MutableList<Value>> = mappings.mapEachItemWith(LinkedHashMap(size)) { item ->
    val key: Key = keyMapper(item)
    val value: Value = valueMapper(item)
    getOrPut(key, ::mutableListOf).add(value)
}


/**
 * Converts this collection to a [Map] via the given mapper functions
 * does NOT support 1 to many relations (if it happens the [onKeyCollision] will be called to determine the result)
 * @receiver Collection<Item>
 * @param keyMapper Function1<Item, Key>
 * @param valueMapper Function1<Item, Value>
 * @return Map<Key, List<Value>>
 */
public inline fun <Item, Key, Value> Collection<Item>.toUniqueMap(
    keyMapper: Function1<Item, Key>,
    valueMapper: Function1<Item, Value>,
    noinline onKeyCollision: ((first: Value, second: Value) -> Value)? = null
): Map<Key, Value> = toUniqueMutableMap(keyMapper, valueMapper, onKeyCollision)

/**
 * Converts this collection to a [MutableMap] via the given mapper functions
 * does NOT support 1 to many relations (if it happens the [onKeyCollision] will be called to determine the result)
 * @receiver Collection<Item>
 * @param keyMapper Function1<Item, Key>
 * @param valueMapper Function1<Item, Value>
 * @return Map<Key, List<Value>>
 */
public inline fun <Item, Key, Value> Collection<Item>.toUniqueMutableMap(
    keyMapper: Function1<Item, Key>,
    valueMapper: Function1<Item, Value>,
    noinline onKeyCollision: ((first: Value, second: Value) -> Value)? = null
): MutableMap<Key, Value> = mappings.mapEachItemWith(LinkedHashMap(size)) {
    val key: Key = keyMapper(it)
    val value: Value = valueMapper(it)
    val existingKey = this[key]
    val valueToWrite = if (onKeyCollision != null && existingKey != null) {
        onKeyCollision(existingKey, value)
    } else {
        value
    }
    this[key] = valueToWrite
}

/**
 * The same as [map] with the resulting list being a mutable list instead
 * @receiver Collection<Item>
 * @param transform Function1<Item, Result>
 * @return MutableList<Result>
 */
public inline fun <Item, Result> Collection<Item>.mapToMutable(
    transform: (Item) -> Result
): MutableList<Result> = mappings.mapEachItemWith(ArrayList(size)) {
    this += transform(it)
}