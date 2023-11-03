package csense.kotlin.patterns.expected.operators

import csense.kotlin.patterns.expected.*
import csense.kotlin.tests.assertions.*
import kotlin.test.*

class TryMapTest {

    class ExpectedInputValueNothingTryMapTransform {

        @Test
        fun toSuccess() {
            val exp: Expected<Int, Nothing> = Expected.Success(42)
            val result: Expected<String, Nothing> = exp.tryMap { _: Int ->
                "value".asSuccess()
            }
            result.assertSuccessWith("value")
        }

        @Test

        fun toFailed() {
            val exp: Expected<Int, Nothing> = Expected.Success(42)
            val result: Expected<Nothing, String> = exp.tryMap { _: Int ->
                "error".asFailed()
            }
            result.assertFailedWith("error")
        }

    }

    class ExpectedInputValueErrorTryMapTransform {

        @Test
        fun successToSuccess() {
            val exp: Expected<Int, Exception> = Expected.Success(42)
            val res: Expected<Long, Exception> = exp.tryMap { 42.toLong().asSuccess() }
            res.assertSuccessWith(42L)

            val exp2: Expected<Int, Exception> = Expected.Success(42)
            val nothingIsAllowed: Expected<Long, Exception> = exp2.tryMap { it.toLong().asSuccess() }
            nothingIsAllowed.assertSuccessWith(42L)
        }

        @Test
        fun successToFailed() {
            val exp: Expected<Int, Long> = Expected.Success(42)
            val res: Expected<Nothing, Long> = exp.tryMap { 11.toLong().asFailed() }
            res.assertFailedWith(11L)
        }

        @Test
        fun failedShouldRemainFailed() {
            val exp: Expected<Int, Int> = Expected.Failed(42)
            val res: Expected<Long, Int> = exp.tryMap { shouldNotBeCalled() }
            res.assertFailedWith(42)
            //the following should trigger a COMPILER Error (mapping a failed is meaningless)
//            val nothingIsAllowed: Expected<Long, Int> = Expected.Failed(42).tryMap { shouldNotBeCalled() }
        }
    }

    class ExpectedSuccessInputValueTryMapTransform {

        @Test
        fun success() {
            Expected.Success(42)
                .tryMap { "$it".asSuccess() }
                .assertSuccessWith("42")
        }
    }
}
