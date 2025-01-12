package org.csenseoss.kotlin.patterns.expected.operators

import org.csenseoss.kotlin.patterns.expected.*
import org.csenseoss.kotlin.patterns.expected.operators.*
import org.csenseoss.kotlin.tests.assertions.*
import org.csenseoss.kotlin.tests.assertions.comparable.*
import org.csenseoss.kotlin.tests.assertions.comparable.assert
import org.csenseoss.kotlin.tests.assertions.exceptions.*
import org.csenseoss.kotlin.tests.assertions.general.*
import kotlin.test.*

class RecoverCatchingTest {
    class ExpectedValueErrorRecoverCatchingTransform {

        @Test
        fun success() {
            val exp: Expected<Int, String> = Expected.Success(value = 42)
            val result: Expected<Int, ExpectedExceptionFailed<String>> = exp.recoverCatching { _: String ->
                shouldNotBeCalled()
            }
            result.assertSuccessWith(42)
        }

        @Test
        fun failed() {
            val exp: Expected<Int, String> = Expected.Failed(error = "42")
            val result: Expected<Int, ExpectedExceptionFailed<String>> = exp.recoverCatching { _: String ->
                42
            }
            result.assertSuccessWith(42)
        }

        @Test
        fun throws() {
            val exp: Expected<Int, String> = Expected.Failed(error = "42")
            val exception = RuntimeException("message")
            val result: Expected<Int, ExpectedExceptionFailed<String>> = exp.recoverCatching { _: String ->
                throw exception
            }
            result.assertIs<Expected.Failed<ExpectedExceptionFailed<String>>>()
            result.error.failed.error.assert("42")
            result.error.exception.assert(exception)
        }
    }
}