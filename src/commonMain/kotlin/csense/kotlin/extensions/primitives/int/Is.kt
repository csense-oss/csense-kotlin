@file:Suppress("NOTHING_TO_INLINE")

package csense.kotlin.extensions.primitives.int

import csense.kotlin.general.*
import kotlin.contracts.*


/**
 *  if this [Int] is not 0 => returns true. false otherwise
 */
public inline val Int.isNotZero: Boolean
    get() = !isZero

/**
 *  if this [Int] is 0 => returns true. false otherwise
 */
public inline val Int.isZero: Boolean
    get() = this == 0

/**
 * Tells if this [Int] is either negative or zero
 */
public inline val Int.isNegativeOrZero: Boolean
    get() = this.isNegative || this.isZero

/**
 * Tells if this [Int] is either positive or zero
 */
public inline val Int.isPositiveOrZero: Boolean
    get() = this.isPositive || this.isZero


/**
 * If this [Int] is less than 0 then its negative
 */
public inline val Int.isNegative: Boolean
    get() = this < 0

/**
 * A value is positive iff it's greater than neutral (0)
 * returns true if this [Int] is positive
 */
public inline val Int.isPositive: Boolean
    get() = this > 0


/**
 * returns true if this [Int] is even (2,4,6....)
 */
public inline val Int.isEven: Boolean
    get() = this % 2 == 0

/**
 * Returns true if this [Int] is odd (1,3,5 ...)
 */
public inline val Int.isOdd: Boolean
    get() = !isEven



/**
 * Tells if this [Int] is a number and different from zero
 * @receiver [Int]? the optional number to test
 * @return [Boolean] false if this is null or zero, true otherwise
 */

public inline fun Int?.isNotNullOrZero(): Boolean {
    contract {
        returns(true) implies (this@isNotNullOrZero != null)
    }
    return this != null && this.isNotZero
}



@Suppress("UnusedReceiverParameter")
@Deprecated(
    "Receiver known at compile time to not be null, thus isNotNull will always be true. Use isNotZero instead",
    level = DeprecationLevel.ERROR
)
public inline fun Int.isNotNullOrZero(): Nothing = unexpected()


/**
 * Tells if this [Int] is either null or zero
 * @receiver [Int]? the optional number to test
 * @return [Boolean] true if this is null or zero, false otherwise
 */

public inline fun Int?.isNullOrZero(): Boolean {
    contract {
        returns(false) implies (this@isNullOrZero != null)
    }
    return this == null || this.isZero
}


@Suppress("UnusedReceiverParameter")
@Deprecated(
    "Receiver known at compile time to not be null, thus isNull will always be false. Use isZero instead",
    level = DeprecationLevel.ERROR
)
public inline fun Int.isNullOrZero(): Nothing = unexpected()