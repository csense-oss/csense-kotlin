package csense.kotlin.patterns.expected.operators

import csense.kotlin.patterns.expected.*
import csense.kotlin.tests.assertions.*
import kotlin.test.*

class ApplyIfSuccessTest {
    @Test
    fun failedShouldNotCall() {
        Expected.Failed(42).applyIfSuccess {
            shouldNotBeCalled()

        }
    }

    @Test
    fun successShouldCall() = assertCalled { shouldBeCalled ->
        Expected.Success(42).applyIfSuccess {
            shouldBeCalled()
        }
    }

}
