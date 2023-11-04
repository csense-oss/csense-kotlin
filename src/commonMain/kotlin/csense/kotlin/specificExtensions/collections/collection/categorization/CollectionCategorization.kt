package csense.kotlin.specificExtensions.collections.collection.categorization

import kotlin.jvm.*


@JvmInline
public value class CollectionCategorization<Item>(public val collection: Collection<Item>)

public inline val <Item> Collection<Item>.categorization: CollectionCategorization<Item>
    get() = CollectionCategorization(this)