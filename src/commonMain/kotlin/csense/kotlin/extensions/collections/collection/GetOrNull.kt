@file:Suppress("NOTHING_TO_INLINE")

package csense.kotlin.extensions.collections.collection

import csense.kotlin.annotations.numbers.*

/**
 * Element at without throwing exception but instead returning null if index out of bounds
 * @receiver [Collection]<Item>
 * @param index [Int]
 * @return Item?
 */
public inline fun <Item> Collection<Item>.getOrNull(
    @IntLimit(from = 0) index: Int
): Item? = elementAtOrNull(index)