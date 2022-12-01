package csense.kotlin.specificExtensions.collections.array

import kotlin.jvm.*

@JvmInline
public value class ArrayMapping<Item>(public val array: Array<Item>)

public inline val <Item> Array<Item>.mappings: ArrayMapping<Item>
    get() = ArrayMapping(this)

