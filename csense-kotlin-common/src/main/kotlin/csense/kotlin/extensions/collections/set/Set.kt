@file:Suppress("NOTHING_TO_INLINE", "unused")

package csense.kotlin.extensions.collections.set

import csense.kotlin.*
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
