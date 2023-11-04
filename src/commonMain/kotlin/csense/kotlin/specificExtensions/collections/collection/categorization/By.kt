package csense.kotlin.specificExtensions.collections.collection.categorization

import csense.kotlin.specificExtensions.collections.collection.*


/**
 * Categorizes the collection into a map of string -> items, such that each of the items gets mapped into a string representation
 * This is only here since categorizing by strings are such a common operation.
 *
 * @receiver [Collection]<Item>
 * @param categorizer [Function1]<Item, [String]>
 * @return [Map]<[String], [List]<Item>>
 */
public inline fun <Item> CollectionCategorization<Item>.categorizeByString(
    categorizer: Function1<Item, String>,
): Map<String, List<Item>> = categorize(categorizer)


/**
 * Categorizes the given collection via the categorizer into a map of categories mapping to the elements matching this.
 * Each element can only be in 1 category. (and will)
 * @receiver [Collection]<T>
 * @param categorizer [Function1]<T, K>
 * @return [Map]<K, [List]<T>>
 */
public inline fun <T, K> CollectionCategorization<T>.categorize(
    categorizer: Function1<T, K>,
): Map<K, List<T>> {
    return collection.mappings.mapEachItemWith(mutableMapOf<K, MutableList<T>>()) { it: T ->
        val key: K = categorizer(it)
        getOrPut(key, ::mutableListOf).add(it)
    }
}