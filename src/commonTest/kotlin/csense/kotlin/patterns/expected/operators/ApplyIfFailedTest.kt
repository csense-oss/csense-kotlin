package csense.kotlin.patterns.expected.operators

import csense.kotlin.patterns.expected.*
import csense.kotlin.tests.assertions.*
import kotlin.test.*

class ApplyIfFailedTest {
    @Test
    fun failedShouldBeCall(): Unit = assertCalled { shouldBeCalled: () -> Unit ->
        Expected.Failed(error = 42).applyIfFailed {
            error.assert(expected = 42)
            shouldBeCalled()
        }
    }

    @Test
    fun successShouldNotBeCall() {
        Expected.Success(value = 42).applyIfFailed {
            shouldNotBeCalled()
        }
    }

}