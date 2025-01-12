package org.csenseoss.kotlin.patterns.expected.expectedMapCatchingError

import org.csenseoss.kotlin.patterns.expected.*
import org.csenseoss.kotlin.tests.assertions.*
import org.csenseoss.kotlin.tests.assertions.comparable.*
import org.csenseoss.kotlin.tests.assertions.general.*
import kotlin.test.*

class ErrorTest {
    @Test
    fun expectedNothingErrorError() {
        val failed: Expected.Failed<Int> = Expected.Failed(42)
        failed.error.assertIs<Int>()
        failed.error.assert(42)
    }
}