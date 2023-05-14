@file:Suppress("NOTHING_TO_INLINE")

package csense.kotlin.extensions.collections.collection

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
public inline fun <Item> Collection<Item>.onEmptyLazy(action: () -> Item): Collection<Item> {
    if (isNotEmpty()) {
        return this
    }
    return listOf(action())
}

@JvmName("onEmptyLazyItems")
public inline fun <Item> Collection<Item>.onEmptyLazy(action: () -> Collection<Item>): Collection<Item> {
    if (isNotEmpty()) {
        return this
    }
    return action()
}