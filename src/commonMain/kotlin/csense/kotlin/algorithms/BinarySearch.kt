@file:Suppress("unused", "NOTHING_TO_INLINE")

package csense.kotlin.algorithms

import csense.kotlin.algorithms.generic.GenericAlgorithms
import csense.kotlin.annotations.numbers.IntLimit
import csense.kotlin.extensions.collections.GenericGetterIndexMethod


/**
 * Performs the binary search algorithm , by providing a custom compare function, that given the current item and the index,
 * yielding the comparison result
 * @receiver List<T>
 * @param compare Function2<T, Int, ItemComparison>
 * @return Int? null if no predicate was equal, or the index if any was found to be equal.
 */
@IntLimit(from = 0)
//TODO RandomAccessList ?
inline fun <T> List<T>.binarySearch(crossinline compareFnc: Function2<T, Int, ItemComparison>): Int? =
        GenericAlgorithms.binarySearch(size, this::get, compareFnc)

/**
 * Performs the binary search algorithm , by providing a custom compare function, that given the current item and the index,
 * yielding the comparison result
 * @receiver Array<T>
 * @param comparere Function2<T, Int, ItemComparison>
 * @return Int? null if no predicate was equal, or the index if any was found to be equal.
 */
@IntLimit(from = 0)
inline fun <T> Array<T>.binarySearch(crossinline compareFnc: Function2<T, Int, ItemComparison>): Int? =
        GenericAlgorithms.binarySearch(size, this::get, compareFnc)

/**
 * Performs the binary search algorithm , by providing a custom compare function, that given the current item and the index,
 * yielding the comparison result
 * @receiver ShortArray
 * @param comparere Function2<Short, Int, ItemComparison>
 * @return Int? null if no predicate was equal, or the index if any was found to be equal.
 */
@IntLimit(from = 0)
inline fun ShortArray.binarySearch(crossinline compareFnc: Function2<Short, Int, ItemComparison>): Int? =
        GenericAlgorithms.binarySearch(size, this::get, compareFnc)

/**
 * Performs the binary search algorithm , by providing a custom compare function, that given the current item and the index,
 * yielding the comparison result
 * @receiver IntArray
 * @param comparere Function2<Int, Int, ItemComparison>
 * @return Int? null if no predicate was equal, or the index if any was found to be equal.
 */
@IntLimit(from = 0)
inline fun IntArray.binarySearch(crossinline compareFnc: Function2<Int, Int, ItemComparison>): Int? =
        GenericAlgorithms.binarySearch(size, this::get, compareFnc)

/**
 * Performs the binary search algorithm , by providing a custom compare function, that given the current item and the index,
 * yielding the comparison result
 * @receiver LongArray
 * @param comparere Function2<Long, Int, ItemComparison>
 * @return Int? null if no predicate was equal, or the index if any was found to be equal.
 */
@IntLimit(from = 0)
inline fun LongArray.binarySearch(crossinline compareFnc: Function2<Long, Int, ItemComparison>): Int? =
        GenericAlgorithms.binarySearch(size, this::get, compareFnc)

/**
 * Performs the binary search algorithm , by providing a custom compare function, that given the current item and the index,
 * yielding the comparison result
 * @receiver FloatArray
 * @param comparere Function2<Float, Int, ItemComparison>
 * @return Int? null if no predicate was equal, or the index if any was found to be equal.
 */
@IntLimit(from = 0)
inline fun FloatArray.binarySearch(crossinline compareFnc: Function2<Float, Int, ItemComparison>): Int? =
        GenericAlgorithms.binarySearch(size, this::get, compareFnc)

/**
 * Performs the binary search algorithm , by providing a custom compare function, that given the current item and the index,
 * yielding the comparison result
 * @receiver DoubleArray
 * @param comparere Function2<Double, Int, ItemComparison>
 * @return Int? null if no predicate was equal, or the index if any was found to be equal.
 */
@IntLimit(from = 0)
inline fun DoubleArray.binarySearch(crossinline compareFnc: Function2<Double, Int, ItemComparison>): Int? =
        GenericAlgorithms.binarySearch(size, this::get, compareFnc)


/**
 * Performs the binary search algorithm , by providing a custom compare function, that given the current item and the index,
 * yielding the comparison result
 * The generic algorithm
 * @receiver GenericAlgorithms (to limit the namespace)
 * @param length Int
 * @param constantGetter GenericGetterIndexMethod<T>
 * @param comparere Function2<T, Int, ItemComparison>
 * @return Int? null if no predicate was equal, or the index if any was found to be equal.
 */
@IntLimit(from = 0)
inline fun <T> GenericAlgorithms.binarySearch(
        @IntLimit(from = 0) length: Int,
        crossinline constantGetter: GenericGetterIndexMethod<T>,
        crossinline compareFnc: Function2<T, Int, ItemComparison>): Int? {
    var start = 0
    var end = length
    while (start < end) {
        val mid = start + (end - start) / 2
        val item = constantGetter(mid)
        when (compareFnc(item, mid)) {
            ItemComparison.LessThan -> start = mid + 1 // the item is less than what we are looking for
            ItemComparison.LargerThan -> end = mid //the item is larger than what we are looking for
            ItemComparison.Equal -> return mid // this is what we are searching for.
        }
    }
    return null
}