@file:Suppress("unused")

package csense.kotlin.extensions.ranges

import csense.kotlin.annotations.numbers.IntLimit


/**
 * The length between last and start [start;last[
 */
inline val IntRange.length: Int
    @IntLimit(from = 0) //the length of a progression is 0 and up
    get() = (last - start) + 1 //+1 since start is inclusive.

/**
 * The largest value (of last / start)
 */
inline val IntRange.largest: Int
    get() = maxOf(last, start)
/**
 * The maximum value in the range (exclusive)
 */
inline val IntRange.endExclusive: Int
    get() = last + 1 //since  +1 is the next size thus above end inclusive is +1