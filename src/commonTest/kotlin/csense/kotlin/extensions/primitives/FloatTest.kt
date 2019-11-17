package csense.kotlin.extensions.primitives

import csense.kotlin.tests.assertions.assert
import csense.kotlin.tests.assertions.assertFalse
import csense.kotlin.tests.assertions.assertTrue
import kotlin.test.Test

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
        0.1f.equalWithin(0.0f, 0.2f).assert(true)
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
}