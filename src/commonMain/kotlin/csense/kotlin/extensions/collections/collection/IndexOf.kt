@file:Suppress("unused", "NOTHING_TO_INLINE", "INVISIBLE_MEMBER", "INVISIBLE_REFERENCE") // see https://youtrack.jetbrains.com/issue/KT-60866/Phase-out-usages-of-SuppressINVISIBLEREFERENCE-INVISIBLEMEMBER-in-libraries

package csense.kotlin.extensions.collections.collection

import csense.kotlin.annotations.numbers.*
import csense.kotlin.extensions.collections.*
import csense.kotlin.extensions.primitives.int.*


/**
 * finds the index of the given element, or null if it was not found.
 * @receiver [Collection]<Item>
 * @param element Item
 * @return [Int]?
 */
@IntLimit(from = 0)
public inline fun <@kotlin.internal.OnlyInputTypes Item> Collection<Item>.indexOfOrNull(element: Item): Int? =
    indexOf(element).indexOfExtensions.unwrapUnsafeIndexOf()


/**
 *
 * @receiver [Collection]<Item>
 * @param predicate [Function1]<Item, [Boolean]>
 * @return [Int]? null if not found, the index otherwise.
 */
@IntLimit(from = 0)
public inline fun <Item> Collection<Item>.indexOfFirstOrNull(predicate: Predicate<Item>): Int? =
    indexOfFirst(predicate).indexOfExtensions.unwrapUnsafeIndexOf()


/**
 *
 * @receiver [Collection]<Item>
 * @param predicate [Function1]<Item, [Boolean]>
 * @return [Int]? null if not found, the index otherwise.
 */
@IntLimit(from = 0)
public inline fun <Item> Collection<Item>.indexOfLastOrNull(predicate: Predicate<Item>): Int? =
    indexOfLast(predicate).indexOfExtensions.unwrapUnsafeIndexOf()


/**
 * finds the last index of the given element, or null if it was not found.
 * @receiver [Collection]<[Item]>
 * @param element [Item]
 * @return [Int]? null if not found, or the last index of it
 */
@IntLimit(from = 0)
public inline fun <@kotlin.internal.OnlyInputTypes Item> Collection<Item>.lastIndexOfOrNull(element: Item): Int? =
    this.lastIndexOf(element).indexOfExtensions.unwrapUnsafeIndexOf()
