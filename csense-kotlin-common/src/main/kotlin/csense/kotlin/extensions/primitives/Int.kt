@file:Suppress("unused", "NOTHING_TO_INLINE")

package csense.kotlin.extensions.primitives

import csense.kotlin.*
import kotlin.math.*

/**
 * Gets this int negative, if it is already negative, returns that.
 * this is also negative Abs.
 */
inline val Int.negative: Int
    get() = minOf(this, -this)


/**
 * this int positive, if it is already postive, returns that.
 * also known as abs
 */
inline val Int.positive: Int
    get () = absoluteValue

/**
 *  if this int is not 0 => returns true. false otherwise
 */
inline val Int.isNotZero: Boolean
    get() = !isZero

/**
 *  if this int is 0 => returns true. false otherwise
 */
inline val Int.isZero: Boolean
    get() = this == 0

/**
 * If this value is less than 0 then its negative
 */
inline val Int.isNegative: Boolean
    get() = this < 0

/**
 * A value is positive iff its greater than neutral (0)
 */
inline val Int.isPositive: Boolean
    get() = this > 0


/**
 * if this whole number is even (2,4,6....)
 */
inline val Int.isEven: Boolean
    get() = this % 2 == 0
/**
 * If this whole number is odd (1,3,5 ...)
 */
inline val Int.isOdd: Boolean
    get() = !isEven

/**
 * Does the given action this values times
 * if this value is less than or equal to 0, then the action is not ran
 * @receiver Int
 * @param action FunctionUnit<Int> the action to perform each this values (positive) times
 */
inline fun Int.forEach(action: FunctionUnit<Int>) {
    if (this.isZero || this.isNegative) {
        return
    }
    for (i in 0 until this) {
        action(i)
    }
}
