@file:Suppress("NOTHING_TO_INLINE", "unused")

package csense.kotlin.extensions.collections.set

import csense.kotlin.FunctionUnit
import csense.kotlin.extensions.*
import csense.kotlin.extensions.collections.generic.*


//region Generic collection extensions
/**
 * Performs traversal in pairs of 2  (with the first index as well)
 * @timecomplexity O(N)
 * @receiver List<T>
 * @param action Function2IndexedUnit<T, T>
 */
inline fun <T> Set<T>.foreach2Indexed(action: Function2IndexedUnit<T, T>) =
        GenericCollectionExtensions.forEach2Indexed(
                size,
                ::elementAt,
                action
        )

/**
 * Performs traversal in pairs of 2
 * @timecomplexity O(N)
 * @receiver List<T>
 * @param action Function2Unit<T, T>
 */
inline fun <T> Set<T>.foreach2(action: Function2Unit<T, T>) =
        GenericCollectionExtensions.forEach2(size, ::elementAt, action)

/**
 * Performs backwards traversal on this
 * @timecomplexity O(N)
 * @receiver List<T>
 * @param action FunctionUnit<T>
 */
inline fun <T> Set<T>.foreachBackwards(action: FunctionUnit<T>) =
        GenericCollectionExtensions.forEachBackwards(size, this::elementAt, action)
//endregion

/**
 * Computes the symmetric difference (subtraction of both sets)
 * so if A = { 1,2,3,4,5}
 * and B = {1,5,7,9,0}
 * then the results are
 * Pair.first = {2,3,4} (UNIQUE IN A)
 * Pair.second = {7,9,0} UNIQUE IN B
 * The Timecomplexity should be around "O(N)" Where N is the largest size of the sets.
 * @timecomplexity O(N)
 * @receiver Set<T>
 * @param otherSet Set<T>
 * @return Pair<Set<T>, Set<T>>
 */
@Deprecated("Return type will change in 0.40.0 to a class", level = DeprecationLevel.WARNING)
inline fun <T> Set<T>.symmetricDifference(otherSet: Set<T>): Pair<Set<T>, Set<T>> {
    val aDif = toMutableSet()
    val bDif = mutableSetOf<T>()
    
    otherSet.forEach {
        if (aDif.contains(it)) {
            aDif.remove(it)
        } else {
            bDif.add(it)
        }
    }
    return Pair(aDif, bDif)
}

/**
 * Tells if the given value is not in the set
 * @receiver Set<T> the set to test for the given value
 * @param value T the value to test existence of
 * @return Boolean true if the value is not found / contained
 */
inline fun <T> Set<T>.doesNotContain(value: T): Boolean =
        !contains(value)


/**
 * Tells if this set does contain any of the given data
 * Also known as "disjoint"
 * Optimized for other being a [Set]
 * @receiver [Set]<E> the
 * @param other [Iterable]<E>
 * @return Boolean true if at least one element from other is in this set.
 * @timecomplexity worstcase o(n * lg(n))
 */
inline fun <E> Set<E>.containsAny(other: Iterable<E>): Boolean {
    return if (other is Set) {
        //if we have 2 sets, then the optimization is to iterate over the smallest
        // set and call contains on the larger (linear is slower than logarithm)
        val smallest: Set<E>
        val largest: Set<E>
        if (size > other.size) {
            smallest = other
            largest = this
        } else {
            smallest = this
            largest = other
        }
        smallest.any { largest.contains(it) }
    } else {
        other.any { contains(it) }
    }
    
}

/**
 * Tells if this set does not contain any of the given data
 *
 * Optimized for other being a [Set]
 * @receiver [Set]<E>
 * @param other [Iterable]<E>
 * @return Boolean true if there are no disjoint elements
 * @timecomplexity worstcase o(n * lg(n))
 */
inline fun <E> Set<E>.doesNotContainAny(other: Iterable<E>): Boolean {
    return if (other is Set) {
        //if we have 2 sets, then the optimization is to iterate over the smallest
        // set and call contains on the larger (linear is slower than logarithm)
        val smallest: Set<E>
        val largest: Set<E>
        if (size > other.size) {
            smallest = other
            largest = this
        } else {
            smallest = this
            largest = other
        }
        smallest.none { largest.contains(it) }
    } else {
        other.none { contains(it) }
    }
}
