package org.csenseoss.kotlin.patterns.expected.operators

import org.csenseoss.kotlin.patterns.expected.*
import csense.kotlin.tests.assertions.*
import org.csenseoss.kotlin.patterns.expected.*
import org.csenseoss.kotlin.patterns.expected.operators.*
import kotlin.test.*

class ApplyIfSuccessTest {
    @Test
    fun failedShouldNotCall() {
        Expected.Failed(error = 42).applyIfSuccess {
            shouldNotBeCalled()

        }
    }

    @Test
    fun successShouldCall(): Unit = assertCalled { shouldBeCalled: () -> Unit ->
        Expected.Success(value = 42).applyIfSuccess {
            value.assert(expected = 42)
            shouldBeCalled()
        }
    }
}