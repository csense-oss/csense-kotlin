package csense.kotlin.patterns.expected.operators

import csense.kotlin.patterns.expected.*
import csense.kotlin.tests.assertions.*
import kotlin.test.*

class IsSuccessTest {

    @Test
    fun successShouldBeSuccess() {
        Expected.Success(42).asExpected().isSuccess().assertTrue()
        Expected.Success("hmm").asExpected().isSuccess().assertTrue()
        Expected.Success(Exception("ex")).asExpected().isSuccess().assertTrue()
    }

    @Test
    fun failedShouldNotBeSuccess() {
        Expected.Failed(42).asExpected().isSuccess().assertFalse()
        Expected.Failed("hmm").asExpected().isSuccess().assertFalse()
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