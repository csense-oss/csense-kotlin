@file:Suppress("unused", "NOTHING_TO_INLINE")

package csense.kotlin.extensions.primitives.long

import csense.kotlin.annotations.numbers.*
import csense.kotlin.general.*
import kotlin.contracts.*
import kotlin.math.*


/**
 * Gets this [Long] negative, if it is already negative, returns that.
 *
 * this is also negative [kotlin.math.abs].
 */
public inline val Long.negative: Long
    @LongLimit(to = 0)
    get() = minOf(this, -this)


/**
 * this [Long] positive, if it is already positive, returns that.
 *
 * also known as [kotlin.math.abs].
 */
public inline val Long.positive: Long
    @LongLimit(from = 0)
    get() = absoluteValue

/**
 *  if this int is not 0 => returns true. false otherwise
 */
public inline val Long.isNotZero: Boolean
    get() = !isZero

/**
 *  if this [Long] is 0 => returns true. false otherwise
 */
public inline val Long.isZero: Boolean
    get() = this == 0L

/**
 * Tells if this [Long] is either negative or zero
 */
public inline val Long.isNegativeOrZero: Boolean
    get() = this.isNegative || this.isZero

/**
 * Tells if this [Long] is either positive or zero
 */
public inline val Long.isPositiveOrZero: Boolean
    get() = this.isPositive || this.isZero


/**
 * If this [Long] is less than 0 then its negative
 */
public inline val Long.isNegative: Boolean
    get() = this < 0

/**
 * A value is positive iff it's greater than neutral (0)
 * returns true if this [Long] is positive
 */
public inline val Long.isPositive: Boolean
    get() = this > 0


/**
 * if this [Long] is even (2,4,6....)
 */
public inline val Long.isEven: Boolean
    get() = this % 2L == 0L

/**
 * If this [Long] is odd (1,3,5 ...)
 */
public inline val Long.isOdd: Boolean
    get() = !isEven


/**
 * Tells if this [Long] is a number and different from zero
 * @receiver [Long]? the optional number to test
 * @return [Boolean] false if this is null or zero, true otherwise
 */

public inline fun Long?.isNotNullOrZero(): Boolean {
    contract {
        returns(true) implies (this@isNotNullOrZero != null)
    }
    return this != null && this.isNotZero
}

@Deprecated(
    "Receiver known at compile time to not be null, thus isNotNull will always be true. Use isNotZero instead",
    level = DeprecationLevel.ERROR
)
public inline fun Long.isNotNullOrZero(): Nothing = unexpected()

/**
 * Tells if this [Long] is either null or zero
 * @receiver [Long]? the optional number to test
 * @return [Boolean] true if this is null or zero, false otherwise
 */

public inline fun Long?.isNullOrZero(): Boolean {
    contract {
        returns(false) implies (this@isNullOrZero != null)
    }
    return this == null || this.isZero
}

@Deprecated(
    "Receiver known at compile time to not be null, thus isNull will always be false. Use isZero instead",
    level = DeprecationLevel.ERROR
)
public inline fun Long.isNullOrZero(): Nothing = unexpected()
