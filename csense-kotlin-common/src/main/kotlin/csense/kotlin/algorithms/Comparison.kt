@file:Suppress("unused", "NOTHING_TO_INLINE")

package csense.kotlin.algorithms

//TODO "inline" when feature available. see
/**
 * A comparison between 2 elements;
 * if equal (x == y) then "Equal"
 * if x > y then Larger than
 * if x < y then less than.
 */
enum class Comparing {
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
inline fun Int.toComparing(): Comparing {
    return when {
        this == 0 -> Comparing.Equal
        this > 0 -> Comparing.LargerThan
        else -> Comparing.LessThan
    }
}

