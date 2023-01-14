@file:Suppress("unused", "NOTHING_TO_INLINE", "INVISIBLE_MEMBER", "INVISIBLE_REFERENCE")

package csense.kotlin.extensions.collections.collection.mutable

import csense.kotlin.annotations.sideEffect.*


/**
 * Clears the collection and adds the given collection
 * @receiver [MutableCollection]<E>
 * @param collection [Collection]<E>
 */
@DiscardableResult
public inline fun <@kotlin.internal.OnlyInputTypes E> MutableCollection<E>.setAll(
    collection: Collection<E>
): Boolean {
    clear()
    return addAll(collection)
}

/**
 * Clears the collection and adds the given vararg of items.
 * @receiver [MutableCollection]<E>
 * @param items [Collection]<E>
 */
@DiscardableResult
public inline fun <@kotlin.internal.OnlyInputTypes E> MutableCollection<E>.setAll(
    vararg items: E
): Boolean {
    clear()
    return addAll(items)
}

/**
 * Clears the collection and adds the given element
 * @receiver [MutableCollection]<E>
 * @param item E
 */
@DiscardableResult
public inline fun <@kotlin.internal.OnlyInputTypes E> MutableCollection<E>.set(
    item: E
): Boolean {
    clear()
    return add(item)
}