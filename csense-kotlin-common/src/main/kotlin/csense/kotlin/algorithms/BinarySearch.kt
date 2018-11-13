@file:Suppress("unused", "NOTHING_TO_INLINE")

package csense.kotlin.algorithms

import csense.kotlin.algorithms.generic.*
import csense.kotlin.extensions.collections.*


/**
 * Performs the binary search algorithm , by providing a custom compare function, that given the current item and the index,
 * yielding the comparison result
 * @receiver List<T>
 * @param comparere Function2<T, Int, Comparing>
 * @return Int? null if no predicate was equal, or the index if any was found to be equal.
 */
inline fun <T> List<T>.binarySearch(crossinline comparere: Function2<T, Int, Comparing>): Int? =
        GenericAlgorithms.binarySearch(size, this::get, comparere)

/**
 * Performs the binary search algorithm , by providing a custom compare function, that given the current item and the index,
 * yielding the comparison result
 * @receiver Array<T>
 * @param comparere Function2<T, Int, Comparing>
 * @return Int? null if no predicate was equal, or the index if any was found to be equal.
 */
inline fun <T> Array<T>.binarySearch(crossinline comparere: Function2<T, Int, Comparing>): Int? =
        GenericAlgorithms.binarySearch(size, this::get, comparere)

/**
 * Performs the binary search algorithm , by providing a custom compare function, that given the current item and the index,
 * yielding the comparison result
 * @receiver ShortArray
 * @param comparere Function2<Short, Int, Comparing>
 * @return Int? null if no predicate was equal, or the index if any was found to be equal.
 */
inline fun ShortArray.binarySearch(crossinline comparere: Function2<Short, Int, Comparing>): Int? =
        GenericAlgorithms.binarySearch(size, this::get, comparere)

/**
 * Performs the binary search algorithm , by providing a custom compare function, that given the current item and the index,
 * yielding the comparison result
 * @receiver IntArray
 * @param comparere Function2<Int, Int, Comparing>
 * @return Int? null if no predicate was equal, or the index if any was found to be equal.
 */
inline fun IntArray.binarySearch(crossinline comparere: Function2<Int, Int, Comparing>): Int? =
        GenericAlgorithms.binarySearch(size, this::get, comparere)

/**
 * Performs the binary search algorithm , by providing a custom compare function, that given the current item and the index,
 * yielding the comparison result
 * @receiver LongArray
 * @param comparere Function2<Long, Int, Comparing>
 * @return Int? null if no predicate was equal, or the index if any was found to be equal.
 */
inline fun LongArray.binarySearch(crossinline comparere: Function2<Long, Int, Comparing>): Int? =
        GenericAlgorithms.binarySearch(size, this::get, comparere)

/**
 * Performs the binary search algorithm , by providing a custom compare function, that given the current item and the index,
 * yielding the comparison result
 * @receiver FloatArray
 * @param comparere Function2<Float, Int, Comparing>
 * @return Int? null if no predicate was equal, or the index if any was found to be equal.
 */
inline fun FloatArray.binarySearch(crossinline comparere: Function2<Float, Int, Comparing>): Int? =
        GenericAlgorithms.binarySearch(size, this::get, comparere)

/**
 * Performs the binary search algorithm , by providing a custom compare function, that given the current item and the index,
 * yielding the comparison result
 * @receiver DoubleArray
 * @param comparere Function2<Double, Int, Comparing>
 * @return Int? null if no predicate was equal, or the index if any was found to be equal.
 */
inline fun DoubleArray.binarySearch(crossinline comparere: Function2<Double, Int, Comparing>): Int? =
        GenericAlgorithms.binarySearch(size, this::get, comparere)


/**
 * Performs the binary search algorithm , by providing a custom compare function, that given the current item and the index,
 * yielding the comparison result
 * The generic algorithm
 * @receiver GenericAlgorithms (to limit the namespace)
 * @param length Int
 * @param constantGetter GenericGetterIndexMethod<T>
 * @param comparere Function2<T, Int, Comparing>
 * @return Int? null if no predicate was equal, or the index if any was found to be equal.
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
            Comparing.LessThan -> start = mid + 1 // the item is less than what we are looking for
            Comparing.LargerThan -> end = mid //the item is larger than what we are looking for
            Comparing.Equal -> return mid // this is what we are searching for.
        }
    }
    return null
}