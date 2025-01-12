package org.csenseoss.kotlin.patterns.expected.expectedMapCatchingError

import org.csenseoss.kotlin.patterns.expected.expectedMapCatchingError.operations.*
import org.csenseoss.kotlin.tests.assertions.*
import org.csenseoss.kotlin.tests.assertions.exceptions.*
import kotlin.test.*

class ExpectedMapCatchingErrorTest {

    @Test
    fun failedError() {
        val throwable = Throwable("error")
        val error: ExpectedMapCatchingError.Failed<Throwable> = ExpectedMapCatchingError.Failed(throwable)
        error.throwable.assert(throwable)
    }


    @Test
    fun exceptionError() {
        val throwable = Throwable("error")
        val error = ExpectedMapCatchingError.Exception<Throwable>(throwable)
        error.throwable.assert(throwable)
    }
}