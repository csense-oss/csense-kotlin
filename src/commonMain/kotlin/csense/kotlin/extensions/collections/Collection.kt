@file:Suppress("unused", "NOTHING_TO_INLINE", "INVISIBLE_MEMBER", "INVISIBLE_REFERENCE")

package csense.kotlin.extensions.collections

import csense.kotlin.*
import csense.kotlin.Function1
import csense.kotlin.annotations.numbers.*
import csense.kotlin.extensions.*
import csense.kotlin.extensions.collections.array.*
import csense.kotlin.extensions.collections.generic.*
import csense.kotlin.extensions.primitives.*
import csense.kotlin.specificExtensions.collections.collection.*
import kotlin.contracts.*

/**
 * Validates the given index for the given collection (so 0 until length)
 * @receiver [Collection]<*>
 * @param index [Int]
 * @return [Boolean]
 */

public inline fun Collection<*>.isIndexValid(index: Int): Boolean =
    index >= 0 && index < count()

/**
 * Validates the given index for the given collection, accounting for inserting in the end (so 0 until (including) length)
 * @receiver [Collection]<*>
 * @param index [Int]
 * @return [Boolean]
 */

public inline fun Collection<*>.isIndexValidForInsert(index: Int): Boolean =
    index >= 0 && index <= count()

/**
 * Element at without throwing exception but instead returning null if index out of bounds
 * @receiver [Collection]<Item>
 * @param index [Int]
 * @return Item?
 */
public inline fun <Item> Collection<Item>.getOrNull(
    @IntLimit(from = 0) index: Int
): Item? = elementAtOrNull(index)

/**
 * Tells if the given range is in the collection (akk range in [ 0 ; length [
 * @receiver [Collection]<*>
 * @param intRange [IntRange]
 * @return [Boolean]
 */
public inline fun Collection<*>.isRangeValid(intRange: IntRange): Boolean =
    (intRange.first >= 0 &&
            intRange.last >= 0 &&
            intRange.last < size &&
            intRange.first <= size)


//TODO categorize into should be considered moved into a "specific" extension
/**
 * Maps the given list into "buckets" / akk categorizes the items.
 * item will appear in multiple buckets / categories iff multiple filters accept them
 * @receiver [Collection]<Item>
 * @param filters [Array]<out [Function1]<Item, [Boolean]>>
 * @return [List]<[List]<Item>>
 */
public inline fun <Item> Collection<Item>.categorizeIntoMultiple(vararg filters: Function1<Item, Boolean>): List<List<Item>> =
    this.categorizeInto(*filters, allowItemInMultipleBuckets = true)


/**
 * Maps the given list into "buckets" / akk categorizes the items.
 * items will NOT appear in multiple buckets / categories even if multiple filters accept them (first one wins)
 * @receiver [Collection]<Item>
 * @param filters [Array]<out [Function1]<Item, [Boolean]>>
 * @return [List]<[List]<Item>>
 */
public inline fun <Item> Collection<Item>.categorizeIntoSingle(vararg filters: Function1<Item, Boolean>): List<List<Item>> =
    this.categorizeInto(*filters, allowItemInMultipleBuckets = false)

/**
 *
 * @receiver [Collection]<Item>
 * @param filters [Array]<out [Function1]<Item, [Boolean]>>
 * @param allowItemInMultipleBuckets [Boolean]
 * @return [List]<[List]<Item>>
 */
public fun <Item> Collection<Item>.categorizeInto(
    vararg filters: Function1<Item, Boolean>,
    allowItemInMultipleBuckets: Boolean = true
): List<List<Item>> {
    val result = filters.mapToMutable { mutableListOf<Item>() }
    return mappings.mapEachItemWith(result) {
        it.categorizeInto(result, filters, allowItemInMultipleBuckets)
    }
}

/**
 * Categorises a single element into the given result array (size of filters)
 * @receiver [Element] the element to categorize.
 * @param result [ArrayList]<[MutableList]<Element>> the place to put the result, given the index of the filter.
 * @param filters [Array]<out [Function1]<Element, [Boolean]>> the filters to use
 * @param allowItemInMultipleBuckets [Boolean] if true, will allow multiple filters to look at this element,
 * if false then it will stop once a filter accepts it.
 */
private inline fun <Element> Element.categorizeInto(
    result: MutableList<MutableList<Element>>,
    filters: Array<out Function1<Element, Boolean>>,
    allowItemInMultipleBuckets: Boolean = true
) {
    filters.forEachIndexed { index: @IntLimit(from = 0) Int, filterAccepts: (Element) -> Boolean ->
        filterAccepts(this).ifTrue {
            result[index].add(this)
            //should we stop finding filters that accepts this item ? if so then go on.
            allowItemInMultipleBuckets.ifFalse {
                return@categorizeInto
            }
        }
    }
}


/**
 * Categorizes the collection into a map of string -> items, such that each of the items gets mapped into a string representation
 * This is only here since categorizing by strings are such a common operation.
 *
 * @receiver [Collection]<Item>
 * @param categorizer [Function1]<Item, [String]>
 * @return [Map]<[String], [List]<Item>>
 */
public inline fun <Item> Collection<Item>.categorizeByString(
    categorizer: Function1<Item, String>
): Map<String, List<Item>> = categorize(categorizer)

/**
 * Categorizes the given collection via the categorizer into a map of categories mapping to the elements matching this.
 * Each element can only be in 1 category. (and will)
 * @receiver [Collection]<T>
 * @param categorizer [Function1]<T, K>
 * @return [Map]<K, [List]<T>>
 */
public inline fun <T, K> Collection<T>.categorize(
    categorizer: Function1<T, K>
): Map<K, List<T>> {
    val categories = mutableMapOf<K, MutableList<T>>()
    return mappings.mapEachItemWith(categories) {
        val key = categorizer(it)
        getOrPut(key, ::mutableListOf).add(it)
    }
}

/**
 * Reverses this collection (a view) if the given boolean is true, otherwise the original collection view is returned
 * @receiver [Collection]<T>
 * @param shouldReverse [Boolean] if true the result will be reversed
 * @return [Collection]<T>
 */
@Deprecated(message = "Too specific for general library, will be removed later")
public inline fun <T> Collection<T>.reversedIf(shouldReverse: Boolean): Collection<T> = if (shouldReverse) {
    reversed()
} else {
    this
}

/**
 * Tells if all booleans in the given collection are true.
 * @receiver [Collection]<[Boolean]>
 * @return [Boolean] true if all is true, false otherwise. for empty its "true"
 */
public inline fun Collection<Boolean>.isAllTrue(): Boolean = all { it }

/**
 * Tells if this collection is NOT null And NOT empty (size > 0)
 * @receiver [Collection]<T>? the nullable collection
 * @return [Boolean] true if the collection is NOT null AND NOT empty
 */
@OptIn(ExperimentalContracts::class)
public inline fun <T> Collection<T>?.isNotNullOrEmpty(): Boolean {
    contract {
        returns(true) implies (this@isNotNullOrEmpty != null)
    }
    return this != null && this.isNotEmpty()
}

/**
 * Tells if this collection is null or empty (size = 0)
 * @receiver [Collection]<T>? the nullable collection
 * @return [Boolean] true if the collection is null or empty
 */
@OptIn(ExperimentalContracts::class)
public inline fun <T> Collection<T>?.isNullOrEmpty(): Boolean {
    contract {
        returns(false) implies (this@isNullOrEmpty != null)
    }
    return this == null || this.isEmpty()
}

//TODO arrays ect ?s
/**
 * Returns the second last element or null if there is no second last (less than 2 elements)
 * @receiver [Collection]<Item>
 * @return Item?
 */
public inline fun <Item> Collection<Item>.secondLastOrNull(): Item? =
    elementAtOrNull(size - 2)


/**
 * finds the index of the given element, or null if it was not found.
 * @receiver [Collection]<Item>
 * @param element Item
 * @return [Int]?
 */
@IntLimit(from = 0)
public inline fun <@kotlin.internal.OnlyInputTypes Item> Collection<Item>.indexOfOrNull(element: Item): Int? =
    indexOf(element).indexOfExtensions.unwrapUnsafeIndexOf()


/**
 *
 * @receiver [Collection]<Item>
 * @param predicate [Function1]<Item, [Boolean]>
 * @return [Int]? null if not found, the index otherwise.
 */
@IntLimit(from = 0)
public inline fun <Item> Collection<Item>.indexOfFirstOrNull(predicate: Function1<Item, Boolean>): Int? =
    indexOfFirst(predicate).indexOfExtensions.unwrapUnsafeIndexOf()


/**
 *
 * @receiver [Collection]<Item>
 * @param predicate [Function1]<Item, [Boolean]>
 * @return [Int]? null if not found, the index otherwise.
 */
@IntLimit(from = 0)
public inline fun <Item> Collection<Item>.indexOfLastOrNull(predicate: Function1<Item, Boolean>): Int? =
    indexOfLast(predicate).indexOfExtensions.unwrapUnsafeIndexOf()


/**
 * finds the last index of the given element, or null if it was not found.
 * @receiver [Collection]<[Item]>
 * @param element [Item]
 * @return [Int]? null if not found, or the last index of it
 */
@IntLimit(from = 0)
public inline fun <@kotlin.internal.OnlyInputTypes Item> Collection<Item>.lastIndexOfOrNull(element: Item): Int? =
    this.lastIndexOf(element).indexOfExtensions.unwrapUnsafeIndexOf()

/**
 * Selects the first item that is able to be "mapped" by the [mappingPredicate]
 * @receiver [Collection]<[Item]> the collection to search though
 * @param [mappingPredicate] [Function1]<[Item], [U]?> the predicate, that either returns null (means not found)
 * or the value that should be returned from this method call
 * @return [U]? the first item that could be mapped to a value or null if non was applicable via the [mappingPredicate]
 */
public inline fun <Item, U> Collection<Item>.selectFirstOrNull(
    mappingPredicate: (Item) -> U?
): U? {
    forEach { item ->
        mappingPredicate(item)?.let { mapped: U ->
            return@selectFirstOrNull mapped
        }
    }
    return null
}

//region joinEvery

/**
 * Joins [toJoin] between [itemsBetweenJoin] into a single [List].
 * @receiver [Collection]<[Item]> The items to insert the joins between
 * @param itemsBetweenJoin [Int] how many items there should be between a join. a join can not be the first or last item in the result
 * @param toJoin [Item] what to join in between the items
 * @return [List]<Item>
 */
public inline fun <Item> Collection<Item>.joinEvery(
    @IntLimit(from = 1) itemsBetweenJoin: Int,
    toJoin: Item
): List<Item> = joinEveryAction(
    itemsBetweenJoin,
    toJoinAction = { toJoin }
)


/**
 * Joins the item from the given [toJoinAction] between [itemsBetweenJoin] into a single [List].
 * @receiver [Collection]<[Item]> The items to insert the joins between
 * @param itemsBetweenJoin [Int] how many items there should be between a join. a join can not be the first or last item in the result
 * @param toJoinAction [Item] the action producing what to join in between the items
 * @return [List]<Item>
 */
public inline fun <Item> Collection<Item>.joinEveryAction(
    @IntLimit(from = 1) itemsBetweenJoin: Int,
    crossinline toJoinAction: () -> Item
): List<Item> {
    if (itemsBetweenJoin <= 0) {
        return this.toList()
    }
    return GenericCollectionExtensions.joinEveryAction(
        itemsBetweenJoins = itemsBetweenJoin,
        toJoinAction = toJoinAction,
        size = size,
        getter = this::elementAt,
        builderType = ::List
    )
}
//endregion

/**
 *
 * @receiver [Collection]<[Any]?>
 * @param function [Function1]<[U], [Unit]>
 */
public inline fun <reified U> Collection<Any?>.forEachWithType(
    function: Function0<U>
): Unit = forEach {
    it?.cast<U>()?.let(function)
}

/**
 *
 * @receiver [Collection]<[Any]?>
 * @param findAction [Function1]<[U], [Boolean]>
 * @return [U]?
 */
public inline fun <reified U> Collection<Any?>.findWithType(
    findAction: Function1<U, Boolean>
): U? {
    forEachWithType<U> {
        findAction(it).ifTrue {
            return@findWithType it
        }
    }
    return null
}

//region toMapFlat
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
//endregion

//region toMap
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
//endregion

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