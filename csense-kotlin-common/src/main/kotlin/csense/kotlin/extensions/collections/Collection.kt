package csense.kotlin.extensions.collections

/**
 * Validates the given index for the given collection (so [0;length[)
 * @receiver Collection<*>
 * @param index Int
 * @return Boolean
 */
@Suppress("NOTHING_TO_INLINE")
inline fun Collection<*>.isIndexValid(index: Int) =
        index >= 0 && index < count()

/**
 * Validates the given index for the given collection, accounting for inserting in the end (so [0;length])
 * @receiver Collection<*>
 * @param index Int
 * @return Boolean
 */
@Suppress("NOTHING_TO_INLINE")
inline fun Collection<*>.isIndexValidForInsert(index: Int) =
        index >= 0 && index <= count()

/**
 * Element at witouth throwing exception but instead returning null if index out of bounds
 * @receiver Collection<T>
 * @param index Int
 * @return T?
 */
@Suppress("NOTHING_TO_INLINE")
inline fun <T> Collection<T>.getSafe(index: Int): T? =
        if (this.isIndexValid(index)) {
            elementAt(index)
        } else {
            null
        }

/**
 * Tells if the given range is in the collection (akk range in [0; length[
 * @receiver Collection<*>
 * @param intRange IntRange
 * @return Boolean
 */
@Suppress("NOTHING_TO_INLINE")
inline fun Collection<*>.isRangeValid(intRange: IntRange): Boolean =
        (intRange.start >= 0 && intRange.endInclusive < size)