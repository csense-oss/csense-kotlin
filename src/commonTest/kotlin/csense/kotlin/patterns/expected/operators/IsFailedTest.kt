package csense.kotlin.patterns.expected.operators

import csense.kotlin.patterns.expected.*
import csense.kotlin.tests.assertions.*
import kotlin.test.*

class IsFailedTest {
    @Test
    fun successShouldNotBeFailed() {
        Expected.Success(value = 42).asExpected().isFailed().assertFalse()
        Expected.Success(value = "hmm").asExpected().isFailed().assertFalse()
        Expected.Success(Exception("ex")).asExpected().isFailed().assertFalse()
    }

    @Test
    fun failedShouldBeFailed() {
        Expected.Failed(error = 42).asExpected().isFailed().assertTrue()
        Expected.Failed(error = "hmm").asExpected().isFailed().assertTrue()
        Expected.Failed(Exception("ex")).asExpected().isFailed().assertTrue()
    }

    @Test
    fun contractsShouldBeRespected() {
        val forContractTest: Expected<String, Int> = expected { 42.asFailed() }
        if (forContractTest.isFailed()) {
            forContractTest.error.assertIs<Int>()
        } else {
            forContractTest.value.assertIs<String>()
        }
    }
}