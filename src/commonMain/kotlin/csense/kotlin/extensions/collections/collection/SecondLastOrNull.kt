@file:Suppress("NOTHING_TO_INLINE")

package csense.kotlin.extensions.collections.collection

/**
 * Returns the second last element or null if there is no second last (less than 2 elements)
 * @receiver [Collection]<Item>
 * @return Item?
 */
public inline fun <Item> Collection<Item>.secondLastOrNull(): Item? =
    elementAtOrNull(size - 2)



