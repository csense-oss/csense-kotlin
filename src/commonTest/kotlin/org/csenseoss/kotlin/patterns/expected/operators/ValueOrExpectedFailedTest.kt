package org.csenseoss.kotlin.patterns.expected.operators

import org.csenseoss.kotlin.patterns.expected.*
import org.csenseoss.kotlin.tests.assertions.*
import org.csenseoss.kotlin.tests.assertions.comparable.*
import org.csenseoss.kotlin.tests.assertions.general.*
import kotlin.test.*

class ValueOrExpectedFailedTest {
    class ExpectedValueErrorValueOrOnExpectedFailedOnFailed {

        @Test
        fun onSuccess() {
            Expected.Success(42).asExpectedValue<Int, Int>().valueOrOnExpectedFailed { shouldNotBeCalled() }.assert(42)
        }

        @Test
        fun onFailed() = assertCalled { shouldBeCalled: () -> Unit ->
            Expected.Failed("42").asExpectedValue<String, String>().valueOrOnExpectedFailed { it: Expected.Failed<String> ->
                shouldBeCalled()
                it.error.assert("42")
                return@assertCalled
            }
        }

    }

}