package csense.kotlin.patterns

import csense.kotlin.tests.assertions.*
import kotlin.test.*

class ExpectedTest {
    @Test
    fun isSuccess() {
        Expected.success(42).isSuccess().assertTrue()
        Expected.success("hmm").isSuccess().assertTrue()
        Expected.success(Exception("ex")).isSuccess().assertTrue()

        Expected.failed(42).isSuccess().assertFalse()
        Expected.failed("hmm").isSuccess().assertFalse()
        Expected.failed(Exception("ex")).isSuccess().assertFalse()

        val forContractTest: Expected<String, Int> = expected { "".asSuccess() }
        if (forContractTest.isSuccess()) {
            forContractTest.value.assertIs<String>()
        } else {
            forContractTest.error.assertIs<Int>()
        }
    }

    @Test
    fun isFailed() {
        Expected.success(42).isFailed().assertFalse()
        Expected.success("hmm").isFailed().assertFalse()
        Expected.success(Exception("ex")).isFailed().assertFalse()

        Expected.failed(42).isFailed().assertTrue()
        Expected.failed("hmm").isFailed().assertTrue()
        Expected.failed(Exception("ex")).isFailed().assertTrue()

        val forContractTest: Expected<String, Int> = expected { 42.asFailed() }
        if (forContractTest.isFailed()) {
            forContractTest.error.assertIs<Int>()
        } else {
            forContractTest.value.assertIs<String>()
        }
    }


    class ExpectedCompanionSuccess {

        @Test
        fun isOkSuccess() {
            Expected.success(42).value.assert(42)
            Expected.success("hello2").value.assert("hello2")
        }
    }

    class ExpectedCompanionFailed {

        @Test
        fun isOkFailed() {
            Expected.failed(42).error.assert(42)
            Expected.failed("hello2").error.assert("hello2")
        }
    }

    @Test
    fun expectedValueNothingValue() {
        val failed = Expected.success(42)
        failed.value.assertIs<Int>()
        failed.value.assert(42)
    }

    @Test
    fun expectedNothingErrorError() {
        val failed = Expected.failed(42)
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
            Expected.success(42).valueOrOnFailed { shouldNotBeCalled() }.assert(42)

            assertThrows<RuntimeException> {
                Expected.failed(42).valueOrOnFailed {
                    it.assert(42)
                    throw RuntimeException()
                }
            }
        }


        @Test
        fun canFastReturnFromFailed() {
            Expected.failed("42").valueOrOnFailed<String, String> {
                it.assert("42")
                return@canFastReturnFromFailed
            }
        }

    }

    class ExpectedValueErrorValueOrExpectedFailed {

        @Test
        fun expectedValueErrorValueOrExpectedFailed() {
            val failed = Expected.failed(42)
            assertThrows<Exception> {
                failed.valueOrExpectedFailed {
                    failed.assertByEquals(this)
                    throw Exception()
                }
            }

            Expected.success(42).valueOrExpectedFailed {
                shouldNotBeCalled()
            }.assert(42)
        }

    }

    class ExpectedDataErrorValueOrDefault {

        @Test
        fun argument() {
            Expected.success(42).valueOrDefault(11).assert(42)
            Expected.failed(42).valueOrDefault(0).assert(0)
        }


        @Test
        fun functional() {
            Expected.success(42).valueOrDefault { shouldNotBeCalled() }.assert(42)
            Expected.failed(42).valueOrDefault { 11 }.assert(11)
        }

    }

    @Test
    fun withErrorType() {
        val success: Expected<Int, RuntimeException> = Expected.success(42)
        success.withErrorType<Int, NotImplementedError>().apply {
            assertIs<Expected<Int, RuntimeException>>()
            assertByEquals(success)
        }

        val failed: Expected<Int, RuntimeException> = Expected.failed(RuntimeException())
        assertThrows<RuntimeException> {
            failed.withErrorType<Int, ErrorTypeException>()
        }
        failed.withErrorType<Int, RuntimeException>().assertByEquals(failed)

        failed.withErrorType<Int, Exception>().assertIs<ExpectedFailed<Exception>>()
    }

    @Test
    fun withErrorTypeOnWrongErrorType() {
        val success: Expected<Int, RuntimeException> = Expected.success(42)
        success.withErrorType<Int, NotImplementedError> { shouldNotBeCalled() }.apply {
            assertIs<Expected<Int, RuntimeException>>()
            assertByEquals(success)
        }

        val failed: Expected<Int, RuntimeException> = Expected.failed(RuntimeException())
        assertCalled { shouldBeCalled ->
            val fallbackError = ErrorTypeException()
            failed.withErrorType {
                shouldBeCalled()
                fallbackError
            }.assertIsApply<ExpectedFailed<ErrorTypeException>> {
                error.assertByEquals(fallbackError)
            }
        }
        failed.withErrorType<Int, RuntimeException> { shouldNotBeCalled() }.assertByEquals(failed)

        failed.withErrorType<Int, Exception> { shouldNotBeCalled() }.apply {
            assertIs<ExpectedFailed<Exception>>()
            error.assertIs<Exception>()
        }
    }

    @Test
    fun asErrorTypeOrNull() {
        val failed = Expected.failed(RuntimeException())
        failed.asErrorTypeOrNull<ErrorTypeException>().assertNull()
        failed.asErrorTypeOrNull<RuntimeException>().assertByEquals(failed)
        failed.asErrorTypeOrNull<RuntimeException>().assertNotNullApply {
            error.assertIs<RuntimeException>()
        }
        failed.asErrorTypeOrNull<Exception>().assertNotNullApply {
            error.assertIs<Exception>()
        }
    }

    class ExpectedInputValueErrorMap {

        @Test
        fun success() {
            val exp: Expected<Int, Exception> = Expected.success(42)
            val res = exp.map { 42.toLong() }
            res.assertIs<Expected<Long, Exception>>()
            res.assertIs<ExpectedSuccess<Long>>()
            res.value.assert(42L)

            val nothingIsAllowed = Expected.success(42).map { it.toLong() }
            nothingIsAllowed.value.assert(42L)
        }

        @Test
        fun failed() {
            val exp: Expected<Int, Int> = Expected.failed(42)
            val res: Expected<Long, Int> = exp.map { shouldNotBeCalled() }
            res.assertIs<Expected<Long, Int>>()
            res.assertIs<ExpectedFailed<Int>>()
            res.error.assert(42)

            //the following should trigger a COMPILER Error (mapping a failed is meaningless)
//            val nothingIsAllowed = Expected.failed(42).map { 43 }
        }

    }

    class ExpectedInputValueErrorTryMap {

        @Test
        fun successToSuccess() {
            val exp: Expected<Int, Exception> = Expected.success(42)
            val res = exp.tryMap { 42.toLong().asSuccess() }
            res.assertIs<Expected<Long, Exception>>()
            res.assertIs<ExpectedSuccess<Long>>()
            res.value.assert(42L)

            val exp2: Expected<Int, Exception> = Expected.success(42)
            val nothingIsAllowed = exp2.tryMap { it.toLong().asSuccess() }
            nothingIsAllowed.assertIs<ExpectedSuccess<Long>>()
            nothingIsAllowed.value.assert(42L)
        }

        @Test
        fun successToFailed() {
            val exp: Expected<Int, Long> = Expected.success(42)
            val res = exp.tryMap { 11.toLong().asFailed() }
            res.assertIs<ExpectedFailed<Long>>()
            res.error.assert(11)
        }

        @Test
        fun failedShouldRemainFailed() {
            val exp: Expected<Int, Int> = Expected.failed(42)
            val res: Expected<Long, Int> = exp.tryMap { shouldNotBeCalled() }
            res.assertIs<Expected<Long, Int>>()
            res.assertIs<ExpectedFailed<Int>>()
            res.error.assert(42)
            //the following should trigger a COMPILER Error (mapping a failed is meaningless)
//            val nothingIsAllowed: Expected<Long, Int> = Expected.failed(42).tryMap { shouldNotBeCalled() }
        }

    }

    class ExpectedInputValueErrorMapCatching {
        @Test
        fun successMapNoThrows() {
            val exp: Expected<Int, Exception> = Expected.success(42)
            val res = exp.tryMap { 42.toLong().asSuccess() }
            res.assertIs<Expected<Long, Exception>>()
            res.assertIs<ExpectedSuccess<Long>>()
            res.value.assert(42L)

            val exp2: Expected<Int, Exception> = Expected.success(42)
            val nothingIsAllowed = exp2.tryMap { it.toLong().asSuccess() }
            nothingIsAllowed.assertIs<ExpectedSuccess<Long>>()
            nothingIsAllowed.value.assert(42L)
        }

        @Test
        fun successMapThrows() {
            val exp: Expected<Int, Long> = Expected.success(42)
            val res = exp.tryMap { 11.toLong().asFailed() }
            res.assertIs<ExpectedFailed<Long>>()
            res.error.assert(11)

        }

        @Test
        fun failedShouldRemainFailed() {
            val exp: Expected<Int, Int> = Expected.failed(42)
            val res: Expected<Nothing, ExpectedMapCatchingError<Int>> = exp.mapCatching { shouldNotBeCalled() }
            res.assertIs<ExpectedFailed<ExpectedMapCatchingError<Int>>>()
            val innerError = res.error
            innerError.assertIs<ExpectedMapCatchingError.Failed<Int>>()
            innerError.error.assert(42)
            //the following should trigger a COMPILER Error (mapping a failed is meaningless)
//            val nothingIsAllowed = Expected.failed(42).mapCatching { shouldNotBeCalled() }
        }
    }

    class ExpectedValueErrorRecover {

        @Test
        fun success() {
            val exp: Expected<String, Int> = Expected.success("42")
            exp.recover { shouldNotBeCalled() }.value.assert("42")

            @Suppress("UNUSED_VARIABLE")
            val nothingError: Expected<String, Nothing> = Expected.success("test")
            //should cause a compiler error
//            nothingError.recover {  }

            //should cause a compiler error
//            Expected.success(42).recover {  }
        }


        @Test
        fun failed() {
            val exp: Expected<String, Int> = Expected.failed(1)
            exp.recover { "test" }.value.assert("test")

            val expNothing: Expected<Nothing, Int> = Expected.failed(999)
            expNothing.recover { "hello" }.value.assert("hello")
        }

    }

    class ExpectedValueErrorTryRecover {

        @Test
        fun success() {
            val exp: Expected<String, Int> = Expected.success("test")
            val res = exp.tryRecover { shouldNotBeCalled() }
            res.assertIs<ExpectedSuccess<String>>()
            res.value.assert("test")
            //should cause a compiler error
//            Expected.success(42).tryRecover {  }
//            val asNothing: Expected<String, Nothing> = Expected.success("")
//            //should cause a compiler error
//            asNothing.tryRecover {  }
        }


        @Test
        fun failedToSuccess() {
            val exp: Expected<String, Int> = Expected.failed(89)
            exp.tryRecover { it.asSuccess() }.assertIsApply<ExpectedSuccess<Int>> { value.assert(89) }
            val complex: Expected<String, Int> = exp.tryRecover {
                "1234".asSuccess()
            }
            complex.assertIs<ExpectedSuccess<String>>()
            complex.value.assert("1234")
        }


        @Test
        fun failedToFailed() {
            val exp: Expected<String, Int> = Expected.failed(89)
            exp.tryRecover { it.asFailed() }.assertIsApply<ExpectedFailed<Int>> {
                error.assert(89)
            }
        }

    }


    @Test
    fun expectedNothingErrorAsFailed() {
        val x: Expected<Nothing, Int> = Expected.failed(42)
        x.asFailed.error.assert(42)
    }

    @Test
    fun expectedValueNothingAsSuccess() {
        val x: Expected<Int, Nothing> = Expected.success(42)
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
            result.assertIs<ExpectedFailed<String>>()
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
            failed.assertIs<ExpectedFailed<String>>()
            failed.error.assert("success")
        }

        @Test
        fun actionThatReturnsSuccess() {
            val success = expected<String, String>(action = {
                "success".asSuccess()
            })
            success.assertIs<ExpectedSuccess<String>>()
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
            val exp: Expected<Int, String> = Expected.success(42)
            val result = exp.recoverCatching {
                shouldNotBeCalled()
            }
            result.assertIs<ExpectedSuccess<Int>>()
            result.value.assert(42)
        }

        @Test
        fun failed() {
            val exp: Expected<Int, String> = Expected.failed("42")
            val result = exp.recoverCatching {
                42
            }
            result.assertIs<ExpectedSuccess<Int>>()
            result.value.assert(42)
        }

        @Test
        fun throws() {
            val exp: Expected<Int, String> = Expected.failed("42")
            val exception = RuntimeException("message")
            val result = exp.recoverCatching {
                throw exception
            }
            result.assertIs<ExpectedFailed<ExpectedExceptionFailed<String>>>()
            result.error.failed.error.assert("42")
            result.error.exception.assert(exception)
        }
    }

    class ExpectedNothingErrorTryRecoverTransform {
        @Test
        fun failedToFailed() {
            val exp = Expected.failed(42)
            val result = exp.tryRecover {
                it.asFailed()
            }
            result.assertIs<ExpectedFailed<Int>>()
            result.error.assert(42)
        }

        @Test
        fun failedToSuccess() {
            val exp = Expected.failed(42)
            val result = exp.tryRecover {
                it.asSuccess()
            }
            result.assertIs<ExpectedSuccess<Int>>()
            result.value.assert(42)
        }
    }

    class ExpectedInputValueNothingTryMapTransform {

        @Test
        fun toSuccess() {
            val exp: Expected<Int, Nothing> = Expected.success(42)
            val result = exp.tryMap {
                "value".asSuccess()
            }
            result.assertIs<ExpectedSuccess<String>>()
            result.value.assert("value")
        }

        @Test
        fun toFailed() {
            val exp: Expected<Int, Nothing> = Expected.success(42)
            val result = exp.tryMap {
                "error".asFailed()
            }
            result.assertIs<ExpectedFailed<String>>()
            result.error.assert("error")
        }

    }

    class ExpectedSuccessInputMapTransform {

        @Test
        fun success() {
            Expected.success("test").map { 42 }.assertIsApply<ExpectedSuccess<Int>> {
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
            exp.assertIs<ExpectedSuccess<String>>()
            exp.value.assert("test")
        }

        @Test
        fun failed() {
            val exp = expectedCatching {
                RuntimeException().asFailed()
            }
            exp.assertIs<ExpectedFailed<Throwable>>()
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
            exp.assertIs<ExpectedFailed<Throwable>>()
            exp.error.assertIs<RuntimeException>()
        }
    }
}

class ErrorTypeException : Throwable()

class ExpectedContextTest {
    @Test
    fun asSuccess() {
        with(Expected.Companion.ExpectedContext.instance) {
            42.asSuccess()
        }.value.assert(42)

        with(Expected.Companion.ExpectedContext.instance) {
            "hello".asSuccess()
        }.value.assert("hello")
    }

    @Test
    fun asFailed() {
        with(Expected.Companion.ExpectedContext.instance) {
            42.asFailed()
        }.error.assert(42)

        with(Expected.Companion.ExpectedContext.instance) {
            "hello".asFailed()
        }.error.assert("hello")
    }
}