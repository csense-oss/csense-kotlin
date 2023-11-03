package csense.kotlin.extensions.primitives.int

import csense.kotlin.annotations.numbers.*


/**
 * Gets this [Int] negative, if it is already negative, returns that.
 *
 * this is also negative Abs.
 */
public inline val Int.negative: Int
    @IntLimit(to = 0)
    get() = minOf(this, -this)