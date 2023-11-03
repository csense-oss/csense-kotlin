package csense.kotlin.patterns.expected.operators

import csense.kotlin.patterns.expected.*
import csense.kotlin.patterns.expected.expectedMapCatchingError.*
import csense.kotlin.patterns.expected.expectedMapCatchingError.operations.*
import csense.kotlin.tests.assertions.*
import kotlin.test.*

class MapCatchingTest {

    class ExpectedInputValueErrorMapCatchingTransform {

        @Test
        fun successMapNoThrows() {
            val exp: Expected<Int, Exception> = Expected.Success(value = 42)
            val res: Expected<Long, ExpectedMapCatchingError<Exception>> = exp.mapCatching { _: Int -> 42.toLong() }
            res.assertSuccessWith(42L)

            val exp2: Expected<Int, Exception> = Expected.Success(value = 42)
            val nothingIsAllowed: Expected<Long, ExpectedMapCatchingError<Exception>> =
                exp2.mapCatching { it: Int -> it.toLong() }

            nothingIsAllowed.assertSuccessWith(42L)
        }

        @Test
        fun successMapThrowsShouldBeException() {
            val thrownException = Exception("my exception")
            val exp: Expected<Int, Long> = Expected.Success(value = 42)
            val res: Expected<Nothing, ExpectedMapCatchingError<Long>> =
                exp.mapCatching { _: Int -> throw thrownException }

            res.assertIs<Expected.Failed<ExpectedMapCatchingError<Throwable>>>()
            val error: ExpectedMapCatchingError<Throwable> = res.error
            error.isException().assertTrue()
            error.throwable.assert(thrownException)
        }

        @Test
        fun failedShouldRemainFailed() {
            val exp: Expected<Int, Int> = Expected.Failed(error = 42)
            val res: Expected<Nothing, ExpectedMapCatchingError<Int>> =
                exp.mapCatching { _: Int -> shouldNotBeCalled() }

            res.assertIs<Expected.Failed<ExpectedMapCatchingError<Int>>>()
            val innerError: ExpectedMapCatchingError<Int> = res.error
            innerError.assertIs<ExpectedMapCatchingError.Failed<Int>>()
            innerError.error.assert(42)

            //the following should trigger a COMPILER Error (mapping a failed is meaningless)
//            val nothingIsAllowed = Expected.Failed(42).mapCatching { shouldNotBeCalled() }
        }

    }
}