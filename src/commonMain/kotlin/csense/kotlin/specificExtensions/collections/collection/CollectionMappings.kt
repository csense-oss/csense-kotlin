@file:Suppress("NOTHING_TO_INLINE")

package csense.kotlin.specificExtensions.collections.collection

import kotlin.jvm.*

@JvmInline
public value class CollectionMappings<Item>(public val collection: Collection<Item>)

public inline val <Item> Collection<Item>.mappings: CollectionMappings<Item>
    get() = CollectionMappings(this)


public inline fun <Item, Result> CollectionMappings<Item>.forEachItemWith(
    result: Result,
    append: Result.(Item) -> Unit
): Result {
    collection.forEach {
        result.append(it)
    }
    return result
}