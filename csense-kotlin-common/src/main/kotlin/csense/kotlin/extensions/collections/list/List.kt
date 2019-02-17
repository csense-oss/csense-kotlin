@file:Suppress("unused", "NOTHING_TO_INLINE")

package csense.kotlin.extensions.collections.list

import csense.kotlin.extensions.primitives.*

/**
 * Returns a limited view of this list, by limiting the size of it (if the list is shorter than the limit,
 * then the result will be the lists' length).
 * @receiver List<E>
 * @param size Int
 * @return List<E>
 */

inline fun <E> List<E>.limitToSize(size: Int): List<E> =
        subList(0, minOf(size, this.size))

/**
 * creates a sublist with the given range (including the end)
 * will throw if the range is out of bounds
 * @receiver List<T>
 * @param intRange IntRange
 * @return List<T>
 */

inline fun <T> List<T>.subList(intRange: IntRange): List<T> =
        subList(intRange.start, intRange.endInclusive)

/**
 * maps the given list into "buckets" / akk categorizes the items. i
 * no item will appear in multiple buckets / categories
 * @receiver List<T>
 * @param filters Array<out Function1<T, Boolean>>
 * @return List<List<T>>
 */
//TODO collection instead

inline fun <T> List<T>.categorizeInto(vararg filters: (T) -> Boolean): List<List<T>> {
    val result = filters.map { mutableListOf<T>() }
    this.forEach {
        filters.forEachIndexed { index, filterAccepts ->
            filterAccepts(it).onTrue { result[index].add(it) }
        }
    }
    return result
}

/**
 * creates a new list by repeating this list the given number of times.
 * @receiver List<T>
 * @param repeatBy Int
 * @return List<T>
 */
inline fun <T> List<T>.repeat(repeatBy: Int): List<T> {
    if (repeatBy.isNegative) {
        return this
    }
    val resultList = this.toMutableList()
    for (i in 0 until repeatBy) {
        resultList += this
    }
    return resultList
}

/**
 * Repeats this list to the given size, by repeating elements from the beginning
 * @receiver List<T>
 * @param size Int
 * @return List<T>
 */
inline fun <reified T> List<T>.repeatToSize(size: Int): List<T> {
    if (isEmpty() || size <= 0) {
        return listOf()
    }
    val timesToRepeate = size / count()
    val missingItemsToCopy = size % count()
    val resultList = this.repeat(timesToRepeate - 1)
    return resultList + this.subList(0, missingItemsToCopy)
}


