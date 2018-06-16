package csense.kotlin.extensions.ranges


inline val IntRange.length: Int
    get() = (last - start) + 1 //+1 since start is inclusive.

inline val IntRange.largest: Int
    get() = maxOf(last, start)