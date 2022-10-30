package csense.kotlin.patterns.expected.operators

import csense.kotlin.patterns.expected.*
import csense.kotlin.tests.assertions.*
import kotlin.test.*

class IsFailedTest {
    @Test
    fun successShouldNotBeFailed() {
        Expected.Success(42).asExpected().isFailed().assertFalse()
        Expected.Success("hmm").asExpected().isFailed().assertFalse()
        Expected.Success(Exception("ex")).asExpected().isFailed().assertFalse()
    }

    @Test
    fun failedShouldBeFailed() {
        Expected.Failed(42).asExpected().isFailed().assertTrue()
        Expected.Failed("hmm").asExpected().isFailed().assertTrue()
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