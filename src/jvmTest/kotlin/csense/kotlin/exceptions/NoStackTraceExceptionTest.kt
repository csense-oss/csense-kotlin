package csense.kotlin.exceptions

import csense.kotlin.tests.assertions.*
import org.junit.jupiter.api.*

class NoStackTraceExceptionTest {
    @Test
    fun hasNoStackTrace() {
        val exception = NoStackTraceException(message = "test")
        exception.stackTrace.assertEmpty("should not have a stacktrace")
        exception.message.assert("test")
    }
}