package org.csenseoss.kotlin.patterns.expected.operators

import org.csenseoss.kotlin.patterns.expected.*
import org.csenseoss.kotlin.tests.assertions.*
import org.csenseoss.kotlin.tests.assertions.general.*
import org.csenseoss.kotlin.tests.assertions.primitives.boolean.*
import kotlin.test.*

class IsSuccessTest {

    @Test
    fun successShouldBeSuccess() {
        Expected.Success(value = 42).asExpected().isSuccess().assertTrue()
        Expected.Success(value = "hmm").asExpected().isSuccess().assertTrue()
        Expected.Success(Exception("ex")).asExpected().isSuccess().assertTrue()
    }

    @Test
    fun failedShouldNotBeSuccess() {
        Expected.Failed(error = 42).asExpected().isSuccess().assertFalse()
        Expected.Failed(error = "hmm").asExpected().isSuccess().assertFalse()
        Expected.Failed(Exception("ex")).asExpected().isSuccess().assertFalse()
    }

    @Test
    fun contractsShouldBeRespected() {
        val forContractTest: Expected<String, Int> = expected { "".asSuccess() }
        if (forContractTest.isSuccess()) {
            forContractTest.value.assertIs<String>()
        } else {
            forContractTest.error.assertIs<Int>()
        }
    }
}