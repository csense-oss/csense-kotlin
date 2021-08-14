@file:Suppress("unused", "NOTHING_TO_INLINE")

package csense.kotlin.extensions.collections.array

import csense.kotlin.*
import csense.kotlin.annotations.numbers.*
import csense.kotlin.extensions.*
import csense.kotlin.extensions.collections.array.generic.*
import csense.kotlin.extensions.collections.generic.*
import csense.kotlin.extensions.primitives.*

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
        it.splitIntoComponents { upperByte: Byte, lowerByte: Byte ->
            val currentIndex = (index * 2) + prefixSize
            val upper = ByteExtensions.hexCharsAsString[upperByte.toInt()]
            val lower = ByteExtensions.hexCharsAsString[lowerByte.toInt()]
            hexChars[currentIndex] = upper.toCase(shouldBeUppercase)
            hexChars[currentIndex + 1] = lower.toCase(shouldBeUppercase)
        }
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
 * Performs traversal in pairs of 2  (with the first index as well)
 */
@Deprecated("will be removed")
@Suppress("MissingTestFunction")
public inline fun ByteArray.forEach2Indexed(action: Function2IndexedUnit<Byte, Byte>): Unit =
    GenericCollectionExtensions.forEach2Indexed(count(), ::elementAt, action)

/**
 * Performs traversal in pairs of 2
 */
@Deprecated("will be removed")
@Suppress("MissingTestFunction")
public inline fun ByteArray.forEach2(action: Function2Unit<Byte, Byte>): Unit =
    GenericCollectionExtensions.forEach2(count(), ::elementAt, action)

/**
 * Performs backwards traversal on this [ByteArray].
 */
public inline fun ByteArray.forEachBackwards(action: FunctionUnit<Byte>): Unit =
    GenericCollectionExtensions.forEachBackwards(count(), this::elementAt, action)
//endregion
