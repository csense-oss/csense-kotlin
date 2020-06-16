@file:Suppress("unused")

package csense.kotlin.extensions.primitives

import csense.kotlin.annotations.numbers.LongLimit
import kotlin.math.absoluteValue


/**
 * Gets this [Long] negative, if it is already negative, returns that.
 *
 * this is also negative Abs.
 */
inline val Long.negative: Long
    @LongLimit(to = 0)
    get() = minOf(this, -this)


/**
 * this [Long] positive, if it is already positive, returns that.
 *
 * also known as abs
 */
inline val Long.positive: Long
    @LongLimit(from = 0)
    get() = absoluteValue

/**
 *  if this int is not 0 => returns true. false otherwise
 */
inline val Long.isNotZero: Boolean
    get() = !isZero

/**
 *  if this [Long] is 0 => returns true. false otherwise
 */
inline val Long.isZero: Boolean
    get() = this == 0L

/**
 * Tells if this [Long] is either negative or zero
 */
inline val Long.isNegativeOrZero: Boolean
    get() = this.isNegative || this.isZero

/**
 * Tells if this [Long] is either positive or zero
 */
inline val Long.isPositiveOrZero: Boolean
    get() = this.isPositive || this.isZero


/**
 * If this [Long] is less than 0 then its negative
 */
inline val Long.isNegative: Boolean
    get() = this < 0

/**
 * A value is positive iff its greater than neutral (0)
 * returns true if this [Long] is positive
 */
inline val Long.isPositive: Boolean
    get() = this > 0


/**
 * if this [Long] is even (2,4,6....)
 */
inline val Long.isEven: Boolean
    get() = this % 2L == 0L
/**
 * If this [Long] is odd (1,3,5 ...)
 */
inline val Long.isOdd: Boolean
    get() = !isEven