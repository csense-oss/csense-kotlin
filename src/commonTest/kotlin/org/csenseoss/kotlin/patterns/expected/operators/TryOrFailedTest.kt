package org.csenseoss.kotlin.patterns.expected.operators

import org.csenseoss.kotlin.patterns.expected.*
import org.csenseoss.kotlin.patterns.expected.*
import org.csenseoss.kotlin.patterns.expected.operators.*
import kotlin.test.*

class TryOrFailedTest {
    @Test
    fun throwing() {
        val exception = RuntimeException("wee")
        val result: Expected<String, Throwable> = tryOrFailed {
            throw exception
        }
        result.assertFailedWithByEquals(exception)
    }

    @Test
    fun returning() {
        val result: Expected<String, Throwable> = tryOrFailed {
            "success"
        }
        result.assertSuccessWith("success")
    }
}