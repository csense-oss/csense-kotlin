package csense.kotlin.patterns.expected.operators

import csense.kotlin.patterns.expected.*
import csense.kotlin.tests.assertions.*
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