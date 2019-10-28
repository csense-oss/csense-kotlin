package csense.kotlin.extensions.primitives

import csense.kotlin.test.assertions.*
import kotlin.test.*

class ByteTest {

    @Test
    fun isNotZero() {
        val zero: Byte = Byte.zero
        val positive: Byte = 1
        val negative: Byte = -1
        zero.isNotZero.assertFalse()
        positive.isNotZero.assertTrue()
        negative.isNotZero.assertTrue()
    }

    @Test
    fun isZero() {
        val zero: Byte = Byte.zero
        val positive: Byte = 1
        val negative: Byte = -1
        zero.isZero.assertTrue()
        positive.isZero.assertFalse()
        negative.isZero.assertFalse()
    }

    @Test
    fun isNegativeOrZero() {
        val zero: Byte = Byte.zero
        val positive: Byte = 1
        val negative: Byte = -1
        zero.isNegativeOrZero.assertTrue()
        positive.isNegativeOrZero.assertFalse()
        negative.isNegativeOrZero.assertTrue()
    }

    @Test
    fun isPositiveOrZero() {
        val zero: Byte = Byte.zero
        val positive: Byte = 1
        val negative: Byte = -1
        zero.isPositiveOrZero.assertTrue()
        positive.isPositiveOrZero.assertTrue()
        negative.isPositiveOrZero.assertFalse()
    }

    @Test
    fun isNegative() {
        val zero: Byte = Byte.zero
        val positive: Byte = 1
        val negative: Byte = -1
        zero.isNegative.assertFalse()
        positive.isNegative.assertFalse()
        negative.isNegative.assertTrue()
    }

    @Test
    fun isPositive() {
        val zero: Byte = Byte.zero
        val positive: Byte = 1
        val negative: Byte = -1
        zero.isPositive.assertFalse()
        positive.isPositive.assertTrue()
        negative.isPositive.assertFalse()
    }

    @Test
    fun isEven() {
        0.toByte().isEven.assertTrue()
        1.toByte().isEven.assertFalse()
        2.toByte().isEven.assertTrue()
        23.toByte().isEven.assertFalse()
        26.toByte().isEven.assertTrue()
    }

    @Test
    fun isOdd() {
        0.toByte().isOdd.assertFalse()
        1.toByte().isOdd.assertTrue()
        2.toByte().isOdd.assertFalse()
        27.toByte().isOdd.assertTrue()
        16.toByte().isOdd.assertFalse()
    }

    @Test
    fun shl() {
        0.toByte().shl(2).assert(0)
        1.toByte().shl(1).assert(2 * 1)
        2.toByte().shl(1).assert(2 * 2)
        8.toByte().shl(2).assert(8 * 2 * 2)
    }

    @Test
    fun shr() {
        0.toByte().shr(2).assert(0)
        1.toByte().shr(1).assert(0)
        2.toByte().shr(1).assert(1)
        8.toByte().shr(2).assert((8 / 2) / 2)
    }

    @Test
    fun toChars() {
        0.toByte().toChars { upperChar, lowerChar ->
            upperChar.toUpperCase().assert('0')
            lowerChar.toUpperCase().assert('0')
            42
        }.assert(42)

        0xFE.toByte().toChars { upperChar, lowerChar ->
            upperChar.toUpperCase().assert('F')
            lowerChar.toUpperCase().assert('E')
        }
    }

    @Test
    fun toHexString() {
        0x00.toByte().toHexString().assert("00")
        0x04.toByte().toHexString().assert("04")
        0x0E.toByte().toHexString().assert("0E")
        0x1E.toByte().toHexString().assert("1E")
        0xFE.toByte().toHexString().assert("FE")
    }

    @Test
    fun splitIntoComponents() {
        0x00.toByte().splitIntoComponents { upper, lower ->
            upper.assert(0x0)
            lower.assert(0x0)
        }
        0x08.toByte().splitIntoComponents { upper, lower ->
            upper.assert(0x0)
            lower.assert(0x8)
        }
        0x11.toByte().splitIntoComponents { upper, lower ->
            upper.assert(0x1)
            lower.assert(0x1)
        }
        0xFF.toByte().splitIntoComponents { upper, lower ->
            upper.assert(0xF)
            lower.assert(0xF)
        }
        0x80.toByte().splitIntoComponents { upper, lower ->
            upper.assert(0x8)
            lower.assert(0x0)
        }
    }

    @Test
    fun byteCompanionZero() {
        Byte.zero.assert(0)
    }

    @Test
    fun byteNegative() {
        0.toByte().negative.assert(-0)
        (-1).toByte().negative.assert(-1)
        1.toByte().negative.assert(-1)
        80.toByte().negative.assert(-80)
        (-82).toByte().negative.assert(-82)
    }

    @Test
    fun byteIsOdd() {
        0.toByte().isOdd.assertFalse()
        (-1).toByte().isOdd.assertTrue()
        1.toByte().isOdd.assertTrue()
        80.toByte().isOdd.assertFalse()
        (-82).toByte().isOdd.assertFalse()
    }

    @Test
    fun byteIsEven() {
        0.toByte().isEven.assertTrue()
        (-1).toByte().isEven.assertFalse()
        1.toByte().isEven.assertFalse()
        80.toByte().isEven.assertTrue()
        (-82).toByte().isEven.assertTrue()
    }

    @Test
    fun byteIsPositive() {
        0.toByte().isPositive.assertFalse()
        (-1).toByte().isPositive.assertFalse()
        1.toByte().isPositive.assertTrue()
        80.toByte().isPositive.assertTrue()
        (-82).toByte().isPositive.assertFalse()
    }

    @Test
    fun byteIsNegative() {
        0.toByte().isNegative.assertFalse()
        (-1).toByte().isNegative.assertTrue()
        1.toByte().isNegative.assertFalse()
        80.toByte().isNegative.assertFalse()
        (-82).toByte().isNegative.assertTrue()
    }

    @Test
    fun byteIsPositiveOrZero() {
        0.toByte().isPositiveOrZero.assertTrue()
        (-1).toByte().isPositiveOrZero.assertFalse()
        1.toByte().isPositiveOrZero.assertTrue()
        80.toByte().isPositiveOrZero.assertTrue()
        (-82).toByte().isPositiveOrZero.assertFalse()
    }

    @Test
    fun byteIsNegativeOrZero() {
        0.toByte().isNegativeOrZero.assertTrue()
        (-1).toByte().isNegativeOrZero.assertTrue()
        1.toByte().isNegativeOrZero.assertFalse()
        80.toByte().isNegativeOrZero.assertFalse()
        (-82).toByte().isNegativeOrZero.assertTrue()
    }

    @Test
    fun byteIsZero() {
        0.toByte().isZero.assertTrue()
        (-1).toByte().isZero.assertFalse()
        1.toByte().isZero.assertFalse()
        80.toByte().isZero.assertFalse()
        (-82).toByte().isZero.assertFalse()
    }

    @Test
    fun byteIsNotZero() {
        0.toByte().isNotZero.assertFalse()
        (-1).toByte().isNotZero.assertTrue()
        1.toByte().isNotZero.assertTrue()
        80.toByte().isNotZero.assertTrue()
        (-82).toByte().isNotZero.assertTrue()
    }

    @Test
    fun bytePositive() {
        0.toByte().positive.assert(0)
        (-1).toByte().positive.assert(1)
        1.toByte().positive.assert(1)
        80.toByte().positive.assert(80)
        (-82).toByte().positive.assert(82)
    }
}