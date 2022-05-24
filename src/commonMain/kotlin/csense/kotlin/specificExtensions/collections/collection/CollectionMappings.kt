@file:Suppress("NOTHING_TO_INLINE")

package csense.kotlin.specificExtensions.collections.collection

import kotlin.jvm.*

@JvmInline
public value class CollectionMappings<Item>(public val collection: Collection<Item>)

public inline val <Item> Collection<Item>.mappings: CollectionMappings<Item>
    get() = CollectionMappings(this)

/**
 * Apply [map] on each item [with] the given [result]
 * @param result Result the result to apply each [map] to
 * @param map Function2<Result, Item, Unit>  the processing of the given item on the [result]
 * @return Result
 */
public inline fun <Item, Result> CollectionMappings<Item>.mapEachItemWith(
    result: Result,
    map: Result.(Item) -> Unit
): Result {
    collection.forEach {
        result.map(it)
    }
    return result
}