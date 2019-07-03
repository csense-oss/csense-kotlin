@file:Suppress("NOTHING_TO_INLINE", "unused")

package csense.kotlin.extensions.primitives

inline infix fun Short.shl(shift: Int): Short = (this.toInt() shl shift).toShort()

inline infix fun Short.shr(shift: Int): Short = (this.toInt() shr shift).toShort()


//region Zero, negative, positive

inline val Short.zero: Short
    get() = 0
/**
 * Gets this Short negative, if it is already negative, returns that.
 * this is also negative Abs.
 */
inline val Short.negative: Short
    get() = if (this.isPositiveOrZero) {
        (-this).toShort()
    } else {
        this
    }


/**
 * this Short positive, if it is already postive, returns that.
 * also known as abs
 */
inline val Short.positive: Short
    get() = if (this.isNegative) {
        (this * -1).toShort()
    } else {
        this
    }

/**
 *  if this Short is not 0 => returns true. false otherwise
 */
inline val Short.isNotZero: Boolean
    get() = !isZero

/**
 *  if this Short is 0 => returns true. false otherwise
 */
inline val Short.isZero: Boolean
    get() = this == zero

/**
 * Tells if this number is either negative or zero
 */
inline val Short.isNegativeOrZero: Boolean
    get() = this.isNegative || this.isZero

/**
 * Tells if this number is either positive or zero
 */
inline val Short.isPositiveOrZero: Boolean
    get() = this.isPositive || this.isZero


/**
 * If this value is less than 0 then its negative
 */
inline val Short.isNegative: Boolean
    get() = this < 0

/**
 * A value is positive iff its greater than neutral (0)
 */
inline val Short.isPositive: Boolean
    get() = this > 0


/**
 * if this whole number is even (2,4,6....)
 */
inline val Short.isEven: Boolean
    get() = this % 2 == 0
/**
 * If this whole number is odd (1,3,5 ...)
 */
inline val Short.isOdd: Boolean
    get() = !isEven
//endregion
