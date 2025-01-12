package org.csenseoss.kotlin.patterns.expected.operators

import org.csenseoss.kotlin.patterns.expected.*
import org.csenseoss.kotlin.tests.assertions.*
import org.csenseoss.kotlin.tests.assertions.comparable.*
import org.csenseoss.kotlin.tests.assertions.general.*
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