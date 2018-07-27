package csense.kotlin.extensions.collections.array

import csense.kotlin.extensions.map
import csense.kotlin.extensions.primitives.*


@Suppress("NOTHING_TO_INLINE")
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
 * Fills this array with the given value
 */
fun ByteArray.fill(value: Byte) =
        fillArray(count(), value, this::set)