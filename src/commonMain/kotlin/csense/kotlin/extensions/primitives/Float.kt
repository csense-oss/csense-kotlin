@file:Suppress("NOTHING_TO_INLINE", "unused")

package csense.kotlin.extensions.primitives

import kotlin.math.absoluteValue


/**
 * Compares this [Float] to the given value,with a margin;
 * this is done since floating numbers are not precise in computers.
 * @receiver [Float]
 * @param value [Float] The value we are comparing to
 * @param margin [Float] the delta / margin we are comparing within
 * the margin will always be converted to positive,
 * since a negative margin would always yield false
 * @return [Boolean]
 */

inline fun Float.equalsWithin(value: Float, margin: Float = 0.1f): Boolean {
    val lower = value - margin.positive
    val upper = value + margin.positive
    return this in lower..upper
}

/**
 *
 * @receiver [Float]
 * @param value [Float]
 * @param margin [Float]
 * @return [Boolean]
 */
inline fun Float.equals(value: Float, margin: Float) = equalsWithin(value, margin)

/**
 * if this [Float] is 0 within a margin of 0.1 akk
 * in range (from inclusive) -0.1 to 0.1 inclusive
 * If you need more margin, see [Float.equalsWithin]
 */
inline val Float.isZero: Boolean
    get() = isZero(margin = 0.1f)

/**
 *
 * @receiver [Float]
 * @param margin [Float]
 * @return [Boolean]
 */
inline fun Float.isZero(margin: Float): Boolean = equalsWithin(0.0f, margin)

/**
 * Gets this [Float] negative, if it is already negative, returns that.
 *
 * could also be called negative abs
 */
inline val Float.negative: Float
    get() = minOf(this, -this)

/**
 * always returns this [Float] as a positive number, so -2.0 will become 2.0
 *
 * also known as abs
 */
inline val Float.positive: Float
    get() = this.absoluteValue

/**
 * Tells if this [Float] is < 0 (not neutral including)
 */
inline val Float.isNegative: Boolean
    get() = this < 0

/**
 * Tells if this [Float] is > 0 (not neutral including)
 */
inline val Float.isPositive: Boolean
    get() = this > 0


/**
 * Tells if this [Float] is either negative or zero
 */
inline val Float.isNegativeOrZero: Boolean
    get() = this.isNegative || this.isZero

/**
 * Tells if this [Float] is either positive or zero
 */
inline val Float.isPositiveOrZero: Boolean
    get() = this.isPositive || this.isZero


/**
 *  if this [Float] is not 0 => returns true. false otherwise
 */
inline val Float.isNotZero: Boolean
    get() = !isZero

/**
 *
 * @receiver [Float]
 * @param margin [Float]
 * @return [Boolean]
 */
inline fun Float.isNotZero(margin: Float): Boolean =
        !isZero(margin)