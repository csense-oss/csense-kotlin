package csense.kotlin.patterns.expected.operators

import csense.kotlin.patterns.expected.*
import csense.kotlin.patterns.expected.operators.*
import csense.kotlin.tests.assertions.*
import kotlin.test.*

class RecoverCatchingTest {
    class ExpectedValueErrorRecoverCatchingTransform {

        @Test
        fun success() {
            val exp: Expected<Int, String> = Expected.Success(42)
            val result = exp.recoverCatching {
                shouldNotBeCalled()
            }
            result.assertIs<Expected.Success<Int>>()
            result.value.assert(42)
        }

        @Test
        fun failed() {
            val exp: Expected<Int, String> = Expected.Failed("42")
            val result = exp.recoverCatching {
                42
            }
            result.assertIs<Expected.Success<Int>>()
            result.value.assert(42)
        }

        @Test
        fun throws() {
            val exp: Expected<Int, String> = Expected.Failed("42")
            val exception = RuntimeException("message")
            val result = exp.recoverCatching {
                throw exception
            }
            result.assertIs<Expected.Failed<ExpectedExceptionFailed<String>>>()
            result.error.failed.error.assert("42")
            result.error.exception.assert(exception)
        }
    }
}