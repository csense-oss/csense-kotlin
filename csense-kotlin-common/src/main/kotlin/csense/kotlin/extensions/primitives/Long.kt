package csense.kotlin.extensions.primitives

import kotlin.math.*


/**
 * Gets this int negative, if it is already negative, returns that.
 * this is also negative Abs.
 */
inline val Long.negative: Long
    get() = minOf(this, -this)


/**
 * this int positive, if it is already postive, returns that.
 * also known as abs
 */
inline val Long.positive: Long
    get () = absoluteValue

/**
 *  if this int is not 0 => returns true. false otherwise
 */
inline val Long.isNotZero: Boolean
    get() = !isZero

/**
 *  if this int is 0 => returns true. false otherwise
 */
inline val Long.isZero: Boolean
    get() = this == 0L

/**
 * Tells if this number is either negative or zero
 */
inline val Long.isNegativeOrZero: Boolean
    get() = this.isNegative || this.isZero

/**
 * Tells if this number is either positive or zero
 */
inline val Long.isPositiveOrZero: Boolean
    get() = this.isPositive || this.isZero


/**
 * If this value is less than 0 then its negative
 */
inline val Long.isNegative: Boolean
    get() = this < 0

/**
 * A value is positive iff its greater than neutral (0)
 */
inline val Long.isPositive: Boolean
    get() = this > 0


/**
 * if this whole number is even (2,4,6....)
 */
inline val Long.isEven: Boolean
    get() = this % 2L == 0L
/**
 * If this whole number is odd (1,3,5 ...)
 */
inline val Long.isOdd: Boolean
    get() = !isEven