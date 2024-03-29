package csense.kotlin.extensions.ranges

import csense.kotlin.tests.assertions.*
import kotlin.test.*

class IntRangeTest {


    @Suppress("EmptyRange")
    @Test
    fun intRangeLength() {
        val range = 0 until 20
        range.length.assert(20)

        val weirdRange = 20 until 21
        weirdRange.length.assert(1)

        val wrongRange = 20 until 20
        wrongRange.length.assert(0)

    }

    @Suppress("EmptyRange")
    @Test
    fun intRangeLargest() {
        val range = 0 until 20
        range.largest.assert(19, "largest element is 20 - 1 = 19(due to until )")

        val weirdRange = 20 until 21
        weirdRange.largest.assert(20)

        val wrongRange = 20 until 20
        wrongRange.largest.assert(20)

    }


}