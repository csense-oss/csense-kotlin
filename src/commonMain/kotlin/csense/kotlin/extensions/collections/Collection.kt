@file:Suppress("unused", "NOTHING_TO_INLINE", "INVISIBLE_MEMBER", "INVISIBLE_REFERENCE")

package csense.kotlin.extensions.collections

import csense.kotlin.*
import csense.kotlin.annotations.numbers.*
import csense.kotlin.extensions.*
import csense.kotlin.extensions.collections.generic.*
import csense.kotlin.extensions.primitives.*
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
 * @receiver [Collection]<T>
 * @param index [Int]
 * @return T?
 */
public inline fun <T> Collection<T>.getOrNull(
    @IntLimit(from = 0) index: Int
): T? = elementAtOrNull(index)

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


/**
 * Maps the given list into "buckets" / akk categorizes the items.
 * item will appear in multiple buckets / categories iff multiple filters accept them
 * @receiver [Collection]<T>
 * @param filters [Array]<out [Function1]<T, [Boolean]>>
 * @return [List]<[List]<T>>
 */
public inline fun <T> Collection<T>.categorizeIntoMultiple(vararg filters: Function1<T, Boolean>): List<List<T>> =
    this.categorizeInto(*filters, allowItemInMultipleBuckets = true)


/**
 * Maps the given list into "buckets" / akk categorizes the items.
 * items will NOT appear in multiple buckets / categories even if multiple filters accept them (first one wins)
 * @receiver [Collection]<T>
 * @param filters [Array]<out [Function1]<T, [Boolean]>>
 * @return [List]<[List]<T>>
 */
public inline fun <T> Collection<T>.categorizeIntoSingle(vararg filters: Function1<T, Boolean>): List<List<T>> =
    this.categorizeInto(*filters, allowItemInMultipleBuckets = false)

/**
 *
 * @receiver [Collection]<Element>
 * @param filters [Array]<out [Function1]<Element, [Boolean]>>
 * @param allowItemInMultipleBuckets [Boolean]
 * @return [List]<[List]<Element>>
 */
public inline fun <Element> Collection<Element>.categorizeInto(
    vararg filters: Function1<Element, Boolean>,
    allowItemInMultipleBuckets: Boolean = true
): List<List<Element>> {
    val result =
        ArrayList(filters.map { mutableListOf<Element>() })
    forEach {
        it.categorizeInto(result, filters, allowItemInMultipleBuckets)
    }
    return result
}

/**
 * Categorises a single element into the given result array (size of filters)
 * @receiver [Element] the element to categorize.
 * @param result [ArrayList]<[MutableList]<Element>> the place to put the result, given the index of the filter.
 * @param filters [Array]<out [Function1]<Element, [Boolean]>> the filters to use
 * @param allowItemInMultipleBuckets [Boolean] if true, will allow multiple filters to look at this element,
 * if false then it will stop once a filter accepts it.
 */
@Deprecated("will be unavilable in the next version", level = DeprecationLevel.WARNING)
public inline fun <Element> Element.categorizeInto(
    result: ArrayList<MutableList<Element>>,
    filters: Array<out Function1<Element, Boolean>>,
    allowItemInMultipleBuckets: Boolean = true
) {
    filters.forEachIndexed { index: @IntLimit(from = 0) Int, filterAccepts: (Element) -> Boolean ->
        filterAccepts(this).ifTrue {
            result[index].add(this)
            //should we stop finding filters that accepts this item ? if so then go on.
            allowItemInMultipleBuckets.ifFalse {
                return@categorizeInto //break out.
            }
        }
    }
}


/**
 * Categorizes the collection into a map of string -> items, such that each of the items gets mapped into a string representation
 * This is only here since categorizing by strings are such a common operation.
 *
 * @receiver [Collection]<T>
 * @param categorizer [Function1]<T, [String]>
 * @return [Map]<[String], [List]<T>>
 */
public inline fun <T> Collection<T>.categorizeByString(
    categorizer: Function1<T, String>
): Map<String, List<T>> = categorize(categorizer)

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
    val result = mutableMapOf<K, MutableList<T>>()
    forEach { item: T ->
        val key = categorizer(item)
        result.getOrPut(key, ::mutableListOf).add(item)
    }
    return result
}

/**
 * Reverses this collection (a view) if the given boolean is true, otherwise the original collection view is returned
 * @receiver [Collection]<T>
 * @param shouldReverse [Boolean] if true the result will be reversed
 * @return [Collection]<T>
 */
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
 * @receiver [Collection]<T>
 * @return T?
 */
public inline fun <T> Collection<T>.secondLastOrNull(): T? =
    elementAtOrNull(size - 2)


/**
 * finds the index of the given element, or null if it was not found.
 * @receiver [Collection]<T>
 * @param element T
 * @return [Int]?
 */
@IntLimit(from = 0)
public inline fun <@kotlin.internal.OnlyInputTypes T> Collection<T>.indexOfOrNull(element: T): Int? =
    indexOf(element).indexOfExtensions.unwrapUnsafeIndexOf()


/**
 *
 * @receiver [Collection]<T>
 * @param predicate [Function1]<T, [Boolean]>
 * @return [Int]? null if not found, the index otherwise.
 */
@IntLimit(from = 0)
public inline fun <T> Collection<T>.indexOfFirstOrNull(predicate: Function1<T, Boolean>): Int? =
    indexOfFirst(predicate).indexOfExtensions.unwrapUnsafeIndexOf()


/**
 *
 * @receiver [Collection]<T>
 * @param predicate [Function1]<T, [Boolean]>
 * @return [Int]? null if not found, the index otherwise.
 */
@IntLimit(from = 0)
public inline fun <T> Collection<T>.indexOfLastOrNull(predicate: Function1<T, Boolean>): Int? =
    indexOfLast(predicate).indexOfExtensions.unwrapUnsafeIndexOf()


/**
 * finds the last index of the given element, or null if it was not found.
 * @receiver [Collection]<[T]>
 * @param element [T]
 * @return [Int]? null if not found, or the last index of it
 */
@IntLimit(from = 0)
public inline fun <@kotlin.internal.OnlyInputTypes T> Collection<T>.lastIndexOfOrNull(element: T): Int? =
    this.lastIndexOf(element).indexOfExtensions.unwrapUnsafeIndexOf()

/**
 * Selects the first item that is able to be "mapped" by the [mappingPredicate]
 * @receiver [Collection]<[T]> the collection to search though
 * @param [mappingPredicate] [Function1]<[T], [U]?> the predicate, that either returns null (means not found)
 * or the value that should be returned from this method call
 * @return [U]? the first item that could be mapped to a value or null if non was applicable via the [mappingPredicate]
 */
public inline fun <T, U> Collection<T>.selectFirstOrNull(
    mappingPredicate: (T) -> U?
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
 * @receiver [Collection]<[T]> The items to insert the joins between
 * @param itemsBetweenJoin [Int] how many items there should be between a join. a join can not be the first or last item in the result
 * @param toJoin [T] what to join in between the items
 * @return [List]<T>
 */
public inline fun <T> Collection<T>.joinEvery(
    @IntLimit(from = 1) itemsBetweenJoin: Int,
    toJoin: T
): List<T> = joinEveryAction(
    itemsBetweenJoin,
    toJoinAction = { toJoin }
)


/**
 * Joins the item from the given [toJoinAction] between [itemsBetweenJoin] into a single [List].
 * @receiver [Collection]<[T]> The items to insert the joins between
 * @param itemsBetweenJoin [Int] how many items there should be between a join. a join can not be the first or last item in the result
 * @param toJoinAction [T] the action producing what to join in between the items
 * @return [List]<T>
 */
public inline fun <T> Collection<T>.joinEveryAction(
    @IntLimit(from = 1) itemsBetweenJoin: Int,
    crossinline toJoinAction: () -> T
): List<T> {
    if (itemsBetweenJoin <= 0) {
        return this.toList()
    }
    return GenericCollectionExtensions.joinEveryAction(
        itemsBetweenJoin,
        toJoinAction,
        size,
        this::elementAt,
        ::List
    )
}
//endregion

/**
 *
 * @receiver [Collection]<[Any]?>
 * @param function [Function1]<[U], [Unit]>
 */
public inline fun <reified U> Collection<Any?>.forEachWithType(function: Function0<U>) {
    forEach {
        it?.cast<U>()?.let(function)
    }
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

/**
 * Maps this [Collection] to a [Map] with the given [keyMapper]
 * @receiver Collection<T>
 * @param keyMapper Function1<T, Key> extracts a key from a given entry
 * @return Map<Key, T>
 */
public inline fun <T, Key> Collection<T>.toMap(
    keyMapper: Function1<T, Key>
): Map<Key, T> = toMutableMap(keyMapper)

/**
 * Maps this [Collection] to a [MutableMap] with the given [keyMapper]
 * @receiver Collection<T>
 * @param keyMapper Function1<T, Key> extracts a key from a given entry
 * @return Map<Key, T>
 */
public inline fun <T, Key> Collection<T>.toMutableMap(
    keyMapper: Function1<T, Key>
): MutableMap<Key, T> = toMutableMap(keyMapper, valueMapper = { it })


public inline fun <T, Key, Value> Collection<T>.toMap(
    keyMapper: Function1<T, Key>,
    valueMapper: Function1<T, Value>
): Map<Key, Value> = toMutableMap(keyMapper, valueMapper)

public inline fun <T, Key, Value> Collection<T>.toMutableMap(
    keyMapper: Function1<T, Key>,
    valueMapper: Function1<T, Value>
): MutableMap<Key, Value> = LinkedHashMap<Key, Value>(size).also { result ->
    forEach {
        result[keyMapper(it)] = valueMapper(it)
    }
}