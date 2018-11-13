@file:Suppress("unused", "NOTHING_TO_INLINE")
package csense.kotlin.extensions.primitives

import kotlin.math.*


/**
 * Gets this int negative, if it is already negative, returns that.
 */
inline val Double.negative: Double
    get() = minOf(this, -this)

/**
 *
 */
inline val Double.positive: Double
    get() = this.absoluteValue
/**
 *
 */
inline val Double.isNegative: Boolean
    get() = this < 0

/**
 *
 */
inline val Double.isPositive: Boolean
    get() = this > 0

/**
 * if this double is 0 within a margin of 0.1 akk
 * in range  (from inclusive) -0.1 to 0.1 inclusive
 * If you need more margin, see Double.equalWithin
 */
inline val Double.isZero: Boolean
    get() = this.equalWithin(0.0, 0.1)

/**
 * Compares this double to the given value,with a margin;
 * this is done since doubles / floating numbers are not precise in computers.
 * @receiver Double
 * @param value Double The value we are comparing to
 * @param margin Double the delta / margin we are comparing within
 * the margin will always be converted to positive,
 * since a negative margin would always yield false
 * @return Boolean
 */
@Suppress("NOTHING_TO_INLINE")
inline fun Double.equalWithin(value: Double, margin: Double = 0.1): Boolean {
    val lower = value - margin.positive
    val upper = value + margin.positive
    return this in lower..upper
}
