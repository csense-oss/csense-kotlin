package csense.kotlin.patterns.expected.expectedMapCatchingError

import csense.kotlin.patterns.expected.*
import csense.kotlin.tests.assertions.*
import kotlin.test.*

class ErrorTest {
    @Test
    fun expectedNothingErrorError() {
        val failed: Expected.Failed<Int> = Expected.Failed(42)
        failed.error.assertIs<Int>()
        failed.error.assert(42)
    }
}