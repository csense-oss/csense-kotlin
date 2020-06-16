@file:Suppress("unused", "NOTHING_TO_INLINE")

package csense.kotlin.extensions.collections


//region set & setAll

/**
 * Clears the collection and add's the given collection
 * @receiver MutableCollection<E>
 * @param collection Collection<E>
 */
inline fun <E> MutableCollection<E>.setAll(collection: Collection<E>) {
    clear()
    addAll(collection)
}

/**
 * Clears the collection and add's the given vararg of items.
 * @receiver MutableCollection<E>
 * @param items Collection<E>
 */
inline fun <E> MutableCollection<E>.setAll(vararg items: E) {
    clear()
    addAll(items)
}

/**
 * Clears the collection and add's the given element
 * @receiver MutableCollection<E>
 * @param item E
 */
inline fun <E> MutableCollection<E>.set(item: E) {
    clear()
    add(item)
}
//endregion


/**
 * Missing from Std-lib, a vararg edition of add all
 * @receiver MutableCollection<E>
 * @param items Array<out E>
 */
inline fun <E> MutableCollection<E>.addAll(vararg items: E) {
    addAll(items)
}
