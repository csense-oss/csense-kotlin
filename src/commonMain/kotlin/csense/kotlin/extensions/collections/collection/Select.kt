package csense.kotlin.extensions.collections.collection

import csense.kotlin.*


/**
 * Selects the first item that is able to be "mapped" by the [mappingPredicate]
 * @receiver [Collection]<[Item]> the collection to search though
 * @param [mappingPredicate] [Function1]<[Item], [U]?> the predicate, that either returns null (means not found)
 * or the value that should be returned from this method call
 * @return [U]? the first item that could be mapped to a value or null if non was applicable via the [mappingPredicate]
 */
public inline fun <Item, U> Collection<Item>.selectFirstOrNull(
    mappingPredicate: Function1<Item, U>
): U? {
    forEach { item ->
        mappingPredicate(item)?.let { mapped: U ->
            return@selectFirstOrNull mapped
        }
    }
    return null
}