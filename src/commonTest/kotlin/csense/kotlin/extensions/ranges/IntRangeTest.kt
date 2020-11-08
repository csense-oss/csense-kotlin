package csense.kotlin.extensions.ranges

import csense.kotlin.tests.assertions.assert
import kotlin.test.Test

class IntRangeTest {


    @Test
    fun intRangeLength() {
        val range = 0 until 20
        range.length.assert(20)

        val weirdRange = 20 until 21
        weirdRange.length.assert(1)

        val wrongRange = 20 until 20
        wrongRange.length.assert(0)

    }

    @Test
    fun intRangeLargest() {
        val range = 0 until 20
        range.largest.assert(19, "largest element is 20 - 1 = 19(due to until )")

        val weirdRange = 20 until 21
        weirdRange.largest.assert(20)

        val wrongRange = 20 until 20
        wrongRange.largest.assert(20)

    }

    @Test
    fun intRangeEndExclusive() {
        val range1 = 0 until 20
        range1.last.assert(19)
        range1.endExclusive.assert(20)

        val weirdRange = 40 until 41
        weirdRange.endExclusive.assert(41)

        val backwards = 20 until 10
        backwards.endExclusive.assert(10)
    }
}