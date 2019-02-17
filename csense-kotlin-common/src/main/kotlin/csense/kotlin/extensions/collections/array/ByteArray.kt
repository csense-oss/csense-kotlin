@file:Suppress("unused", "NOTHING_TO_INLINE")

package csense.kotlin.extensions.collections.array

import csense.kotlin.*
import csense.kotlin.extensions.*
import csense.kotlin.extensions.collections.array.generic.*
import csense.kotlin.extensions.collections.generic.*
import csense.kotlin.extensions.primitives.*

/**
 *
 * @receiver ByteArray
 * @param appendHexPrefix Boolean
 * @param shouldBeUppercase Boolean
 * @return String
 */

inline fun ByteArray.toHexString(appendHexPrefix: Boolean = false,
                                 shouldBeUppercase: Boolean = true): String {

    val prefixSize = appendHexPrefix.map(2, 0)

    val hexChars = CharArray(size * 2 + prefixSize)
    if (appendHexPrefix) {
        hexChars[0] = '0'
        hexChars[1] = 'x'
    }

    forEachIndexed { index, it ->
        it.splitIntoComponents { upperByte: Byte, lowerByte: Byte ->
            val currentIndex = (index * 2) + prefixSize
            val upper = hexCharsAsString[upperByte.toInt()]
            val lower = hexCharsAsString[lowerByte.toInt()]
            hexChars[currentIndex] = upper.toCase(shouldBeUppercase)
            hexChars[currentIndex + 1] = lower.toCase(shouldBeUppercase)
        }
    }
    return String.createFromChars(hexChars)
}

/**
 * A foreach, but not taking any result for the given receiver
 * @receiver ByteArray
 * @param receiver (T) -> U
 */
inline fun <U> ByteArray.forEachDiscard(receiver: Function1<Byte, U>) =
        GenericArray.foreachDiscardResult(count(), this::get, receiver)

/**
 * Fills this array with the given value
 * @receiver ByteArray
 * @param value Byte
 */
fun ByteArray.fill(value: Byte) =
        GenericArray.fillArray(count(), value, this::set)


//region Generic collection extensions
/**
 * Performs traversal in pairs of 2  (with the first index as well)
 */
inline fun ByteArray.forEach2Indexed(action: Function2IndexedUnit<Byte, Byte>) =
        GenericCollectionExtensions.forEach2Indexed(count(), ::elementAt, action)

/**
 * Performs traversal in pairs of 2
 */
inline fun ByteArray.forEach2(action: Function2Unit<Byte, Byte>) =
        GenericCollectionExtensions.forEach2(count(), ::elementAt, action)

/**
 * Performs backwards traversal on this list.
 */
inline fun ByteArray.forEachBackwards(action: FunctionUnit<Byte>) =
        GenericCollectionExtensions.forEachBackwards(count(), this::elementAt, action)
//endregion
