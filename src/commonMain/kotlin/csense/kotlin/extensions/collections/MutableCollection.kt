@file:Suppress("unused", "NOTHING_TO_INLINE", "INVISIBLE_MEMBER", "INVISIBLE_REFERENCE")

package csense.kotlin.extensions.collections


//region set & setAll

/**
 * Clears the collection and adds the given collection
 * @receiver [MutableCollection]<E>
 * @param collection [Collection]<E>
 */
public inline fun <@kotlin.internal.OnlyInputTypes E> MutableCollection<E>.setAll(collection: Collection<E>) {
    clear()
    addAll(collection)
}

/**
 * Clears the collection and adds the given vararg of items.
 * @receiver [MutableCollection]<E>
 * @param items [Collection]<E>
 */
public inline fun <@kotlin.internal.OnlyInputTypes E> MutableCollection<E>.setAll(vararg items: E) {
    clear()
    addAll(items)
}

/**
 * Clears the collection and adds the given element
 * @receiver [MutableCollection]<E>
 * @param item E
 */
public inline fun <@kotlin.internal.OnlyInputTypes E> MutableCollection<E>.set(item: E) {
    clear()
    add(item)
}
//endregion


/**
 * Missing from Standard library, a vararg edition of add all
 * @receiver [MutableCollection]<E>
 * @param items [Array]<out E>
 */
public inline fun <@kotlin.internal.OnlyInputTypes E> MutableCollection<E>.addAll(vararg items: E) {
    addAll(items)
}
