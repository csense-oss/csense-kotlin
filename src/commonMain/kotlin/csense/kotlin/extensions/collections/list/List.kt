@file:Suppress("unused", "NOTHING_TO_INLINE")

package csense.kotlin.extensions.collections.list

import csense.kotlin.annotations.numbers.IntLimit
import csense.kotlin.extensions.primitives.isNegativeOrZero

/**
 * Returns a limited view of this list, by limiting the size of it (if the list is shorter than the limit,
 * then the result will be the lists' length).
 * @receiver List<E>
 * @param size Int
 * @return List<E>
 */

inline fun <E> List<E>.limitToSize(@IntLimit(from = 0) size: Int): List<E> {
    if (size.isNegativeOrZero) {
        return emptyList()
    }
    return subList(0, minOf(this.size, size))
}

/**
 * Creates a sublist with the given range (including the end)
 * Checks bounds before accessing. returns empty list of out of bounds.
 * @receiver List<T>
 * @param intRange IntRange
 * @return List<T>
 */
inline fun <T> List<T>.subList(intRange: IntRange): List<T> =
        subListSafe(intRange.first, intRange.last)

/**
 * Extracts a sublist or returns an empty list if the requested range is out of bounds.
 * This is a safe alternative to the STD lib edition that goes out of bounds.
 * @receiver List<T>
 * @param fromIndex Int (inclusive)  [0;size[
 * @param toIndex Int (exclusive)    ]fromIndex;size[
 * @return List<T>
 */
inline fun <T> List<T>.subListSafe(@IntLimit(from = 0) fromIndex: Int, @IntLimit(from = 0) toIndex: Int): List<T> {
    return if (fromIndex >= 0 && toIndex <= size && fromIndex <= toIndex) {
        subList(fromIndex, toIndex)
    } else {
        emptyList()
    }
}


/**
 * Creates a new list by repeating this list the given number of times.
 * so if you set repeat by to 1 you will get a copy, and 2 will be 2 copies.
 * a negative / 0 repeatBy will give you an empty list.
 * @receiver List<T>
 * @param repeatBy Int
 * @return List<T>
 */
inline fun <T> List<T>.repeat(@IntLimit(from = 0) repeatBy: Int): List<T> {
    if (repeatBy.isNegativeOrZero) {
        return emptyList()
    }
    val resultList = mutableListOf<T>()
    for (i in 0 until repeatBy) {
        resultList += this
    }
    return resultList
}

/**
 * Repeats this list to the given toSize, by repeating elements from the beginning
 * if toSize is less than the list size, returns the sublist.
 * else it expands with the already existing data.
 *
 * @receiver List<T>
 * @param toSize Int
 * @return List<T> a new list to the desired size, by repeating this list, over and over again.
 */
inline fun <reified T> List<T>.repeatToSize(@IntLimit(from = 0) toSize: Int): List<T> {
    //empty bounds
    if (size.isNegativeOrZero || toSize.isNegativeOrZero) {
        return emptyList()
    }
    // this as sublist ?
    if (toSize < size) {
        return this.subList(0, toSize)
    }
    //we are to expand, first calculate full "copies"
    val timesToRepeat = toSize / size
    //calculate the missing after each "copy", like make this list 2,5 times larger (the 0.5 part)
    val missingItemsToCopy = toSize % size
    //copy the list each time applicable
    val resultList = this.repeat(timesToRepeat)
    //and add the sublist missing part.
    return resultList + this.subList(0, missingItemsToCopy)
}


