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
