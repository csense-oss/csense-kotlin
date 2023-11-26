@file:Suppress(
    "unused",
    "NOTHING_TO_INLINE",
    "INVISIBLE_MEMBER",
    "INVISIBLE_REFERENCE"
) // see https://youtrack.jetbrains.com/issue/KT-60866/Phase-out-usages-of-SuppressINVISIBLEREFERENCE-INVISIBLEMEMBER-in-libraries


package csense.kotlin.extensions.collections.collection

import kotlin.internal.*
import kotlin.jvm.*

public inline fun <Item> Collection<Item>.onEmpty(item: Item): Collection<Item> {
    if (isNotEmpty()) {
        return this
    }
    return listOf(item)
}

public inline fun <Item> Collection<Item>.onEmpty(items: Collection<Item>): Collection<Item> {
    if (isNotEmpty()) {
        return this
    }
    return items
}

@JvmName("onEmptyLazyItem")
public inline fun <@OnlyInputTypes Item> Collection<Item>.onEmptyLazy(
    item: () -> Item
): Collection<Item> {
    return onEmptyLazy(items = {
        listOf(item())
    })
}

@JvmName("onEmptyLazyItems")
public inline fun <@OnlyInputTypes Item> Collection<Item>.onEmptyLazy(
    items: () -> Collection<Item>
): Collection<Item> {
    if (isNotEmpty()) {
        return this
    }
    return items()
}