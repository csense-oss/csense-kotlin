@file:Suppress("unused", "NOTHING_TO_INLINE", "INVISIBLE_MEMBER", "INVISIBLE_REFERENCE")

package csense.kotlin.extensions.collections.set

import csense.kotlin.*
import csense.kotlin.extensions.collections.generic.collection.*
import csense.kotlin.extensions.collections.generic.collection.operations.*


//region Generic collection extensions

/**
 * Performs backwards traversal on this
 * @receiver [List]<T>
 * @param action [FunctionUnit]<T>
 * @timecomplexity O(N)
 */
public inline fun <T> Set<T>.foreachBackwards(action: FunctionUnit<T>): Unit =
    GenericCollections.forEachBackwards(size, this::elementAt, action)
//endregion

/**
 * Computes the symmetric difference (subtraction of both sets)
 * so if A = { 1,2,3,4,5}
 * and B = {1,5,7,9,0}
 * then the results are
 * Pair.first = {2,3,4} (UNIQUE IN A)
 * Pair.second = {7,9,0} UNIQUE IN B
 * @receiver [Set]<T>
 * @param otherSet [Set]<T>
 * @return [SymmetricDifferenceResult]<T>
 * The time complexity should be around "O(N)" Where N is the largest size of the sets.
 * @timecomplexity O(N)
 */
public inline fun <@kotlin.internal.OnlyInputTypes T> Set<T>.symmetricDifference(otherSet: Set<T>): SymmetricDifferenceResult<T> {
    val aDif = toMutableSet()
    val bDif = mutableSetOf<T>()
    otherSet.forEach {
        if (aDif.contains(it)) {
            aDif.remove(it)
        } else {
            bDif.add(it)
        }
    }
    return SymmetricDifferenceResult(aDif, bDif)
}

/**
 * Represents the result from a symmetric difference
 * @param T the type of the sets.
 * @property uniqueInFirst [Set]<T> the elements that were ONLY found in the first set
 * @property uniqueInSecond [Set]<T> the elements that were ONLY found in the second set.
 */
public class SymmetricDifferenceResult<T>(
    public val uniqueInFirst: Set<T>,
    public val uniqueInSecond: Set<T>
)

/**
 * Tells if the given value is not in the set
 * @receiver [Set]<T> the set to test for the given value
 * @param value T the value to test existence of
 * @return [Boolean] true if the value is not found / contained
 */
public inline fun <@kotlin.internal.OnlyInputTypes T> Set<T>.doesNotContain(value: T): Boolean =
    !contains(value)


/**
 * Tells if this set does contain any of the given data
 * Also known as "disjoint"
 * Optimized for other being a [Set]
 * @receiver [Set]<E> the
 * @param other [Iterable]<E>
 * @return [Boolean] true if at least one element from other is in this set.
 * @timecomplexity worst case o(n * lg(n))
 */
public inline fun <@kotlin.internal.OnlyInputTypes E> Set<E>.containsAny(other: Iterable<E>): Boolean {
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
 * @return [Boolean] true if there are no disjoint elements
 * @timecomplexity worst case o(n * lg(n))
 */
public inline fun <@kotlin.internal.OnlyInputTypes E> Set<E>.doesNotContainAny(other: Iterable<E>): Boolean {
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
