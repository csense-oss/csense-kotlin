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
    fun expectedMapCatchingErrorErrorIsFailed() {
        ExpectedMapCatchingError.Exception<String>(Exception()).isFailed().assertFalse()
        ExpectedMapCatchingError.Failed("").isFailed().assertTrue()
        //for contracts
        val forContracts: ExpectedMapCatchingError<String> = ExpectedMapCatchingError.Failed("")
        if (forContracts.isFailed()) {
            forContracts.error.assertIs<String>()
        } else {
            forContracts.exception.assertIs<Exception>()
        }

    }

    @Test
    fun expectedMapCatchingErrorErrorIsException() {
        ExpectedMapCatchingError.Exception<String>(Exception()).isException().assertTrue()
        ExpectedMapCatchingError.Failed("").isException().assertFalse()
        //for contracts
        val forContracts: ExpectedMapCatchingError<String> = ExpectedMapCatchingError.Exception(Exception())
        if (forContracts.isException()) {
            forContracts.exception.assertIs<Exception>()
        } else {
            forContracts.error.assertIs<String>()
        }
    }

    class ExpectedValueErrorValueOrOnFailed {

        @Test
        fun handlesSuccessAndFailedCorrectly() {
            Expected.Success(42).asExpected().valueOrOnFailed { shouldNotBeCalled() }.assert(42)

            assertThrows<RuntimeException> {
                Expected.Failed(42).asExpected().valueOrOnFailed {
                    it.assert(42)
                    throw RuntimeException()
                }
            }
        }


        @Test
        fun canFastReturnFromFailed() {
            Expected.Failed("42").valueOrOnFailed<String, String> {
                it.assert("42")
                return@canFastReturnFromFailed
            }
        }

    }

    class ExpectedValueErrorValueOrExpectedFailed {

        @Test
        fun expectedValueErrorValueOrExpectedFailed() {
            val failed = Expected.Failed(42)
            assertThrows<Exception> {
                failed.asExpected().valueOrFailed {
                    failed.assertByEquals(this)
                    throw Exception()
                }
            }

            Expected.Success(42).asExpected().valueOrFailed {
                shouldNotBeCalled()
            }.assert(42)
        }

    }

    class ExpectedDataErrorValueOrDefault {

        @Test
        fun argument() {
            Expected.Success(42).asExpected().valueOrDefault(11).assert(42)
            Expected.Failed(42).asExpectedValue<Int, Int>().valueOrDefault(0).assert(0)
        }


        @Test
        fun functional() {
            Expected.Success(42).asExpected().valueOrDefault { shouldNotBeCalled() }.assert(42)
            Expected.Failed(42).asExpectedValue<Int, Int>().valueOrDefault { 11 }.assert(11)
        }

    }





    class ExpectedValueErrorRecover {

        @Test
        fun success() {
            val exp: Expected<String, Int> = Expected.Success("42")
            exp.recover { shouldNotBeCalled() }.value.assert("42")

            @Suppress("UNUSED_VARIABLE")
            val nothingError: Expected<String, Nothing> = Expected.Success("test")
            //should cause a compiler error
//            nothingError.recover {  }

            //should cause a compiler error
//            Expected.Success(42).recover {  }
        }


        @Test
        fun failed() {
            val exp: Expected<String, Int> = Expected.Failed(1)
            exp.recover { "test" }.value.assert("test")

            val expNothing: Expected<Nothing, Int> = Expected.Failed(999)
            expNothing.recover { "hello" }.value.assert("hello")
        }

    }

    class ExpectedValueErrorTryRecover {

        @Test
        fun success() {
            val exp: Expected<String, Int> = Expected.Success("test")
            val res = exp.tryRecover { shouldNotBeCalled() }
            res.assertIs<Expected.Success<String>>()
            res.value.assert("test")
            //should cause a compiler error
//            Expected.Success(42).tryRecover {  }
//            val asNothing: Expected<String, Nothing> = Expected.Success("")
//            //should cause a compiler error
//            asNothing.tryRecover {  }
        }


        @Test
        fun failedToSuccess() {
            val exp: Expected<String, Int> = Expected.Failed(89)
            exp.tryRecover { it.asSuccess() }.assertIsApply<Expected.Success<Int>> { value.assert(89) }
            val complex: Expected<String, Int> = exp.tryRecover {
                "1234".asSuccess()
            }
            complex.assertIs<Expected.Success<String>>()
            complex.value.assert("1234")
        }


        @Test
        fun failedToFailed() {
            val exp: Expected<String, Int> = Expected.Failed(89)
            exp.tryRecover { it.asFailed() }.assertIsApply<Expected.Failed<Int>> {
                error.assert(89)
            }
        }

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

    class ExpectedValueErrorRecoverCatching {

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

    class ExpectedNothingErrorTryRecoverTransform {
        @Test
        fun failedToFailed() {
            val exp = Expected.Failed(42)
            val result = exp.tryRecover {
                it.asFailed()
            }
            result.assertIs<Expected.Failed<Int>>()
            result.error.assert(42)
        }

        @Test
        fun failedToSuccess() {
            val exp = Expected.Failed(42)
            val result = exp.tryRecover {
                it.asSuccess()
            }
            result.assertIs<Expected.Success<Int>>()
            result.value.assert(42)
        }
    }

    class ExpectedInputValueNothingTryMapTransform {

        @Test
        fun toSuccess() {
            val exp: Expected<Int, Nothing> = Expected.Success(42)
            val result = exp.tryMap {
                "value".asSuccess()
            }
            result.assertIs<Expected.Success<String>>()
            result.value.assert("value")
        }

        @Test
        fun toFailed() {
            val exp: Expected<Int, Nothing> = Expected.Success(42)
            val result = exp.tryMap {
                "error".asFailed()
            }
            result.assertIs<Expected.Failed<String>>()
            result.error.assert("error")
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

fun <Value> Expected.Success<Value>.asExpected(): Expected<Value, *> {
    return this
}

fun <Error> Expected.Failed<Error>.asExpected(): Expected<*, Error> {
    return this
}

fun <Value, Error> Expected.Failed<Error>.asExpectedValue(): Expected<Value, Error> {
    return this
}