@file:Suppress("unused", "NOTHING_TO_INLINE")

package csense.kotlin.extensions.collections.list

import csense.kotlin.*
import csense.kotlin.annotations.numbers.*
import csense.kotlin.extensions.*
import csense.kotlin.extensions.primitives.*

/**
 * Returns a limited view of this list, by limiting the size of it (if the list is shorter than the limit,
 * then the result will be the lists' length).
 * @receiver [List]<E>
 * @param [size] Int
 * @return [List]<E>
 */

public inline fun <E> List<E>.limitToSize(@IntLimit(from = 0) size: Int): List<E> {
    if (size.isNegativeOrZero) {
        return emptyList()
    }
    return subList(0, minOf(this.size, size))
}

/**
 * Creates a sublist with the given range (including the end)
 * Checks bounds before accessing. returns empty list if out of bounds.
 * @receiver [List]<T>
 * @param intRange [IntRange]
 * @return [List]<T>
 */
public inline fun <T> List<T>.subList(intRange: IntRange): List<T> =
    subListSafe(intRange.first, intRange.last)

/**
 * Creates a sublist from the given [fromIndex] to the end
 * Checks bounds before accessing. returns empty list if out of bounds.
 * @receiver [List]<T>
 * @param fromIndex [Int] where from we should start including elements
 * @return [List]<T>
 */
public inline fun <T> List<T>.subList(
    @IntLimit(from = 0) fromIndex: Int
): List<T> = subListSafe(fromIndex, size)


/**
 * Extracts a sublist or returns an empty list if the requested range is out of bounds.
 * This is a safe alternative to the standard library edition that goes out of bounds.
 * @receiver [List]<T>
 * @param fromIndex [Int] (inclusive)  [0;size[
 * @param toIndex [Int] (exclusive)    ]fromIndex;size[
 * @return [List]<T>
 */
public inline fun <T> List<T>.subListSafe(
    @IntLimit(from = 0) fromIndex: Int,
    @IntLimit(from = 0) toIndex: Int
): List<T> {
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

/**
 * invokes the given action on each item that is of the expected type (U)
 * @receiver [List]<*>
 * @param indices [IntProgression] the indexes to go over.
 * @param action [Function1]<U, *>
 */
public inline fun <reified U> List<*>.forEachIsInstance(
    indices: IntProgression,
    action: Function1<U, *>
): Unit = indices.forEach {
    getOrNull(it)?.invokeIsInstance(action)
}


/**
 * Combines the inner list of the other with this.
 * The length will always match the longest of the receiver and the other parameter.
 * @receiver [List]<[List]<T>>
 * @param other [List]<[List]<T>>
 * @return [List]<[List]<T>>
 */
public inline fun <T> List<List<T>>.combine(
    other: List<List<T>>
): List<List<T>> {
    val isOtherLarger = other.size > this.size
    val largest = isOtherLarger.map(other, this)
    val smallest = isOtherLarger.map(this, other)
    return largest.mapIndexed { index, list ->
        list + smallest.getOrNull(index).orEmpty()
    }
}

/**
 * Tells if there are no element of the given
 *
 * @receiver [List]<T> the list to check
 * @param element T the element to search for
 * @return [Boolean] true if the element was not found, false if it was found.
 */
public inline fun <T> List<T>.doesNotContain(element: T): Boolean =
    !contains(element)