package org.csenseoss.kotlin.patterns.expected.operators

import org.csenseoss.kotlin.patterns.expected.*
import org.csenseoss.kotlin.tests.assertions.exceptions.*
import org.csenseoss.kotlin.tests.assertions.primitives.charSequence.*
import kotlin.test.*

class FailedExceptionTest {
    @Test
    fun failed() {
        val cause = RuntimeException()
        val failed: Expected.Failed<Exception> = Expected.FailedException(message = "message", cause = cause)
        failed.error.message.assert("message")
        failed.error.cause.assert(cause)
    }
}