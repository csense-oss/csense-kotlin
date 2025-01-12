package org.csenseoss.kotlin.patterns.expected.operators

import org.csenseoss.kotlin.patterns.expected.*
import org.csenseoss.kotlin.tests.assertions.exceptions.*
import org.csenseoss.kotlin.tests.assertions.general.*
import kotlin.test.*

class WithErrorTypeTest {
    @Test
    fun withErrorType() {
        val success: Expected<Int, RuntimeException> = Expected.Success(42)
        success.withErrorType<Int, NotImplementedError>().apply {
            assertIs<Expected<Int, RuntimeException>>()
            assertByEquals(success)
        }

        val failed: Expected<Int, RuntimeException> = Expected.Failed(RuntimeException())
        assertThrows<RuntimeException> {
            failed.withErrorType<Int, ErrorTypeException>()
        }
        failed.withErrorType<Int, RuntimeException>().assertByEquals(failed)

        failed.withErrorType<Int, Exception>().assertIs<Expected.Failed<Exception>>()
    }

    @Test
    fun withErrorTypeOnWrongErrorType() {
        val success: Expected<Int, RuntimeException> = Expected.Success(42)
        success.withErrorType<Int, NotImplementedError> { shouldNotBeCalled() }.apply {
            assertIs<Expected<Int, RuntimeException>>()
            assertByEquals(success)
        }

        val failed: Expected<Int, RuntimeException> = Expected.Failed(RuntimeException())
        assertCalled { shouldBeCalled: () -> Unit ->
            val fallbackError = ErrorTypeException()
            val failedType = failed.withErrorType {
                shouldBeCalled()
                fallbackError
            }
            failedType.assertIs<Expected.Failed<ErrorTypeException>>()
            failedType.error.assertByEquals(fallbackError)
        }
        failed.withErrorType<Int, RuntimeException> { shouldNotBeCalled() }.assertByEquals(failed)

        failed.withErrorType<Int, Exception> { shouldNotBeCalled() }.apply {
            assertIs<Expected.Failed<Exception>>()
            error.assertIs<Exception>()
        }
    }


}