package csense.kotlin.extensions.primitives

import csense.kotlin.test.assertions.*
import kotlin.test.*

class DoubleTest {

    @Test
    fun negative() {
        val positive = 2.0
        positive.negative.assert(-2.0)
        val negative: Double = -2.0
        negative.negative.assert(-2.0)
    }

    @Test
    fun positive() {
        val positive = 8.0
        positive.positive.assert(8.0)
        val negative: Double = -12.0
        negative.positive.assert(12.0)
    }

    @Test
    fun isNegative() {
        (-2.0).isNegative.assertTrue()
        (-0.0).isNegative.assertTrue()
        Double.MIN_VALUE.isNegative.assertTrue()

        (1.0).isNegative.assertFalse()
        (600.0).isNegative.assertFalse()
        Double.MAX_VALUE.isNegative.assertFalse()
    }

    @Test
    fun isPositive() {

    }

    @Test
    fun isZero() {

    }

    @Test
    fun equalWithin() {

    }


}
