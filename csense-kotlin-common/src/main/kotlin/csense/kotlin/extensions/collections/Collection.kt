@file:Suppress("unused", "NOTHING_TO_INLINE")

package csense.kotlin.extensions.collections

import csense.kotlin.extensions.primitives.*

/**
 * Validates the given index for the given collection (so 0 until length)
 * @receiver Collection<*>
 * @param index Int
 * @return Boolean
 */

inline fun Collection<*>.isIndexValid(index: Int) =
        index >= 0 && index < count()

/**
 * Validates the given index for the given collection, accounting for inserting in the end (so 0 until (including) length)
 * @receiver Collection<*>
 * @param index Int
 * @return Boolean
 */

inline fun Collection<*>.isIndexValidForInsert(index: Int) =
        index >= 0 && index <= count()

/**
 * Element at witouth throwing exception but instead returning null if index out of bounds
 * @receiver Collection<T>
 * @param index Int
 * @return T?
 */

inline fun <T> Collection<T>.getSafe(index: Int): T? =
        if (this.isIndexValid(index)) {
            elementAt(index)
        } else {
            null
        }

/**
 * Tells if the given range is in the collection (akk range in [ 0 ; length [
 * @receiver Collection<*>
 * @param intRange IntRange
 * @return Boolean
 */
inline fun Collection<*>.isRangeValid(intRange: IntRange): Boolean =
        (intRange.start >= 0 &&
                intRange.endInclusive >= 0 &&
                intRange.endInclusive < size &&
                intRange.start <= size)


/**
 * Maps the given list into "buckets" / akk categorizes the items.
 * item will appear in multiple buckets / categories iff multiple filters accept them
 * @receiver Collection<T>
 * @param filters Array<out Function1<T, Boolean>>
 * @return List<List<T>>
 */
inline fun <T> Collection<T>.categorizeIntoMultiple(vararg filters: Function1<T, Boolean>): List<List<T>> =
        this.categorizeInto(*filters, allowItemInMultipleBuckets = true)


/**
 * Maps the given list into "buckets" / akk categorizes the items.
 * items will NOT appear in multiple buckets / categories even if multiple filters accept them (first one wins)
 * @receiver Collection<T>
 * @param filters Array<out Function1<T, Boolean>>
 * @return List<List<T>>
 */
inline fun <T> Collection<T>.categorizeIntoSingle(vararg filters: Function1<T, Boolean>): List<List<T>> =
        this.categorizeInto(*filters, allowItemInMultipleBuckets = false)

/**
 *
 * @receiver Collection<T>
 * @param filters Array<out Function1<T, Boolean>>
 * @param allowItemInMultipleBuckets Boolean
 * @return List<List<T>>
 */
inline fun <T> Collection<T>.categorizeInto(vararg filters: Function1<T, Boolean>, allowItemInMultipleBuckets: Boolean = true): List<List<T>> {
    val result = ArrayList(filters.map { mutableListOf<T>() })
    this.forEach {
        filters.forEachIndexed { index, filterAccepts ->
            filterAccepts(it).onTrue {
                result[index].add(it)
                //should we stop finding filters that accepts this item ? if so then go on.
                allowItemInMultipleBuckets.ifFalse {
                    return@forEach //break to the foreach, akk go on to the next element
                }
            }
        }
    }
    return result
}