package csense.kotlin.patterns.expected.expectedMapCatchingError

import csense.kotlin.tests.assertions.*
import kotlin.test.*

class ExpectedMapCatchingErrorExtensionsTest {

    class ExpectedMapCatchingErrorTThrowable {

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
}
