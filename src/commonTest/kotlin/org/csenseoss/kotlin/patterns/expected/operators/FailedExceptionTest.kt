package org.csenseoss.kotlin.patterns.expected.operators

import csense.kotlin.tests.assertions.*
import org.csenseoss.kotlin.patterns.expected.*
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