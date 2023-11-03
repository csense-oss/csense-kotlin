package csense.kotlin.patterns.expected.operators

import csense.kotlin.patterns.expected.*
import csense.kotlin.tests.assertions.*
import kotlin.test.*

class ExpectedTest {

    class ExpectedActionOnException {

        @Test
        fun actionWithNoExceptionSuccess() {
            val result: Expected<Int, Nothing> = expected(action = {
                42.asSuccess()
            }, onException = { _: Throwable ->
                shouldNotBeCalled()
            })
            result.value.assert(42)
        }

        @Test
        fun actionWithNoExceptionFailed() {
            val result: Expected<Nothing, Int> = expected(action = {
                42.asFailed()
            }, onException = { _: Throwable ->
                shouldNotBeCalled()
            })
            result.error.assert(42)
        }

        @Test
        fun actionThatThrows(): Unit = assertCalled { shouldBeCalled: () -> Unit ->
            val exception = RuntimeException("Wee")
            val result: Expected<String, String> = expected(action = {
                throw exception
            }, onException = { it: Throwable ->
                shouldBeCalled()
                it.assert(exception)
                "error"
            })
            result.assertFailedWith("error")
        }

    }

    class ExpectedAction {

        @Test
        fun actionThatReturnsFailed() {
            val failed: Expected<String, String> = expected<String, String>(action = {
                "failed".asFailed()
            })
            failed.assertFailedWith("failed")
        }

        @Test
        fun actionThatReturnsSuccess() {
            val success: Expected<String, String> = expected<String, String>(action = {
                "success".asSuccess()
            })
            success.assertSuccessWith("success")
        }

        @Test
        fun actionThatThrows(): Unit = assertThrows<RuntimeException> {
            expected<String, String>(action = {
                throw RuntimeException()
            })
        }

    }


}