package csense.kotlin.patterns.expected.expectedMapCatchingError.operations

import csense.kotlin.patterns.expected.expectedMapCatchingError.*
import csense.kotlin.tests.assertions.*
import kotlin.test.*

class ThrowableTest {
    @Test
    fun exception() {
        val realException = RuntimeException("")
        val expected: ExpectedMapCatchingError<Throwable> = ExpectedMapCatchingError.Exception(realException)
        expected.throwable.assert(realException)
    }

    @Test
    fun failedThrowable() {
        val realException = RuntimeException("")
        val expected: ExpectedMapCatchingError<Throwable> = ExpectedMapCatchingError.Failed(realException)
        expected.throwable.assert(realException)
    }
}