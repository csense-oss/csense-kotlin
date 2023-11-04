@file:Suppress("unused", "NOTHING_TO_INLINE")

package csense.kotlin.extensions.collections.iterator

/**
 * Converts the current iterator to a list by iterating all the "remaining" elements
 * @receiver Iterator<T>
 * @return List<T> a list of all the items (remaining)
 */
public inline fun <T> Iterator<T>.toList(): List<T> =
    toMutableList()


/**
 * Converts the current iterator to a list by iterating all the "remaining" elements
 * @receiver Iterator<T>
 * @return MutableList<T> a mutable list of all the items (remaining)
 */
public inline fun <T> Iterator<T>.toMutableList(

): MutableList<T> = mutableListOf<T>().also { list ->
    this.forEach {
        list += it
    }
}

