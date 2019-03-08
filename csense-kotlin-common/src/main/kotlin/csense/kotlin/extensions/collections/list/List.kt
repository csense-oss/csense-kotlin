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

inline fun <E> List<E>.limitToSize(size: Int): List<E> {
    return if (size.isNegativeOrZero) {
        listOf()
    } else {
        subList(0, minOf(this.size, size))
    }

}

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
 * creates a new list by repeating this list the given number of times.
 * @receiver List<T>
 * @param repeatBy Int
 * @return List<T>
 */
inline fun <T> List<T>.repeat(repeatBy: Int): List<T> {
    if (repeatBy.isNegativeOrZero) {
        return this
    }
    val resultList = this.toMutableList()
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
inline fun <reified T> List<T>.repeatToSize(toSize: Int): List<T> {
    //empty bounds
    if (size.isNegativeOrZero || toSize.isNegativeOrZero) {
        return listOf()
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
    val resultList = this.repeat(timesToRepeat - 1)
    //and add the sublist missing part.
    return resultList + this.subList(0, missingItemsToCopy)
}


