@file:Suppress("NOTHING_TO_INLINE")

package csense.kotlin.specificExtensions.collections.collection.categorization

import csense.kotlin.annotations.numbers.*
import csense.kotlin.annotations.sideEffect.*
import csense.kotlin.extensions.collections.array.generic.*
import csense.kotlin.specificExtensions.collections.collection.*
import csense.kotlin.*

/**
 * Maps the given list into "buckets" / akk categorizes the items.
 * item will appear in multiple buckets / categories iff multiple filters accept them
 * @receiver [Collection]<Item>
 * @param filters [Array]<out [Function1]<Item, [Boolean]>>
 * @return [List]<[List]<Item>>
 */
public inline fun <Item> CollectionCategorization<Item>.categorizeIntoMultiple(
    vararg filters: Function1<Item, Boolean>,
): List<List<Item>> = categorizeInto(
    filters = filters,
    allowItemInMultipleBuckets = true
)


/**
 * Maps the given list into "buckets" / akk categorizes the items.
 * items will NOT appear in multiple buckets / categories even if multiple filters accept them (first one wins)
 * @receiver [Collection]<Item>
 * @param filters [Array]<out [Function1]<Item, [Boolean]>>
 * @return [List]<[List]<Item>>
 */
public inline fun <Item> CollectionCategorization<Item>.categorizeIntoSingle(
    vararg filters: Function1<Item, Boolean>,
): List<List<Item>> = categorizeInto(
    filters = filters,
    allowItemInMultipleBuckets = false
)

/**
 *
 * @receiver [Collection]<Item>
 * @param filters [Array]<out [Function1]<Item, [Boolean]>>
 * @param allowItemInMultipleBuckets [Boolean]
 * @return [List]<[List]<Item>>
 */
public fun <Item> CollectionCategorization<Item>.categorizeInto(
    vararg filters: Function1<Item, Boolean>,
    allowItemInMultipleBuckets: Boolean = true,
): List<List<Item>> {
    val result: MutableList<MutableList<Item>> = filters.mapToMutable { mutableListOf() }
    return collection.mappings.mapEachItemWith(result) { it: Item ->
        it.categorizeInto(
            result = result,
            filters = filters,
            allowItemInMultipleBuckets = allowItemInMultipleBuckets
        )
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
@SideEffects
private inline fun <Element> Element.categorizeInto(
    result: MutableList<MutableList<Element>>,
    filters: Array<out Function1<Element, Boolean>>,
    allowItemInMultipleBuckets: Boolean = true,
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


