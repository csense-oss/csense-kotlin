package csense.kotlin.extensions

import csense.kotlin.tests.assertions.*
import org.junit.*

class ThrowableTest {
    @Test
    fun throwableStackTraceToString() {
        val currentStackTrace = Throwable(message = "issue right here").stackTraceToString()
        currentStackTrace.assertContainsInOrder(
            values = listOf("issue right here", "ThrowableTest.throwableStackTraceToString"),
            ignoreCase = false
        )
    }
}
