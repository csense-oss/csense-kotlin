package org.csenseoss.kotlin.exceptions

import org.csenseoss.kotlin.tests.assertions.collections.array.generic.*
import org.csenseoss.kotlin.tests.assertions.primitives.charSequence.*
import org.junit.jupiter.api.*

class NoStackTraceExceptionTest {
    @Test
    fun hasNoStackTrace() {
        val exception = NoStackTraceException(message = "test")
        exception.stackTrace.assertEmpty("should not have a stacktrace")
        exception.message.assert("test")
    }
}