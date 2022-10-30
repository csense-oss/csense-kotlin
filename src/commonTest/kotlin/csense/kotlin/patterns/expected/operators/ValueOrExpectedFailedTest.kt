package csense.kotlin.patterns.expected.operators

import csense.kotlin.patterns.expected.*
import csense.kotlin.tests.assertions.*
import kotlin.test.*

class ValueOrExpectedFailedTest {
    class ExpectedValueErrorValueOrOnExpectedFailedOnFailed {

        @Test
        fun onSuccess() {
            Expected.Success(42).asExpectedValue<Int, Int>().valueOrOnExpectedFailed { shouldNotBeCalled() }.assert(42)
        }

        @Test
        fun onFailed() {
            assertCalled { shouldBeCalled ->
                Expected.Failed("42").asExpectedValue<String, String>().valueOrOnExpectedFailed {
                    shouldBeCalled()
                    it.error.assert("42")
                    return@assertCalled
                }
            }
        }

    }

}