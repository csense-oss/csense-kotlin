@file:Suppress("unused", "NOTHING_TO_INLINE")

package csense.kotlin.extensions.primitives

import csense.kotlin.*
import csense.kotlin.annotations.numbers.*
import csense.kotlin.extensions.*
import kotlin.contracts.*
import kotlin.math.*

/**
 * Gets this [Int] negative, if it is already negative, returns that.
 *
 * this is also negative Abs.
 */
public inline val Int.negative: Int
    @IntLimit(to = 0)
    get() = minOf(this, -this)


/**
 * this [Int] positive, if it is already positive, returns that.
 *
 * also known as abs
 */
public inline val Int.positive: Int
    @IntLimit(from = 0)
    get() = absoluteValue

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
 * A value is positive iff its greater than neutral (0)
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
 * Does the given action this values times
 * if this value is less than or equal to 0, then the action is not ran
 * @receiver [Int]
 * @param action [FunctionUnit]<[Int]> the action to perform each this values (positive) times
 */
@IntLimit(from = 0)
public inline fun Int.forEach(action: FunctionUnit<Int>) {
    if (this.isNegativeOrZero) {
        return
    }
    for (i in 0 until this) {
        action(i)
    }
}


//region indexOf extensions


public inline class IndexOfExtensions(public val value: Int)

public inline val Int.indexOfExtensions: IndexOfExtensions
    get() = IndexOfExtensions(this)

/**
 * Unwraps an unsafe index of int. everything below 0 becomes null otherwise the given number
 * @receiver [Int]
 * @return [Int]? null if negative, the value otherwise
 */
@IntLimit(from = 0)
public inline fun IndexOfExtensions.unwrapUnsafeIndexOf(): Int? = value.isNegative.map(null, value)
//endregion

/**
 * Tells if this [Int] is a number and different from zero
 * @receiver [Int]? the optional number to test
 * @return [Boolean] false if this is null or zero, true otherwise
 */
@OptIn(ExperimentalContracts::class)
public inline fun Int?.isNotNullOrZero(): Boolean {
    contract {
        returns(true) implies (this@isNotNullOrZero != null)
    }
    return this != null && this.isNotZero
}


/**
 * Tells if this [Int] is either null or zero
 * @receiver [Int]? the optional number to test
 * @return [Boolean] true if this is null or zero, false otherwise
 */
@OptIn(ExperimentalContracts::class)
public inline fun Int?.isNullOrZero(): Boolean {
    contract {
        returns(false) implies (this@isNullOrZero != null)
    }
    return this == null || this.isZero
}