package csense.kotlin.patterns.expected.operators

import csense.kotlin.patterns.expected.*
import csense.kotlin.tests.assertions.*
import kotlin.test.*

class ValueOrDefaultTest {

    class ExpectedDataErrorValueOrDefault {

        @Test
        fun argument() {
            Expected.Success(42).asExpected().valueOrDefault(11).assert(42)
            Expected.Failed(42).asExpectedValue<Int, Int>().valueOrDefault(0).assert(0)
        }


    }

    class ExpectedDataErrorValueOrDefaultLazy {

        @Test
        fun functional() {
            Expected.Success(42).asExpected().valueOrDefault { shouldNotBeCalled() }.assert(42)
            Expected.Failed(42).asExpectedValue<Int, Int>().valueOrDefault { 11 }.assert(11)
        }

    }

}