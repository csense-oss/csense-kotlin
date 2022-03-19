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
}

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