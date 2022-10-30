package csense.kotlin.patterns.expected.operators

import csense.kotlin.patterns.expected.*
import csense.kotlin.patterns.expected.operators.*
import csense.kotlin.tests.assertions.*
import kotlin.test.*

class MapTest {
    @Test
    fun success() {
        val exp: Expected<Int, Exception> = Expected.Success(42)
        val res = exp.map { 42.toLong() }
        res.assertIs<Expected<Long, Exception>>()
        res.assertIs<Expected.Success<Long>>()
        res.value.assert(42L)

        val nothingIsAllowed = Expected.Success(42).map { it.toLong() }
        nothingIsAllowed.value.assert(42L)
    }

    @Test
    fun failed() {
        val exp: Expected<Int, Int> = Expected.Failed(42)
        val res: Expected<Long, Int> = exp.map { shouldNotBeCalled() }
        res.assertIs<Expected<Long, Int>>()
        res.assertIs<Expected.Failed<Int>>()
        res.error.assert(42)
//        the following should trigger a COMPILER Error (mapping a failed is meaningless)
//            val nothingIsAllowed = Expected.Failed(42).map { 43 }
    }

    class ExpectedInputValueErrorMapTransform {

        @Test
        fun success() {
            val result = Expected.Success(42).map { it }
            result.assertIs<Expected.Success<Int>>()
            result.value.assert(42)
        }


        @Test
        fun failed() {
            val result = Expected.Failed(42).asExpected().map { shouldNotBeCalled() }
            result.assertIs<Expected.Failed<Int>>()
            result.error.assert(42)
        }
    }

    @Test
    fun expectedSuccessInputValueMapTransform() {
        val result = Expected.Success(42).map { it }
        result.assertIs<Expected.Success<Int>>()
        result.value.assert(42)
    }
}
