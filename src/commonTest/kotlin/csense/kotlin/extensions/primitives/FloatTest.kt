package csense.kotlin.extensions.primitives

import csense.kotlin.tests.assertions.*
import kotlin.test.*

class FloatTest {

    @Test
    fun floatNegative() {
        2f.negative.assert(-2f)
        (-2f).negative.assert(-2f)
        0f.negative.assert(0f)
    }

    @Test
    fun floatPositive() {
        2f.positive.assert(2f)
        (-2f).positive.assert(2f)
        0f.positive.assert(0f)
    }


    @Test
    fun floatIsZero() {
        2f.isZero.assert(false)
        0f.isZero.assert(true)
        0.1f.equalsWithin(0.0f, 0.2f).assert(true)
    }

    @Test
    fun floatIsNegative() {
        1f.isNegative.assertFalse()
        800.9f.isNegative.assertFalse()
        (-50f).isNegative.assertTrue()
        0.isNegative.assertFalse()
    }

    @Test
    fun floatIsPositive() {
        1f.isPositive.assertTrue()
        800.9f.isPositive.assertTrue()
        (-50f).isPositive.assertFalse()
        0.isPositive.assertFalse()
    }

    @Test
    fun floatIsNotZero() {
        1f.isNotZero.assertTrue()
        800.9f.isNotZero.assertTrue()
        (-50f).isNotZero.assertTrue()
        0.isNotZero.assertFalse()
        (-0).isNotZero.assertFalse()
    }

    @Test
    fun floatIsNegativeOrZero() {
        (-1).toFloat().isNegativeOrZero.assertTrue()
        0.toFloat().isNegativeOrZero.assertTrue()
        1.toFloat().isNegativeOrZero.assertFalse()
        (-50).toFloat().isNegativeOrZero.assertTrue()
        42.toFloat().isNegativeOrZero.assertFalse()
    }

    @Test
    fun floatIsPositiveOrZero() {
        (-1).toFloat().isPositiveOrZero.assertFalse()
        0.toFloat().isPositiveOrZero.assertTrue()
        1.toFloat().isPositiveOrZero.assertTrue()
        (-50).toFloat().isPositiveOrZero.assertFalse()
        42.toFloat().isPositiveOrZero.assertTrue()
    }

    @Test
    fun floatEqualsWithin() {
        2.0f.equalsWithin(2.0f, 1.0f).assertTrue()
        2.0f.equalsWithin(2.9f, 1.0f).assertTrue()
        2.0f.equalsWithin(4.0f, 1.0f).assertFalse()

    }

    @Test
    fun floatEquals() {
        2.0f.equals(2.0f, 1.0f).assertTrue()
        2.0f.equals(2.9f, 1.0f).assertTrue()
        2.0f.equals(4.0f, 1.0f).assertFalse()
    }

    @Test
    fun floatWithoutDecimalPart() {
        (-1).toFloat().withoutDecimalPart().assert(value = -1.0f, delta = 0.001f)
        0.toFloat().withoutDecimalPart().assert(value = 0.0f, delta = 0.001f)
        1.toFloat().withoutDecimalPart().assert(value = 1.0f, delta = 0.001f)
        20.5f.withoutDecimalPart().assert(value = 20.0f, delta = 0.001f)
        20.9f.withoutDecimalPart().assert(value = 20.0f, delta = 0.001f)
        20.0001f.withoutDecimalPart().assert(value = 20.0f, delta = 0.0001f, message = "should be more precise than not doing it")
    }

    @Test
    fun floatWithDecimalPart() {
        (-1).toFloat().withDecimalPart(0.0f).assert(value = -1.0f, delta = 0.0001f)
        0.toFloat().withDecimalPart(0.0f).assert(value = 0.0f, delta = 0.0001f)
        1.toFloat().withDecimalPart(0.0f).assert(value = 1.0f, delta = 0.0001f)
        1.5f.withDecimalPart(0.0f).assert(value = 1.0f, delta = 0.0001f)
        1.6f.withDecimalPart(0.1f).assert(value = 1.1f, delta = 0.0001f)
        90.0579f.withDecimalPart(0.01234f).assert(value = 90.01234f, delta = 0.00001f)
    }

    @Test
    fun floatDecimalPart() {
        (-1).toFloat().decimalPart().assert(value = 0.0f, delta = 0.0001f)
        0.toFloat().decimalPart().assert(value = 0.0f, delta = 0.0001f)
        1.toFloat().decimalPart().assert(value = 0.0f, delta = 0.0001f)
        100.5f.decimalPart().assert(value = 0.5f, delta = 0.0001f)
        100.99f.decimalPart().assert(value = 0.99f, delta = 0.0001f)
        100.0005f.decimalPart().assert(value = 0.0005f, delta = 0.0001f)
    }
}