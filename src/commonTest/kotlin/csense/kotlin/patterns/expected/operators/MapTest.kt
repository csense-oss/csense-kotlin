package csense.kotlin.patterns.expected.operators

import csense.kotlin.patterns.expected.*
import csense.kotlin.tests.assertions.*
import kotlin.test.*

class MapTest {
    @Test
    fun success() {
        val exp: Expected<Int, Exception> = Expected.Success(value = 42)
        val res: Expected<Long, Exception> = exp.map { _: Int -> 42.toLong() }
        res.assertSuccessWith(42L)

        val nothingIsAllowed: Expected.Success<Long> = Expected.Success(42).map { it: Int -> it.toLong() }
        nothingIsAllowed.value.assert(42L)
    }


    @Test
    fun failed() {
        val exp: Expected<Int, Int> = Expected.Failed(error = 42)
        val res: Expected<Long, Int> = exp.map { shouldNotBeCalled() }
        res.assertFailedWith(42)
//        the following should trigger a COMPILER Error (mapping a failed is meaningless)
//            val nothingIsAllowed = Expected.Failed(42).map { 43 }
    }

    class ExpectedInputValueErrorMapTransform {

        @Test
        fun success() {
            val result: Expected.Success<Int> = Expected.Success(value = 42).map { it: Int -> it }
            result.assertSuccessWith(42)
        }


        @Test
        fun failed() {
            val result: Expected<Nothing, Int> =
                Expected.Failed(error = 42).asExpected().map { _: Any? -> shouldNotBeCalled() }
            result.assertFailedWith(42)
        }
    }

}
