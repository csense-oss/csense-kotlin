@file:Suppress("unused", "NOTHING_TO_INLINE", "INVISIBLE_MEMBER", "INVISIBLE_REFERENCE")

package csense.kotlin.extensions.collections.array.generic


/**
 * Gets the value at [index] or returns [defaultValue] if out of bounds.
 * @receiver Array<Item>
 * @param index Int
 * @param defaultValue Item
 * @return Item
 */
public inline fun <Item> Array<out Item>.getOr(index: Int, defaultValue: Item & Any): Item & Any {
    return getOrNull(index) ?: defaultValue
}
