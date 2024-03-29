@file:Suppress("unused")

package csense.kotlin.extensions.ranges

import csense.kotlin.annotations.numbers.*


/**
 * The length between last and start [start;last[
 */
public inline val IntRange.length: Int
    @IntLimit(from = 0) //the length of a progression is 0 and up
    get() = (last - start) + 1 //+1 since start is inclusive.

/**
 * The largest value (of last / start)
 */
public inline val IntRange.largest: Int
    get() = maxOf(last, start)
