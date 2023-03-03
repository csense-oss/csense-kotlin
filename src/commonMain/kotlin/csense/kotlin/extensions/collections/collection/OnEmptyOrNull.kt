@file:Suppress(
    "UNUSED_PARAMETER",
    "NOTHING_TO_INLINE",
    "UnusedReceiverParameter",
    "INVISIBLE_MEMBER",
    "INVISIBLE_REFERENCE"
)

package csense.kotlin.extensions.collections.collection

import csense.kotlin.general.*
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

@Deprecated(
    "Receiver known at compile time to not be null. Use onEmpty instead",
    level = DeprecationLevel.ERROR,
    replaceWith = ReplaceWith("onEmpty(item)", "csense.kotlin.extensions.collections.collection")
)
public inline fun Collection<Any>.onNullOrEmpty(item: Any): Nothing = unexpected()

@Deprecated(
    "Receiver known at compile time to not be null. Use onEmpty instead",
    level = DeprecationLevel.ERROR,
    replaceWith = ReplaceWith("onEmpty(items)", "csense.kotlin.extensions.collections.collection")
)
public inline fun Collection<Any>.onNullOrEmpty(items: Collection<Any>): Nothing = unexpected()

@Deprecated(
    "Receiver known at compile time to not be null. Use onEmpty instead",
    level = DeprecationLevel.ERROR,
    replaceWith = ReplaceWith("onEmptyLazy(item)", "csense.kotlin.extensions.collections.collection")
)
public inline fun Collection<Any>.onNullOrEmptyLazy(item: Any): Nothing = unexpected()


@Deprecated(
    "Receiver known at compile time to not be null. Use onEmpty instead",
    level = DeprecationLevel.ERROR,
    replaceWith = ReplaceWith("onEmptyLazy(items)", "csense.kotlin.extensions.collections.collection")
)
public inline fun Collection<Any>.onNullOrEmptyLazy(items: Collection<Any>): Nothing = unexpected()

