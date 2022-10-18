package csense.kotlin.patterns.expected

import csense.kotlin.tests.assertions.*
import kotlin.test.*

class ValueOrExpectedFailedTest {
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

    class ExpectedValueErrorValueOrFailedOnFailed {
        @Test
        fun onSuccess() {
            Expected.Success(42).asExpectedValue<Int, Int>().valueOrFailed { shouldNotBeCalled() }.assert(42)
        }

        @Test
        fun onFailed() {
            assertCalled { shouldBeCalled ->
                Expected.Failed("42").asExpectedValue<String, String>().valueOrFailed {
                    shouldBeCalled()
                    error.assert("42")
                    return@assertCalled
                }
            }
        }

    }
}