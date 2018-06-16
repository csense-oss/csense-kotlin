package csense.kotlin.extensions.primitives

import csense.kotlin.extensions.primitives.isZero
import csense.kotlin.extensions.primitives.negative
import csense.kotlin.test.assertions.assert
import kotlin.test.Test

class FloatTest {

    @Test
    fun testNegative() {
        2f.negative.assert(-2f)
        (-2f).negative.assert(-2f)
        0f.negative.assert(0f)
    }

    @Test
    fun testIsZero() {
        2f.isZero().assert(false)
        0f.isZero().assert(true)
        0.1f.isZero(0.2f).assert(true)
    }
}