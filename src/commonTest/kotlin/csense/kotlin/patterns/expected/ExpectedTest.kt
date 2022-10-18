package csense.kotlin.patterns.expected

import csense.kotlin.tests.assertions.*
import kotlin.test.*

class ExpectedTest {

    class ExpectedCompanionSuccess {

        @Test
        fun isOkSuccess() {
            Expected.Success(42).value.assert(42)
            Expected.Success("hello2").value.assert("hello2")
        }
    }

    class ExpectedCompanionFailed {

        @Test
        fun isOkFailed() {
            Expected.Failed(42).error.assert(42)
            Expected.Failed("hello2").error.assert("hello2")
        }
    }

    @Test
    fun expectedValueNothingValue() {
        val failed = Expected.Success(42)
        failed.value.assertIs<Int>()
        failed.value.assert(42)
    }

    @Test
    fun expectedNothingErrorError() {
        val failed = Expected.Failed(42)
        failed.error.assertIs<Int>()
        failed.error.assert(42)
    }


    @Test
    fun expectedNothingErrorAsFailed() {
        val x: Expected<Nothing, Int> = Expected.Failed(42)
        x.asFailed.error.assert(42)
    }

    @Test
    fun expectedValueNothingAsSuccess() {
        val x: Expected<Int, Nothing> = Expected.Success(42)
        x.asSuccess.value.assert(42)
    }

    class ExpectedActionOnException {

        @Test
        fun actionWithNoExceptionSuccess() {
            val result: Expected<Int, Nothing> = expected(action = {
                42.asSuccess()
            }, onException = {
                shouldNotBeCalled()
            })
            result.value.assert(42)
        }

        @Test
        fun actionWithNoExceptionFailed() {
            val result: Expected<Nothing, Int> = expected(action = {
                42.asFailed()
            }, onException = {
                shouldNotBeCalled()
            })
            result.error.assert(42)
        }

        @Suppress(
            "UNREACHABLE_CODE",
            "UNUSED_VARIABLE",
            "UNUSED_ANONYMOUS_PARAMETER"
        ) //intellij cannot figure this out?
        @Test
        fun actionThatThrows() = assertCalled { shouldBeCalled ->
            val exception = RuntimeException("Wee")
            val result: Expected<String, String> = expected(action = {
                throw exception
            }, onException = {
                shouldBeCalled()
                it.assert(exception)
                "error"
            })
            result.assertIs<Expected.Failed<String>>()
            result.error.assert("error")
        }

    }

    @Test
    fun expectedAction() {

        @Test
        fun actionThatReturnsFailed() {
            val failed = expected<String, String>(action = {
                "failed".asFailed()
            })
            failed.assertIs<Expected.Failed<String>>()
            failed.error.assert("success")
        }

        @Test
        fun actionThatReturnsSuccess() {
            val success = expected<String, String>(action = {
                "success".asSuccess()
            })
            success.assertIs<Expected.Success<String>>()
            success.value.assert("success")
        }

        @Test
        fun actionThatThrows() = assertThrows<RuntimeException> {
            expected<String, String>(action = {
                throw RuntimeException()
            })
        }

    }


    class ExpectedSuccessInputMapTransform {

        @Test
        fun success() {
            Expected.Success("test").map { 42 }.assertIsApply<Expected.Success<Int>> {
                value.assert(42)
            }
        }

    }


    class ExpectedCatching {
        @Test
        fun success() {
            val exp = expectedCatching {
                "test".asSuccess()
            }
            exp.assertIs<Expected.Success<String>>()
            exp.value.assert("test")
        }

        @Test
        fun failed() {
            val exp = expectedCatching {
                RuntimeException().asFailed()
            }
            exp.assertIs<Expected.Failed<Throwable>>()
            exp.error.assertIs<RuntimeException>()
        }

        @Suppress(
            "UNREACHABLE_CODE",
            "UNUSED_VARIABLE"
        ) //kotlin compiler / idea does not know calls in place != exceptions will be passed though
        @Test
        fun throwing() {
            val exp: Expected<Nothing, Throwable> = expectedCatching {
                throw RuntimeException()
            }
            exp.assertIs<Expected.Failed<Throwable>>()
            exp.error.assertIs<RuntimeException>()
        }
    }

    class ExpectedValueErrorApplyIfSuccess {

        @Test
        fun onFailed() {
            Expected.Failed(Throwable("error")).applyIfSuccess {
                shouldNotBeCalled()
            }
        }

        @Test
        fun onSuccess() = assertCalled { shouldBeCalled ->
            Expected.Success("test").applyIfSuccess {
                value.assert("test")
                shouldBeCalled()
            }
        }

    }

    class ExpectedValueErrorApplyIfFailed {

        @Test
        fun onFailed() = assertCalled { shouldBeCalled ->
            Expected.Failed("failedText").applyIfFailed {
                error.assert("failedText")
                shouldBeCalled()
            }
        }

        @Test
        fun onSuccess() {
            Expected.Success("test").applyIfFailed {
                shouldNotBeCalled()
            }
        }

    }

    class ExpectedCompanionSuccessOrFailed {

        @Test
        fun nullSuccess() {
            Expected.successOrFailed(potentialSuccess = null, potentialErrorOrFallback = "error")
                .assertIsApply<Expected.Failed<String>> {
                    error.assert("error")
                }

        }


        @Test
        fun success() {
            Expected.successOrFailed(potentialSuccess = "test", potentialErrorOrFallback = "error")
                .assertIsApply<Expected.Success<String>> {
                    value.assert("test")
                }
        }
    }


}

class ErrorTypeException : Throwable()

class ExpectedContextTest {
    @Test
    fun asSuccess() {
        with(Expected.Companion.ExpectedContext) {
            42.asSuccess()
        }.value.assert(42)

        with(Expected.Companion.ExpectedContext) {
            "hello".asSuccess()
        }.value.assert("hello")
    }

    @Test
    fun asFailed() {
        with(Expected.Companion.ExpectedContext) {
            42.asFailed()
        }.error.assert(42)

        with(Expected.Companion.ExpectedContext) {
            "hello".asFailed()
        }.error.assert("hello")
    }
}
