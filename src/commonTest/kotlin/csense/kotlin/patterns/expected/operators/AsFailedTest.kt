package csense.kotlin.patterns.expected.operators

import csense.kotlin.patterns.expected.*
import csense.kotlin.tests.assertions.*
import kotlin.test.*

class AsFailedTest {
    @Test
    fun success() {
        Expected.Success(value = 42).asExpectedValue<Int, Int>().asFailed().assertFailedWith(42)
    }

    @Test
    fun failed() {
        Expected.Failed(error = 42).asExpectedValue<Int, Int>().asFailed().assertFailedWith(42)
    }

    @Test
    fun expectedNothingErrorAsFailed() {
        val x: Expected<Nothing, Int> = Expected.Failed(error = 42)
        x.asFailed.error.assert(42)
    }

}