@file:Suppress("unused", "NOTHING_TO_INLINE")

package csense.kotlin.extensions.collections.array

import csense.kotlin.*
import csense.kotlin.annotations.numbers.*
import csense.kotlin.extensions.*
import csense.kotlin.extensions.collections.array.generic.*
import csense.kotlin.extensions.collections.generic.*
import csense.kotlin.extensions.primitives.*
import csense.kotlin.extensions.primitives.number.*

/**
 *
 * @receiver [ByteArray]
 * @param appendHexPrefix [Boolean]
 * @param shouldBeUppercase [Boolean]
 * @return [String]
 */

public inline fun ByteArray.toHexString(
    appendHexPrefix: Boolean = false,
    shouldBeUppercase: Boolean = true
): String {
    if (isEmpty()) {
        return ""
    }

    val prefixSize = appendHexPrefix.map(2, 0)

    val hexChars = CharArray(size * 2 + prefixSize)
    if (appendHexPrefix) {
        hexChars[0] = '0'
        hexChars[1] = 'x'
    }

    forEachIndexed { index: @IntLimit(from = 0) Int, it: Byte ->
        val pair = it.bitOperations.splitIntoNibbles()
        val currentIndex = (index * 2) + prefixSize
        val upper = ByteExtensions.hexCharsAsString[pair.upperNibble.toInt()]
        val lower = ByteExtensions.hexCharsAsString[pair.lowerNibble.toInt()]
        hexChars[currentIndex] = upper.toCase(shouldBeUppercase)
        hexChars[currentIndex + 1] = lower.toCase(shouldBeUppercase)
    }
    return hexChars.concatToString()
}

/**
 * A foreach, but not taking any result for the given receiver
 * @receiver [ByteArray]
 * @param receiver [Function1]<[Byte], U>
 */
public inline fun <U> ByteArray.forEachDiscard(receiver: Function1<Byte, U>): Unit =
    GenericArray.foreachDiscardResult(count(), this::get, receiver)

//region Generic collection extensions
/**
 * Performs backwards traversal on this [ByteArray].
 */
public inline fun ByteArray.forEachBackwards(action: FunctionUnit<Byte>): Unit =
    GenericCollectionExtensions.forEachBackwards(count(), this::elementAt, action)
//endregion
