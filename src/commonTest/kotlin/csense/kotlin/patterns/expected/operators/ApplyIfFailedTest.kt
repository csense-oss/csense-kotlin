package csense.kotlin.patterns.expected.operators

import csense.kotlin.patterns.expected.*
import csense.kotlin.tests.assertions.*
import kotlin.test.*

class ApplyIfFailedTest {
    @Test
    fun failedShouldNotCall() = assertCalled { shouldBeCalled ->
        Expected.Failed(42).applyIfFailed {
            shouldBeCalled()
        }
    }

    @Test
    fun successShouldCall() {
        Expected.Success(42).applyIfFailed {
            shouldNotBeCalled()
        }
    }

}