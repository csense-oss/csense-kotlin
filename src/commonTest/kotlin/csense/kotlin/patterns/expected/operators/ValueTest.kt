package csense.kotlin.patterns.expected.operators

import csense.kotlin.patterns.expected.*
import csense.kotlin.tests.assertions.*
import kotlin.test.*

class ValueTest {

    @Test
    fun expectedTTValue() {
        Expected.Failed(42).asExpectedValue<Int, Int>().value().assert(42)
        Expected.Success(42).asExpectedValue<Int, Int>().value().assert(42)
    }

    @Test
    fun expectedValueNothingValue() {
        val failed: Expected.Success<Int> = Expected.Success(42)
        failed.value.assertIs<Int>()
        failed.value.assert(42)
    }

}