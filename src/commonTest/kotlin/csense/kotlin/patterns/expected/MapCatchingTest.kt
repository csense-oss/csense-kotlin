package csense.kotlin.patterns.expected

import csense.kotlin.tests.assertions.*
import kotlin.test.*

class MapCatchingTest {
    @Test
    fun successMapNoThrows() {
        val exp: Expected<Int, Exception> = Expected.Success(42)
        val res = exp.mapCatching { 42.toLong() }
        res.assertIs<Expected<Long, Exception>>()
        res.assertIs<Expected.Success<Long>>()
        res.value.assert(42L)

        val exp2: Expected<Int, Exception> = Expected.Success(42)
        val nothingIsAllowed = exp2.mapCatching { it.toLong() }
        nothingIsAllowed.assertIs<Expected.Success<Long>>()
        nothingIsAllowed.value.assert(42L)
    }

    @Test
    fun successMapThrowsShouldBeException() {
        val thrownException = Exception("my exception")
        val exp: Expected<Int, Long> = Expected.Success(42)
        val res = exp.mapCatching { throw thrownException }
        res.assertIs<Expected.Failed<ExpectedMapCatchingError<Throwable>>>()
        val error = res.error
        error.isException().assertTrue()
        error.assertIs<ExpectedMapCatchingError.Exception<Throwable>>()
        error.exception.assert(thrownException)
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