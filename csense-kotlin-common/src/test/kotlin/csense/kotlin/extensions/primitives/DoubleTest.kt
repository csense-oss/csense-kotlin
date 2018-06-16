package csense.kotlin.extensions.primitives

import csense.kotlin.extensions.primitives.negative
import csense.kotlin.test.assertions.assert
import kotlin.test.Test

class DoubleTest {

    @Test
    fun getNegative() {
        val positive: Double = 2.0
        positive.negative.assert(-2.0)
        val negative: Double = -2.0
        negative.negative.assert(-2.0)
    }


}