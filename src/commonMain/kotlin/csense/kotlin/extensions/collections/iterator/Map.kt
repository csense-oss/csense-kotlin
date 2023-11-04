package csense.kotlin.extensions.collections.iterator


/**
 * Maps all (remaining) items of this Iterator by the given [transform] function
 * @receiver Iterator<T>
 * @param transform Function1<T, R> transforms a given item from this iterator
 * @return List<R> the transformed list
 */
public inline fun <T, R> Iterator<T>.map(
    transform: (T) -> R,
): List<R> = mutableListOf<R>().also { list ->
    this.forEach {
        list += transform(it)
    }
}