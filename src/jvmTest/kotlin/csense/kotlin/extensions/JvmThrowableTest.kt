package csense.kotlin.extensions

import csense.kotlin.tests.assertions.*
import org.junit.*

class JvmThrowableTest {
    @Test
    fun throwableStackTraceToString() {
        val currentStackTrace = Throwable(message = "issue right here").stackTraceToString()
        currentStackTrace.assertStartsWith(
            "java.lang.Throwable: issue right here"
        )

        val thisCanonicalClassName = JvmThrowableTest::class.qualifiedName ?: ""
        val thisMethodName = this::throwableStackTraceToString.name
        currentStackTrace.assertContains("at $thisCanonicalClassName.$thisMethodName")
    }
}
