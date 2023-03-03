@file:Suppress("NOTHING_TO_INLINE")

package csense.kotlin.extensions.collections.collection

import kotlin.jvm.*


public inline fun <Item> Collection<Item>?.onNullOrEmpty(item: Item): Collection<Item> {
    if (isNotNullOrEmpty()) {
        return this
    }
    return listOf(item)
}

public inline fun <Item> Collection<Item>?.onNullOrEmpty(items: Collection<Item>): Collection<Item> {
    if (isNotNullOrEmpty()) {
        return this
    }
    return items
}

@JvmName("onNullOrEmptyLazyItem")
public inline fun <Item> Collection<Item>?.onNullOrEmptyLazy(action: () -> Item): Collection<Item> {
    if (isNotNullOrEmpty()) {
        return this
    }
    return listOf(action())
}

@JvmName("onNullOrEmptyLazyItems")
public inline fun <Item> Collection<Item>?.onNullOrEmptyLazy(action: () -> Collection<Item>): Collection<Item> {
    if (isNotNullOrEmpty()) {
        return this
    }
    return action()
}