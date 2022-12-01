@file:Suppress("unused", "NOTHING_TO_INLINE", "INVISIBLE_MEMBER", "INVISIBLE_REFERENCE")

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

//TODO arrays ect ?s
/**
 * Returns the second last element or null if there is no second last (less than 2 elements)
 * @receiver [Collection]<Item>
 * @return Item?
 */
public inline fun <Item> Collection<Item>.secondLastOrNull(): Item? =
    elementAtOrNull(size - 2)



