package org.csenseoss.kotlin.patterns.expected.operators

import csense.kotlin.tests.assertions.*
import org.csenseoss.kotlin.patterns.expected.*
import kotlin.test.*

class AsSuccessTest {
    @Test
    fun success() {
        Expected.Success(value = 42).asExpectedValue<Int, Int>().asSuccess().assertSuccessWith(42)
    }

    @Test
    fun failed() {
        Expected.Failed(error = 42).asExpectedValue<Int, Int>().asSuccess().assertSuccessWith(42)
    }

    @Test
    fun expectedValueNothingAsSuccess() {
        val x: Expected<Int, Nothing> = Expected.Success(value = 42)
        x.asSuccess.value.assert(42)
    }
}