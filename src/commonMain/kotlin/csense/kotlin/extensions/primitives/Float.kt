@file:Suppress("NOTHING_TO_INLINE", "unused")

package csense.kotlin.extensions.primitives

import kotlin.math.*


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
public inline fun Float.equalsWithin(value: Float, margin: Float = 0.1f): Boolean {
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
public inline fun Float.equals(value: Float, margin: Float): Boolean =
    equalsWithin(value, margin)

/**
 * if this [Float] is 0 within a margin of 0.1 akk
 * in range (from inclusive) -0.1 to 0.1 inclusive
 * If you need more margin, see [Float.equalsWithin]
 */
public inline val Float.isZero: Boolean
    get() = isZero(margin = 0.1f)


/**
 *  if this [Float] is not within 0.1 of 0 => returns true. false otherwise
 */
public inline val Float.isNotZero: Boolean
    get() = !isZero

/**
 *
 * @receiver [Float]
 * @param margin [Float]
 * @return [Boolean]
 */
public inline fun Float.isZero(margin: Float): Boolean =
    equalsWithin(0.0f, margin)

/**
 * Gets this [Float] negative, if it is already negative, returns that.
 *
 * could also be called negative abs
 */
public inline val Float.negative: Float
    get() = minOf(this, -this)

/**
 * always returns this [Float] as a positive number, so -2.0 will become 2.0
 *
 * also known as abs
 */
public inline val Float.positive: Float
    get() = this.absoluteValue

/**
 * Tells if this [Float] is < 0 (not neutral including)
 */
public inline val Float.isNegative: Boolean
    get() = this < 0

/**
 * Tells if this [Float] is > 0 (not neutral including)
 */
public inline val Float.isPositive: Boolean
    get() = this > 0


/**
 * Tells if this [Float] is either negative or zero
 */
public inline val Float.isNegativeOrZero: Boolean
    get() = this.isNegative || this.isZero

/**
 * Tells if this [Float] is either positive or zero
 */
public inline val Float.isPositiveOrZero: Boolean
    get() = this.isPositive || this.isZero

/**
 *
 * @receiver [Float]
 * @param margin [Float]
 * @return [Boolean]
 */
public inline fun Float.isNotZero(margin: Float): Boolean =
    !isZero(margin)


/**
 * The whole part of this [Float] (2.4 becomes 2.0)
 * Imprecise (thus 2.4 can become 2.000000<something> etc)
 */
public inline fun Float.withoutDecimalPart(): Float {
    return toInt().toFloat()
}

/**
 * The decimal part (2.4 becomes 0.4)
 * Imprecise (thus 2.4 can become 0.39999 etc.)
 */
public inline fun Float.decimalPart(): Float =
    this - withoutDecimalPart()

/**
 * Adds the given decimal part from the given part to this
 * @receiver [Float]
 * @param decimalPart [Float] the decimal part to add (will ignore the whole part)
 * @return [Float] the "whole part" of this Float + the decimal part of [decimalPart]'s Float
 * Imprecise (thus 2.4 with decimal part (0.3) can become 2.299999999)
 */
public inline fun Float.withDecimalPart(decimalPart: Float): Float =
    withoutDecimalPart() + decimalPart.decimalPart()