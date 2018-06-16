package csense.kotlin.extensions.primitives

import kotlin.experimental.and

@Suppress("NOTHING_TO_INLINE")
inline infix fun Byte.shl(shift: Int): Byte = (this.toInt() shl shift).toByte()

@Suppress("NOTHING_TO_INLINE")
inline infix fun Byte.shr(shift: Int): Byte = (this.toInt() shr shift).toByte()


inline fun <T> Byte.toChars(crossinline action: (upperChar: Char, lowerChar: Char) -> T): T =
        splitIntoComponents { upperByte, lowerByte ->
            action(hexCharsAsString[upperByte.toInt()], hexCharsAsString[lowerByte.toInt()])
        }

@Suppress("NOTHING_TO_INLINE")
inline fun Byte.toHexString(): String =
        this.toChars { upperChar, lowerChar ->
            String.createFromChars(kotlin.charArrayOf(upperChar, lowerChar))
        }


/**
 * Splits a byte of 0x2f into "0x02" and into "0x0f"
 *
 */
inline fun <T> Byte.splitIntoComponents(crossinline action: (upperByte: Byte, lowerByte: Byte) -> T): T {
    val lower: Byte = this and 0x0f
    val upper: Byte = (this shr 4) and 0x0F.toByte()
    return action(upper, lower)
}


const val hexCharsAsString = "0123456789ABCDEF"