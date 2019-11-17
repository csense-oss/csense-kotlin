@file:Suppress("NOTHING_TO_INLINE", "unused")

package csense.kotlin.extensions.primitives

import kotlin.math.absoluteValue


/**
 * Compares this float to the given value,with a margin;
 * this is done since floating numbers are not precise in computers.
 * @receiver float
 * @param value float The value we are comparing to
 * @param margin float the delta / margin we are comparing within
 * the margin will always be converted to positive,
 * since a negative margin would always yield false
 * @return Boolean
 */

inline fun Float.equalWithin(value: Float, margin: Float = 0.1f): Boolean {
    val lower = value - margin.positive
    val upper = value + margin.positive
    return this in lower..upper
}

/**
 * if this float is 0 within a margin of 0.1 akk
 * in range  (from inclusive) -0.1 to 0.1 inclusive
 * If you need more margin, see Float.equalWithin
 */
inline val Float.isZero: Boolean
    get() = this.equalWithin(0.0f, 0.1f)


/**
 * Gets this number negative, if it is already negative, returns that.
 */
inline val Float.negative: Float
    get() = minOf(this, -this)

/**
 * always returns this value as a positive number, so -2.0 will become 2.0
 */
inline val Float.positive: Float
    get() = this.absoluteValue

/**
 * Tells if this number is < 0 (not neutral including)
 */
inline val Float.isNegative: Boolean
    get() = this < 0

/**
 * Tells if this number is > 0 (not neutral including)
 */
inline val Float.isPositive: Boolean
    get() = this > 0


/**
 * Tells if this number is either negative or zero
 */
inline val Float.isNegativeOrZero: Boolean
    get() = this.isNegative || this.isZero

/**
 * Tells if this number is either positive or zero
 */
inline val Float.isPositiveOrZero: Boolean
    get() = this.isPositive || this.isZero


/**
 *  if this int is not 0 => returns true. false otherwise
 */
inline val Float.isNotZero: Boolean
    get() = !isZero
