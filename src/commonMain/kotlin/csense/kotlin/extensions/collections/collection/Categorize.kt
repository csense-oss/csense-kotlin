@file:Suppress("unused", "NOTHING_TO_INLINE", "INVISIBLE_MEMBER", "INVISIBLE_REFERENCE")

package csense.kotlin.extensions.collections.collection

import csense.kotlin.annotations.numbers.*
import csense.kotlin.extensions.collections.array.generic.*
import csense.kotlin.specificExtensions.collections.collection.*


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
        if (!filterAccepts(this)) {
            return@forEachIndexed
        }
        result[index].add(this)
        if (!allowItemInMultipleBuckets) {
            return@categorizeInto
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