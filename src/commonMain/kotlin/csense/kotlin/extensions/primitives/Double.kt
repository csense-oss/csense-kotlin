@file:Suppress("unused", "NOTHING_TO_INLINE")

package csense.kotlin.extensions.primitives

import kotlin.math.*


/**
 * Gets this number negative, if it is already negative, returns that.
 *
 * could also be called negative abs
 */
public inline val Double.negative: Double
    get() = minOf(this, -this)

/**
 * always returns this value as a positive number, so -2.0 will become 2.0
 *
 * also known as abs
 */
public inline val Double.positive: Double
    get() = this.absoluteValue

/**
 * Tells if this [Double] is < 0 (not neutral including)
 */
public inline val Double.isNegative: Boolean
    get() = this < 0

/**
 * Tells if this [Double] is > 0 (not neutral including)
 */
public inline val Double.isPositive: Boolean
    get() = this > 0

/**
 * Tells if this [Double] is either negative or zero
 */
public inline val Double.isNegativeOrZero: Boolean
    get() = this.isNegative || this.isZero

/**
 * Tells if this [Double] is either positive or zero
 */
public inline val Double.isPositiveOrZero: Boolean
    get() = this.isPositive || this.isZero


/**
 * if this double is 0 within a margin of 0.1 akk
 * in range  (from inclusive) -0.1 to 0.1 inclusive
 * If you need more margin, see [Double.equalsWithin]
 */
public inline val Double.isZero: Boolean
    get() = this.equalsWithin(0.0, 0.1)


/**
 *  if this [Double] is not within 0.1 of 0 => returns true. false otherwise
 */
public inline val Double.isNotZero: Boolean
    get() = !isZero

/**
 * Compares this double to the given value,with a margin;
 * this is done since doubles / floating numbers are not precise in computers.
 * @receiver [Double]
 * @param value [Double] The value we are comparing to
 * @param margin [Double] the delta / margin we are comparing within
 * the margin will always be converted to positive,
 * since a negative margin would always yield false
 * @return [Boolean]
 */
public inline fun Double.equalsWithin(value: Double, margin: Double = 0.1): Boolean {
    val lower = value - margin.positive
    val upper = value + margin.positive
    return this in lower..upper
}

/**
 * The whole part of this [Double] (2.4 becomes 2.0)
 * Imprecise (thus 2.4 can become 2.000000<something> etc)
 */
public inline fun Double.withoutDecimalPart(): Double {
    return toInt().toDouble()
}

/**
 * The decimal part (2.4 becomes 0.4)
 * Imprecise (thus 2.4 can become 0.39999 etc)
 */
public inline fun Double.decimalPart(): Double =
    this - withoutDecimalPart()

/**
 * Adds the given decimal part from the given part to this
 * @receiver [Double]
 * @param decimalPart [Double] the decimal part to add (will ignore the whole part)
 * @return [Double] the "whole part" of this Double + the decimal part of [decimalPart]'s double
 * Imprecise (thus 2.4 with decimal part (0.3) can become 2.299999999)
 */
public inline fun Double.withDecimalPart(decimalPart: Double): Double =
    withoutDecimalPart() + decimalPart.decimalPart()