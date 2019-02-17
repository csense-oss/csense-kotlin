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

/*
TODO ????
inline fun Int.compareToRange(from: Int, to: Int): Comparing {
    //make it a bit more "defined" behavior.
    if (from > to) {
        return Comparing.LessThan
    }
    return when {
        this in (from..to) -> Comparing.Equal
        this > to -> Comparing.LargerThan
        else -> Comparing.LessThan
    }
}



 */