package org.csenseoss.kotlin.extensions.primitives.int

import org.csenseoss.kotlin.annotations.numbers.limit.*
import kotlin.math.*


/**
 * this [Int] positive, if it is already positive, returns that.
 *
 * also known as abs
 */
public inline val Int.positive: Int
    @IntLimit(from = 0)
    get() = absoluteValue