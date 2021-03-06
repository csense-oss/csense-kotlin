package csense.kotlin.extensions.primitives

import csense.kotlin.tests.assertions.assert
import csense.kotlin.tests.assertions.assertFalse
import csense.kotlin.tests.assertions.assertTrue
import kotlin.test.Test

class DoubleTest {

    @Test
    fun doubleNegative() {
        val positive = 2.0
        positive.negative.assert(-2.0)
        val negative: Double = -2.0
        negative.negative.assert(-2.0)
    }

    @Test
    fun doublePositive() {
        val positive = 8.0
        positive.positive.assert(8.0)
        val negative: Double = -12.0
        negative.positive.assert(12.0)
    }

    @Test
    fun doubleIsNegative() {
        (-2.0).isNegative.assertTrue()
        (-1.0).isNegative.assertTrue()
        (-0.1).isNegative.assertTrue()
        (-0.0).isNegative.assertFalse("0 cannot be negative.")
        Double.MIN_VALUE.isNegative.assertFalse("min_value is least positive value, not negative.")


        (1.0).isNegative.assertFalse()
        (600.0).isNegative.assertFalse()
        Double.MAX_VALUE.isNegative.assertFalse()
    }

    @Test
    fun doubleIsPositive() {
        (0.0).isPositive.assertFalse("0 is not counted towards positive, as its the \"neutral element\" in mathmatics")
        (1.0).isPositive.assertTrue()
        (-1.0).isPositive.assertFalse()
        Double.MIN_VALUE.isPositive.assertTrue()
        Double.MAX_VALUE.isPositive.assertTrue()
    }

    @Test
    fun doubleIsZero() {
        (0.0).isZero.assertTrue()
        (-0.0).isZero.assertTrue()

        (1.0).isZero.assertFalse()
        (-1.0).isZero.assertFalse()

        Double.MAX_VALUE.isZero.assertFalse()

        (-2.0).isZero.assertFalse()
    }

    @Test
    fun doubleIsNegativeOrZero() {
        (-1).toDouble().isNegativeOrZero.assertTrue()
        0.toDouble().isNegativeOrZero.assertTrue()
        1.toDouble().isNegativeOrZero.assertFalse()
        (-50).toDouble().isNegativeOrZero.assertTrue()
        42.toDouble().isNegativeOrZero.assertFalse()
    }

    @Test
    fun doubleIsPositiveOrZero() {
        (-1).toDouble().isPositiveOrZero.assertFalse()
        0.toDouble().isPositiveOrZero.assertTrue()
        1.toDouble().isPositiveOrZero.assertTrue()
        (-50).toDouble().isPositiveOrZero.assertFalse()
        42.toDouble().isPositiveOrZero.assertTrue()
    }

    @Test
    fun doubleEqualsWithin() {
        0.0.equalsWithin(1.0, 2.0).assertTrue("is 0 in the range [1 - 2; 1+2] => [-1 to 3] => yes")
        0.0.equalsWithin(1.0, 0.5).assertFalse("is 0 in the range [1 - 0.5; 1+0.5] => [0.5 to 1.5] => no")

        1.0.equalsWithin(1.0).assertTrue("default margin should work for concepttually equal numbers")
        0.0.equalsWithin(0.0).assertTrue("default margin should work for concepttually equal numbers")
        0.1.equalsWithin(0.1).assertTrue("default margin should work for concepttually equal numbers")
        0.0001.equalsWithin(0.0001).assertTrue("default margin should work for concepttually equal numbers")
        0.0000001.equalsWithin(0.0000001).assertTrue("default margin should work for concepttually equal numbers")

        0.001.equalsWithin(0.002, 0.0001).assertFalse("precision mismatch by a factor 10")

        500.05.equalsWithin(500.0).assertTrue("default should be a close equal")
        500.05.equalsWithin(500.0, 0.01).assertFalse("specified should not be close enough")

        Double.MAX_VALUE.equalsWithin(Double.MAX_VALUE).assertTrue()
        Double.MAX_VALUE.equalsWithin(Double.MAX_VALUE, -0.5).assertTrue()
        Double.MIN_VALUE.equalsWithin(Double.MIN_VALUE).assertTrue()

        5.0.equalsWithin(3.0, -2.5).assertTrue("margin should be abs, not negative.")
    }

    @Test
    fun doubleIsNotZero() {
        (-1).toDouble().isNotZero.assertTrue("is far from 0, so its not zero")
        0.toDouble().isNotZero.assertFalse("is zero (or very close to)")
        0.05.isNotZero.assertFalse("is very close to 0")
        (-0.05).isNotZero.assertFalse("is very close to 0")
        0.5.isNotZero.assertTrue("is far off 0")
        1.toDouble().isNotZero.assertTrue()
        (-50).toDouble().isNotZero.assertTrue()
        42.toDouble().isNotZero.assertTrue()
    }

    @Test
    fun doubleWithoutDecimalPart() {
        (-1).toDouble().withoutDecimalPart().assert(value = -1.0, delta = 0.001)
        0.toDouble().withoutDecimalPart().assert(value = 0.0, delta = 0.001)
        1.toDouble().withoutDecimalPart().assert(value = 1.0, delta = 0.001)
        20.5.withoutDecimalPart().assert(value = 20.0, delta = 0.001)
        20.9.withoutDecimalPart().assert(value = 20.0, delta = 0.001)
        20.0001.withoutDecimalPart().assert(value = 20.0, delta = 0.0001, message = "should be more precise than not doing it")
    }

    @Test
    fun doubleWithDecimalPart() {
        (-1).toDouble().withDecimalPart(0.0).assert(value = -1.0, delta = 0.0001)
        0.toDouble().withDecimalPart(0.0).assert(value = 0.0, delta = 0.0001)
        1.toDouble().withDecimalPart(0.0).assert(value = 1.0, delta = 0.0001)
        1.5.withDecimalPart(0.0).assert(value = 1.0, delta = 0.0001)
        1.6.withDecimalPart(0.1).assert(value = 1.1, delta = 0.0001)
        90.0579.withDecimalPart(0.01234).assert(value = 90.01234, delta = 0.00001)
    }

    @Test
    fun doubleDecimalPart() {
        (-1).toDouble().decimalPart().assert(value = 0.0, delta = 0.0001)
        0.toDouble().decimalPart().assert(value = 0.0, delta = 0.0001)
        1.toDouble().decimalPart().assert(value = 0.0, delta = 0.0001)
        100.5.decimalPart().assert(value = 0.5, delta = 0.0001)
        100.99.decimalPart().assert(value = 0.99, delta = 0.0001)
        100.0005.decimalPart().assert(value = 0.0005, delta = 0.0001)
    }
}
