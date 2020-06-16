package csense.kotlin.extensions.iterator


/**
 * Loops through the iterator and returns the first element matching the given predicate
 * @receiver Iterator<T>
 * @param predicate Function1<T, Boolean>
 * @return T?
 */
@Deprecated(
        "will be moved to other package name (collection) in 0.0.40",
        level = DeprecationLevel.WARNING
)
inline fun <T> Iterator<T>.findFirst(predicate: Function1<T, Boolean>): T? {
    for (item in this) {
        if (predicate(item)) {
            return item
        }
    }
    return null
}