package org.csenseoss.kotlin.patterns.expected.operators

import org.csenseoss.kotlin.patterns.expected.*
import org.csenseoss.kotlin.patterns.expected.*
import org.csenseoss.kotlin.patterns.expected.operators.*
import kotlin.test.*

class ExpectedCatchingTest {
    @Test
    fun success() {
        val exp: Expected<String, Throwable> = expectedCatching {
            "test".asSuccess()
        }
        exp.assertSuccessWith("test")
    }

    @Test
    fun failed() {
        val exception = RuntimeException()
        val exp: Expected<Nothing, Throwable> = expectedCatching {
            exception.asFailed()
        }
        exp.assertFailedWithByEquals(exception)
    }

    @Test
    fun throwing() {
        val exception = RuntimeException()
        val exp: Expected<Nothing, Throwable> = expectedCatching {
            throw exception
        }
        exp.assertFailedWithByEquals(exception)
    }
}