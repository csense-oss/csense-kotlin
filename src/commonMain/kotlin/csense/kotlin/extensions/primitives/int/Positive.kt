package csense.kotlin.extensions.primitives.int

import csense.kotlin.annotations.numbers.*
import kotlin.math.*


/**
 * this [Int] positive, if it is already positive, returns that.
 *
 * also known as abs
 */
public inline val Int.positive: Int
    @IntLimit(from = 0)
    get() = absoluteValue