package org.csenseoss.kotlin.patterns.expected.operators

import org.csenseoss.kotlin.patterns.expected.*
import org.csenseoss.kotlin.tests.assertions.*
import org.csenseoss.kotlin.tests.assertions.comparable.*
import org.csenseoss.kotlin.tests.assertions.exceptions.*
import org.csenseoss.kotlin.tests.assertions.general.*
import kotlin.test.*

class ValueOrOnFailedTest {
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
            Expected.Failed("42").valueOrOnFailed<String, String> { it: String ->
                it.assert("42")
                return@canFastReturnFromFailed
            }
        }

    }
}