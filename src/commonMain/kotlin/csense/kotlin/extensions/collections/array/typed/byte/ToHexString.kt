package csense.kotlin.extensions.collections.array.typed.byte

import csense.kotlin.annotations.numbers.*
import csense.kotlin.extensions.mapping.*
import csense.kotlin.extensions.primitives.byte.*
import csense.kotlin.extensions.primitives.char.*
import csense.kotlin.extensions.primitives.operations.*


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

    val prefixSize: Int = appendHexPrefix.map(ifTrue = 2, ifFalse = 0)

    val hexChars = CharArray(size = size * 2 + prefixSize)
    if (appendHexPrefix) {
        hexChars[0] = '0'
        hexChars[1] = 'x'
    }

    forEachIndexed { index: @IntLimit(from = 0) Int, it: Byte ->
        val pair: NibblePair = it.bits.splitIntoNibbles()
        val currentIndex: Int = (index * 2) + prefixSize
        val upper: Char = ByteExtensions.hexCharsAsString[pair.upperNibble.toInt()]
        val lower: Char = ByteExtensions.hexCharsAsString[pair.lowerNibble.toInt()]
        hexChars[currentIndex] = upper.toCase(shouldBeUppercase)
        hexChars[currentIndex + 1] = lower.toCase(shouldBeUppercase)
    }
    return hexChars.concatToString()
}
