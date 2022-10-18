package csense.kotlin.patterns.expected

import csense.kotlin.tests.assertions.*
import kotlin.test.*

class TryMapTest {

    class ExpectedInputValueNothingTryMapTransform {

        @Test
        fun toSuccess() {
            val exp: Expected<Int, Nothing> = Expected.Success(42)
            val result = exp.tryMap {
                "value".asSuccess()
            }
            result.assertIs<Expected.Success<String>>()
            result.value.assert("value")
        }

        @Test
        fun toFailed() {
            val exp: Expected<Int, Nothing> = Expected.Success(42)
            val result = exp.tryMap {
                "error".asFailed()
            }
            result.assertIs<Expected.Failed<String>>()
            result.error.assert("error")
        }

    }

    class ExpectedInputValueErrorTryMapTransform {

        @Test
        fun successToSuccess() {
            val exp: Expected<Int, Exception> = Expected.Success(42)
            val res = exp.tryMap { 42.toLong().asSuccess() }
            res.assertIs<Expected<Long, Exception>>()
            res.assertIs<Expected.Success<Long>>()
            res.value.assert(42L)

            val exp2: Expected<Int, Exception> = Expected.Success(42)
            val nothingIsAllowed = exp2.tryMap { it.toLong().asSuccess() }
            nothingIsAllowed.assertIs<Expected.Success<Long>>()
            nothingIsAllowed.value.assert(42L)
        }

        @Test
        fun successToFailed() {
            val exp: Expected<Int, Long> = Expected.Success(42)
            val res = exp.tryMap { 11.toLong().asFailed() }
            res.assertIs<Expected.Failed<Long>>()
            res.error.assert(11)
        }

        @Test
        fun failedShouldRemainFailed() {
            val exp: Expected<Int, Int> = Expected.Failed(42)
            val res: Expected<Long, Int> = exp.tryMap { shouldNotBeCalled() }
            res.assertIs<Expected<Long, Int>>()
            res.assertIs<Expected.Failed<Int>>()
            res.error.assert(42)
            //the following should trigger a COMPILER Error (mapping a failed is meaningless)
//            val nothingIsAllowed: Expected<Long, Int> = Expected.Failed(42).tryMap { shouldNotBeCalled() }
        }
    }

    class ExpectedSuccessInputValueTryMapTransform {

        @Test
        fun success() {
            Expected.Success(42)
                .tryMap { "$it".asSuccess() }
                .assertIsApply<Expected.Success<String>> {
                    value.assert("42")
                }
        }

    }
}