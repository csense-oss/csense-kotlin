package csense.kotlin.extensions.primitives

import csense.kotlin.extensions.primitives.byte.*
import csense.kotlin.tests.assertions.*
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
    fun toChars() {
        0.toByte().toChars { upperChar, lowerChar ->
            upperChar.assert('0', ignoreCase = true)
            lowerChar.assert('0', ignoreCase = true)
            42
        }.assert(42)

        0xFE.toByte().toChars { upperChar, lowerChar ->
            upperChar.assert('F', ignoreCase = true)
            lowerChar.assert('E', ignoreCase = true)
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

    @Test
    fun byteToIntBitWise() {
        0.toByte().toIntBitWise().assert(0)
        (-1).toByte().toIntBitWise().assert(0b11111111)
        1.toByte().toIntBitWise().assert(1)
        80.toByte().toIntBitWise().assert(80)
        (-82).toByte().toIntBitWise().assert(0b10101110) //-82 in binary is 10101110
    }
}