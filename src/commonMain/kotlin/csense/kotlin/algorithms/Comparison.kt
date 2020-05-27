@file:Suppress("unused", "NOTHING_TO_INLINE")

package csense.kotlin.algorithms

import csense.kotlin.extensions.ranges.largest
//TODO consider moving to algorithm module

//TODO "inline" when feature available. see
/**
 * A comparison between 2 elements;
 * if equal (x == y) then "Equal"
 * if x > y then Larger than
 * if x < y then less than.
 */
enum class ItemComparison {
    /**
     * x > y then Larger than
     */
    LargerThan,
    /**
     * x < y then less than.
     */
    LessThan,
    /**
     * (x == y) then "Equal"
     */
    Equal
}

/**
 * Compares a regular "compareTo" into a comparing.
 * @receiver Int
 */
inline fun Int.toComparing(): ItemComparison {
    return when {
        this == 0 -> ItemComparison.Equal
        this > 0 -> ItemComparison.LargerThan
        else -> ItemComparison.LessThan
    }
}


/**
 * Compares this number to a range defined by the given from and to (as a whole),
 * thus its equal iff in range, and then otherwise above or below.
 * @receiver Int
 * @param fromInclusive Int
 * @param toInclusive Int
 * @return ItemComparison
 */
inline fun Int.compareToRange(fromInclusive: Int, toInclusive: Int): ItemComparison {
    //make it a bit more "defined" behavior.
    return compareToRange(fromInclusive until toInclusive)
}


/**
 * Compares this number to a range (as a whole),
 * thus its equal iff in range, and then otherwise above or below.
 * @receiver Int
 * @param intRange IntRange
 * @return ItemComparison
 */
inline fun Int.compareToRange(intRange: IntRange): ItemComparison {
    return when {
        this in intRange -> ItemComparison.Equal
        this > intRange.largest -> ItemComparison.LargerThan
        else -> ItemComparison.LessThan
    }

}