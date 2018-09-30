package csense.kotlin.extensions.collections.set

/**
 * Toggles whenever a set contains the given item;
 * if the set contains the item it will be removed.
 * if it does not contain the item, the item will be inserted.
 * @receiver MutableSet<T>
 * @param item T
 */
@Suppress("NOTHING_TO_INLINE")
inline fun <T> MutableSet<T>.toggleExistence(item: T) {
    setExistence(item, !contains(item))
}

/**
 * like toggle, except you control the action by the "shouldExists";
 * if that is true, then the element is added, if false the element is removed.
 * @receiver MutableSet<T>
 * @param item T
 * @param shouldExists Boolean
 */
@Suppress("NOTHING_TO_INLINE")
inline fun <T> MutableSet<T>.setExistence(item: T, shouldExists: Boolean) {
    if (shouldExists) {
        add(item)
    } else {
        remove(item)
    }
}