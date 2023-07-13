@file:Suppress("NOTHING_TO_INLINE")
package csense.kotlin.extensions.collections.list

import csense.kotlin.annotations.numbers.*
import csense.kotlin.extensions.primitives.int.*


/**
 * Creates a new list by repeating this list the given number of times.
 * so if you set repeat by to 1 you will get a copy, and 2 will be 2 copies.
 * a negative / 0 repeatBy will give you an empty list.
 * @receiver [List]<T>
 * @param repeatBy [Int]
 * @return [List]<T>
 */
public inline fun <T> List<T>.repeat(
    @IntLimit(from = 0) repeatBy: Int
): List<T> {
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
 * @receiver [List]<T>
 * @param toSize [Int]
 * @return [List]<T> a new list to the desired size, by repeating this list, over and over again.
 */
public inline fun <reified T> List<T>.repeatToSize(@IntLimit(from = 0) toSize: Int): List<T> {
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