@file:Suppress("NOTHING_TO_INLINE", "unused")

package csense.kotlin.extensions.primitives

import csense.kotlin.annotations.numbers.*

/**
 * Shifts this [Short] left the given number of times
 * @receiver [Short]
 * @param shift [Int]
 * @return [Short]
 */
public inline infix fun Short.shl(@IntLimit(from = 1) shift: Int): Short =
        (this.toInt() shl shift).toShort()

/**
 * Shifts this [Short] right the given times
 * @receiver [Short]
 * @param shift [Int]
 * @return [Short]
 */
public inline infix fun Short.shr(@IntLimit(from = 1) shift: Int): Short =
        (this.toInt() shr shift).toShort()


//region Zero, negative, positive
@ShortLimit(from = 0, to = 0)
public inline val Short.Companion.zero: Short
    get() = 0

/**
 * Gets this [Short] negative, if it is already negative, returns that.
 *
 * this is also negative Abs.
 */
public inline val Short.negative: Short
    @ShortLimit(to = 0)
    get() = if (this.isPositiveOrZero) {
        (-this).toShort()
    } else {
        this
    }


/**
 * this [Short] positive, if it is already positive, returns that.
 *
 * also known as abs
 */
public inline val Short.positive: Short
    @ShortLimit(from = 0)
    get() = if (this.isNegative) {
        (this * -1).toShort()
    } else {
        this
    }

/**
 *  if this [Short] is not 0 => returns true. false otherwise
 */
public inline val Short.isNotZero: Boolean
    get() = !isZero

/**
 *  if this [Short] is 0 => returns true. false otherwise
 */
public inline val Short.isZero: Boolean
    get() = this == Short.zero

/**
 * Tells if this [Short] is either negative or zero
 */
public inline val Short.isNegativeOrZero: Boolean
    get() = this.isNegative || this.isZero

/**
 * Tells if this [Short] is either positive or zero
 */
public inline val Short.isPositiveOrZero: Boolean
    get() = this.isPositive || this.isZero


/**
 * If this [Short] is less than 0 then its negative
 */
public inline val Short.isNegative: Boolean
    get() = this < 0

/**
 * A value is positive iff its greater than neutral (0)
 * returns true if this [Short] is positive
 */
public inline val Short.isPositive: Boolean
    get() = this > 0


/**
 * if this [Short] is even (2,4,6....)
 */
public inline val Short.isEven: Boolean
    get() = this % 2 == 0

/**
 * If this [Short] is odd (1,3,5 ...)
 */
public inline val Short.isOdd: Boolean
    get() = !isEven
//endregion
