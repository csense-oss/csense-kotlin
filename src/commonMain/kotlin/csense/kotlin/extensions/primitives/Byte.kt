@file:Suppress("unused", "NOTHING_TO_INLINE")
@file:OptIn(ExperimentalContracts::class)

package csense.kotlin.extensions.primitives

import csense.kotlin.annotations.numbers.*
import csense.kotlin.extensions.primitives.number.*
import kotlin.contracts.*
import kotlin.jvm.*


//region Zero, negative, positive

public inline val Byte.Companion.zero: Byte
    get() = 0

/**
 * This [Byte] as negative, if it is already negative, returns that.
 * this is also negative Abs.
 */
public inline val Byte.negative: Byte
    @ByteLimit(to = 0)
    get() = if (this.isPositiveOrZero) {
        (-this).toByte()
    } else {
        this
    }


/**
 * this [Byte] as positive, if it is already positive, returns that.
 * also known as abs
 */
public inline val Byte.positive: Byte
    @ByteLimit(from = 0)
    get() = if (this.isNegative) {
        (this * -1).toByte()
    } else {
        this
    }

/**
 *  if this [Byte] is not 0 => returns true. false otherwise
 */
public inline val Byte.isNotZero: Boolean
    get() = !isZero

/**
 *  if this [Byte] is 0 => returns true. false otherwise
 */
public inline val Byte.isZero: Boolean
    get() = this == Byte.zero

/**
 * Tells if this [Byte] is either negative or zero
 */
public inline val Byte.isNegativeOrZero: Boolean
    get() = this.isNegative || this.isZero

/**
 * Tells if this [Byte] is either positive or zero
 */
public inline val Byte.isPositiveOrZero: Boolean
    get() = this.isPositive || this.isZero


/**
 * If this [Byte] is less than 0 then its negative
 */
public inline val Byte.isNegative: Boolean
    get() = this < 0

/**
 * If this [Byte] is positive, meaning iff its greater than neutral (0)
 */
public inline val Byte.isPositive: Boolean
    get() = this > 0


/**
 * if this [Byte] is even (2,4,6....)
 */
public inline val Byte.isEven: Boolean
    get() = this % 2 == 0

/**
 * If this [Byte] is odd (1,3,5 ...)
 */
public inline val Byte.isOdd: Boolean
    get() = !isEven
//endregion

//region hex converting
/**
 * Converts a given byte to a pair of chars, and then returns the resulting
 * @receiver [Byte]
 * @param action (upperChar: [Char], lowerChar: [Char]) -> T
 * @return T
 */
public fun <T> Byte.toChars(
    action: (upperChar: Char, lowerChar: Char) -> T
): T {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    val (upper: Byte, lower: Byte) = bitOperations.splitIntoNibbles()
    return action(
        ByteExtensions.hexCharsAsString[upper.toInt()],
        ByteExtensions.hexCharsAsString[lower.toInt()]
    )
}

/**
 * converts a given byte to a hex string.
 * @receiver [Byte]
 * @return [String]
 */

public inline fun Byte.toHexString(): String = this.toChars { upperChar, lowerChar ->
    charArrayOf(upperChar, lowerChar).concatToString()
}

//endregion

@JvmInline
public value class ByteExtensions(public val byte: Byte) {
    public companion object {
        public const val hexCharsAsString: String = "0123456789ABCDEF"
    }
}

/**
 * Converts this [Byte]'s raw bits into an [Int]
 * by pruning the top bits; Eg (-1) byte becomes 255 in the int.
 * Normally [Byte.toInt] would preserve the -1 (which is a different bit representation)
 * @return [Int]
 */
public fun Byte.toIntBitWise(): Int {
    val unsafeInt = toInt()
    //prune all except the lower 8 bits
    return unsafeInt.and(0xFF)
}
