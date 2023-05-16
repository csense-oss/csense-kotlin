@file:Suppress("unused", "NOTHING_TO_INLINE", "INVISIBLE_MEMBER", "INVISIBLE_REFERENCE")
package csense.kotlin.extensions.collections.set


/**
 * Computes the symmetric difference (subtraction of both sets)
 * so if A = { 1,2,3,4,5}
 * and B = {1,5,7,9,0}
 * then the results are
 * Pair.first = {2,3,4} (UNIQUE IN A)
 * Pair.second = {7,9,0} UNIQUE IN B
 * @receiver [Set]<T>
 * @param otherSet [Set]<T>
 * @return [SymmetricSetDifferenceResult]<T>
 * The time complexity should be around "O(N)" Where N is the largest size of the sets.
 * @timecomplexity O(N)
 */
public inline fun <@kotlin.internal.OnlyInputTypes T> Set<T>.symmetricDifference(otherSet: Set<T>): SymmetricSetDifferenceResult<T> {
    val aDif = toMutableSet()
    val bDif = mutableSetOf<T>()
    otherSet.forEach {
        if (aDif.contains(it)) {
            aDif.remove(it)
        } else {
            bDif.add(it)
        }
    }
    return SymmetricSetDifferenceResult(aDif, bDif)
}


/**
 * Represents the result from a symmetric difference
 * @param T the type of the sets.
 * @property uniqueInFirst [Set]<T> the elements that were ONLY found in the first set
 * @property uniqueInSecond [Set]<T> the elements that were ONLY found in the second set.
 */
public data class SymmetricSetDifferenceResult<T>(
    public val uniqueInFirst: Set<T>,
    public val uniqueInSecond: Set<T>
)
