package csense.kotlin.extensions.collections


/**
 * Clears the collection and add's the given collection
 * @receiver MutableCollection<E>
 * @param collection Collection<E>
 */
fun <E> MutableCollection<E>.set(collection: Collection<E>) {
    clear()
    addAll(collection)
}

/**
 * Clears the collection and add's the given element
 * @receiver MutableCollection<E>
 * @param item E
 */
fun <E> MutableCollection<E>.set(item: E) {
    clear()
    add(item)
}