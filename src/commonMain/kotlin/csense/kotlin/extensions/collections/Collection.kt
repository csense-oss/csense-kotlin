@file:Suppress("unused", "NOTHING_TO_INLINE")

package csense.kotlin.extensions.collections

import csense.kotlin.*
import csense.kotlin.annotations.numbers.*
import csense.kotlin.extensions.*
import csense.kotlin.extensions.primitives.*
import kotlin.contracts.*

/**
 * Validates the given index for the given collection (so 0 until length)
 * @receiver [Collection]<*>
 * @param index [Int]
 * @return [Boolean]
 */

inline fun Collection<*>.isIndexValid(index: Int): Boolean =
        index >= 0 && index < count()

/**
 * Validates the given index for the given collection, accounting for inserting in the end (so 0 until (including) length)
 * @receiver [Collection]<*>
 * @param index [Int]
 * @return [Boolean]
 */

inline fun Collection<*>.isIndexValidForInsert(index: Int): Boolean =
        index >= 0 && index <= count()

/**
 * Element at without throwing exception but instead returning null if index out of bounds
 * @receiver [Collection]<T>
 * @param index [Int]
 * @return T?
 */
inline fun <T> Collection<T>.getOrNull(
        @IntLimit(from = 0) index: Int
): T? = elementAtOrNull(index)

/**
 * Tells if the given range is in the collection (akk range in [ 0 ; length [
 * @receiver [Collection]<*>
 * @param intRange [IntRange]
 * @return [Boolean]
 */
inline fun Collection<*>.isRangeValid(intRange: IntRange): Boolean =
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
inline fun <T> Collection<T>.categorizeIntoMultiple(vararg filters: Function1<T, Boolean>): List<List<T>> =
        this.categorizeInto(*filters, allowItemInMultipleBuckets = true)


/**
 * Maps the given list into "buckets" / akk categorizes the items.
 * items will NOT appear in multiple buckets / categories even if multiple filters accept them (first one wins)
 * @receiver [Collection]<T>
 * @param filters [Array]<out [Function1]<T, [Boolean]>>
 * @return [List]<[List]<T>>
 */
inline fun <T> Collection<T>.categorizeIntoSingle(vararg filters: Function1<T, Boolean>): List<List<T>> =
        this.categorizeInto(*filters, allowItemInMultipleBuckets = false)

/**
 *
 * @receiver [Collection]<Element>
 * @param filters [Array]<out [Function1]<Element, [Boolean]>>
 * @param allowItemInMultipleBuckets [Boolean]
 * @return [List]<[List]<Element>>
 */
inline fun <Element> Collection<Element>.categorizeInto(
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
inline fun <Element> Element.categorizeInto(
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
inline fun <T> Collection<T>.categorizeByString(
        categorizer: Function1<T, String>
): Map<String, List<T>> = categorize(categorizer)

/**
 * Categorizes the given collection via the categorizer into a map of categories mapping to the elements matching this.
 * Each element can only be in 1 category. (and will)
 * @receiver [Collection]<T>
 * @param categorizer [Function1]<T, K>
 * @return [Map]<K, [List]<T>>
 */
inline fun <T, K> Collection<T>.categorize(
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
inline fun <T> Collection<T>.reversedIf(shouldReverse: Boolean): Collection<T> = if (shouldReverse) {
    reversed()
} else {
    this
}

/**
 * Tells if all booleans in the given collection are true.
 * @receiver [Collection]<[Boolean]>
 * @return [Boolean] true if all is true, false otherwise. for empty its "true"
 */
inline fun Collection<Boolean>.isAllTrue(): Boolean = all { it }

/**
 * Tells if this collection is NOT null And NOT empty (size > 0)
 * @receiver [Collection]<T>? the nullable collection
 * @return [Boolean] true if the collection is NOT null AND NOT empty
 */
@OptIn(ExperimentalContracts::class)
inline fun <T> Collection<T>?.isNotNullOrEmpty(): Boolean {
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
inline fun <T> Collection<T>?.isNullOrEmpty(): Boolean {
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
inline fun <T> Collection<T>.secondLastOrNull(): T? {
    return elementAtOrNull(size - 2)
}


/**
 * finds the index of the given element, or null if it was not found.
 * @receiver [Collection]<T>
 * @param element T
 * @return [Int]?
 */
@IntLimit(from = 0)
inline fun <T> Collection<T>.indexOfOrNull(element: T): Int? =
        indexOf(element).indexOfExtensions.unwrapUnsafeIndexOf()


/**
 *
 * @receiver [Collection]<T>
 * @param predicate [Function1]<T, [Boolean]>
 * @return [Int]? null if not found, the index otherwise.
 */
@IntLimit(from = 0)
inline fun <T> Collection<T>.indexOfFirstOrNull(predicate: Function1<T, Boolean>): Int? =
        indexOfFirst(predicate).indexOfExtensions.unwrapUnsafeIndexOf()


/**
 *
 * @receiver [Collection]<T>
 * @param predicate [Function1]<T, [Boolean]>
 * @return [Int]? null if not found, the index otherwise.
 */
@IntLimit(from = 0)
inline fun <T> Collection<T>.indexOfLastOrNull(predicate: Function1<T, Boolean>): Int? =
        indexOfLast(predicate).indexOfExtensions.unwrapUnsafeIndexOf()


/**
 * finds the last index of the given element, or null if it was not found.
 * @receiver [Collection]<T>
 * @param element T
 * @return [Int]? null if not found, or the last index of it
 */
@IntLimit(from = 0)
inline fun <T> Collection<T>.lastIndexOfOrNull(element: T): Int? =
        this.lastIndexOf(element).indexOfExtensions.unwrapUnsafeIndexOf()
