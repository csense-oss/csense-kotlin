package org.csenseoss.kotlin.extensions.throwable

import org.csenseoss.kotlin.tests.assertions.general.*
import org.csenseoss.kotlin.tests.assertions.primitives.charSequence.*
import org.junit.jupiter.api.*

class JvmThrowableTest {

    @Nested
    class ToSensitiveStackTraceString {

        @Test
        fun excludesMessages() {
            val trace = RuntimeException().toSensitiveStackTraceString()
            trace.assertStartsWith("java.lang.RuntimeException -message excluded-")
        }
    }

    class FormatToString {
        @Test
        fun usesCallbacks() {
            val trace: String = RuntimeException().formatToString(
                onException = { exception: Throwable -> "exception ${exception.javaClass.name}" },
                onStackTrace = { stackTraceElement: StackTraceElement -> "stacktrace for ${stackTraceElement.className}" },
                onSuppressed = { exception: Throwable -> shouldNotBeCalled() }
            )
            trace.assertStartsWith(
                "exception java.lang.RuntimeException\n" +
                        "stacktrace for "
            )
        }

        @Test
        fun handlesSuppressed() {
            val rootException = RuntimeException()
            rootException.addSuppressed(IllegalArgumentException())
            val trace: String = rootException.formatToString(
                onException = { exception: Throwable -> "" },
                onStackTrace = { stackTraceElement: StackTraceElement -> "" },
                onSuppressed = { exception: Throwable -> "suppressed ${exception.javaClass.simpleName}" }
            )
            trace.assertStartsWith("suppressed IllegalArgumentException")
        }
    }
}