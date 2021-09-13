package csense.kotlin.extensions.primitives

import csense.kotlin.tests.assertions.*
import kotlin.test.*

class ShortTest {

    @Test
    fun shortIsOdd() {
        0.toShort().isOdd.assertFalse()
        (-1).toShort().isOdd.assertTrue()
        1.toShort().isOdd.assertTrue()
        80.toShort().isOdd.assertFalse()
        (-82).toShort().isOdd.assertFalse()
    }

    @Test
    fun shortIsEven() {
        0.toShort().isEven.assertTrue()
        (-1).toShort().isEven.assertFalse()
        1.toShort().isEven.assertFalse()
        80.toShort().isEven.assertTrue()
        (-82).toShort().isEven.assertTrue()
    }

    @Test
    fun shortIsPositive() {
        0.toShort().isPositive.assertFalse()
        (-1).toShort().isPositive.assertFalse()
        1.toShort().isPositive.assertTrue()
        80.toShort().isPositive.assertTrue()
        (-82).toShort().isPositive.assertFalse()
    }

    @Test
    fun shortIsNegative() {
        0.toShort().isNegative.assertFalse()
        (-1).toShort().isNegative.assertTrue()
        1.toShort().isNegative.assertFalse()
        80.toShort().isNegative.assertFalse()
        (-82).toShort().isNegative.assertTrue()
    }

    @Test
    fun shortIsPositiveOrZero() {
        0.toShort().isPositiveOrZero.assertTrue()
        (-1).toShort().isPositiveOrZero.assertFalse()
        1.toShort().isPositiveOrZero.assertTrue()
        80.toShort().isPositiveOrZero.assertTrue()
        (-82).toShort().isPositiveOrZero.assertFalse()
    }

    @Test
    fun shortIsNegativeOrZero() {
        0.toShort().isNegativeOrZero.assertTrue()
        (-1).toShort().isNegativeOrZero.assertTrue()
        1.toShort().isNegativeOrZero.assertFalse()
        80.toShort().isNegativeOrZero.assertFalse()
        (-82).toShort().isNegativeOrZero.assertTrue()
    }

    @Test
    fun shortIsZero() {
        0.toShort().isZero.assertTrue()
        (-1).toShort().isZero.assertFalse()
        1.toShort().isZero.assertFalse()
        80.toShort().isZero.assertFalse()
        (-82).toShort().isZero.assertFalse()
    }

    @Test
    fun shortIsNotZero() {
        0.toShort().isNotZero.assertFalse()
        (-1).toShort().isNotZero.assertTrue()
        1.toShort().isNotZero.assertTrue()
        80.toShort().isNotZero.assertTrue()
        (-82).toShort().isNotZero.assertTrue()
    }

    @Test
    fun shortPositive() {
        0.toShort().positive.assert(0)
        (-1).toShort().positive.assert(1)
        1.toShort().positive.assert(1)
        80.toShort().positive.assert(80)
        (-82).toShort().positive.assert(82)
    }

    @Test
    fun shortNegative() {
        0.toShort().negative.assert(-0)
        (-1).toShort().negative.assert(-1)
        1.toShort().negative.assert(-1)
        80.toShort().negative.assert(-80)
        (-82).toShort().negative.assert(-82)
    }


    @Test
    fun shortShr() {
        0.toShort().shr(0).assert(0)
        1.toShort().shr(0).assert(1)
        50.toShort().shr(0).assert(50)

        1.toShort().shr(1).assert(0)
        (0b0111).toShort().shr(1).assert(0b0011)
        (0b0111).toShort().shr(2).assert(0b0001)
        (0b0111).toShort().shr(3).assert(0b0000)
        (0b0111).toShort().shr(4).assert(0b0000)
    }


    @Test
    fun shortShl() {
        0.toShort().shl(0).assert(0)
        1.toShort().shl(0).assert(1)
        50.toShort().shl(0).assert(50)

        1.toShort().shl(1).assert(2)
        (0b0111).toShort().shl(1).assert(0b1110)
        (0b0001).toShort().shl(2).assert(0b0100)
        (0b0001).toShort().shl(3).assert(0b1000)
        (0b0001).toShort().shl(4).assert(0b10000)

    }

    @Test
    fun shortCompanionZero() {
        Short.zero.assert(0)
    }
}