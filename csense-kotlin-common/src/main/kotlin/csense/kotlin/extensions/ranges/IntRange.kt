package csense.kotlin.extensions.ranges


/**
 * The length between last and start [start;last[
 */
inline val IntRange.length: Int
    get() = (last - start) + 1 //+1 since start is inclusive.

/**
 * The largest value (of last / start)
 */
inline val IntRange.largest: Int
    get() = maxOf(last, start)