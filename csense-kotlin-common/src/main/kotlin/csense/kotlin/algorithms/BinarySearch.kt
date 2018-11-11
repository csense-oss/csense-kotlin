@file:Suppress("unused", "NOTHING_TO_INLINE")

package csense.kotlin.algorithms

import csense.kotlin.algorithms.generic.*
import csense.kotlin.extensions.collections.*

inline fun <T : Comparable<T>> List<T>.binarySearch(crossinline comparere: Function2<T, Int, Comparing>): Int? {
    var start = 0
    var end = size
    while (start < end) {
        val mid = start + (end - start) / 2
        val item = get(mid)
        val compResult = comparere(item, mid)
        when (compResult) {
            Comparing.LargerThan -> start = mid + 1
            Comparing.LessThan -> end = mid
            Comparing.Equal -> return mid
        }
    }
    return null
}


inline fun <T> Array<T>.binarySearch(
        crossinline comparere: Function2<T, Int, Comparing>): Int? =
        GenericAlgorithms.binarySearch(count(), this::get, comparere)


/**
 *
 * @receiver GenericAlgorithms
 */
inline fun <T> GenericAlgorithms.binarySearch(
        length: Int,
        constantGetter: GenericGetterIndexMethod<T>,
        crossinline comparere: Function2<T, Int, Comparing>): Int? {
    var start = 0
    var end = length
    while (start < end) {
        val mid = start + (end - start) / 2
        val item = constantGetter(mid)
        val compResult = comparere(item, mid)
        when (compResult) {
            Comparing.LargerThan -> start = mid + 1
            Comparing.LessThan -> end = mid
            Comparing.Equal -> return mid
        }
    }
    return null
}