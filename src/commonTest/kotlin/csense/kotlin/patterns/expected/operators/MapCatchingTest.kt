package csense.kotlin.patterns.expected.operators

import csense.kotlin.*
import csense.kotlin.patterns.expected.*
import csense.kotlin.patterns.expected.expectedMapCatchingError.*
import csense.kotlin.patterns.expected.operators.*
import csense.kotlin.tests.assertions.*
import kotlin.test.*

class MapCatchingTest {

    class ExpectedInputValueErrorMapCatchingTransform {

        @Test
        fun successMapNoThrows() {
            val exp: Expected<Int, Exception> = Expected.Success(42)
            val res = exp.mapCatching { 42.toLong() }
            res.assertSuccessWith(42L)

            val exp2: Expected<Int, Exception> = Expected.Success(42)
            val nothingIsAllowed = exp2.mapCatching { it.toLong() }
            nothingIsAllowed.assertSuccessWith(42L)
        }

        @Test
        fun successMapThrowsShouldBeException() {
            val thrownException = Exception("my exception")
            val exp: Expected<Int, Long> = Expected.Success(42)
            val res = exp.mapCatching { throw thrownException }

            res.assertIs<Expected.Failed<ExpectedMapCatchingError<Throwable>>>()
            val error = res.error
            error.isException().assertTrue()
            error.throwable.assert(thrownException)
        }

        @Test
        fun failedShouldRemainFailed() {
            val exp: Expected<Int, Int> = Expected.Failed(42)
            val res: Expected<Nothing, ExpectedMapCatchingError<Int>> = exp.mapCatching { shouldNotBeCalled() }
            res.assertIs<Expected.Failed<ExpectedMapCatchingError<Int>>>()
            val innerError = res.error
            innerError.assertIs<ExpectedMapCatchingError.Failed<Int>>()
            innerError.error.assert(42)

            //the following should trigger a COMPILER Error (mapping a failed is meaningless)
//            val nothingIsAllowed = Expected.Failed(42).mapCatching { shouldNotBeCalled() }
        }

    }
}