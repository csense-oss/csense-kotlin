package csense.kotlin.patterns.values.lifecycle

import csense.kotlin.tests.assertions.*
import kotlin.test.*

class LifecycleValueTest {


    @Test
    fun value() = assertCalled { shouldBeCalled: () -> Unit ->
        LifecycleValue(
            createValue = {
                shouldBeCalled()
                "test"
            },
            onResetValue = { shouldNotBeCalled() }
        ).value.assert("test")
    }

    @Test
    fun isValuePresent() {
        LifecycleValue(
            createValue = {
                "test"
            },
            onResetValue = { shouldNotBeCalled() }
        ).isValuePresent().assertTrue("should be created eagerly")
    }


    @Test
    fun reset() = assertCalled { shouldBeCalled: () -> Unit ->
        val value = LifecycleValue(
            createValue = {
                "test"
            },
            onResetValue = { shouldBeCalled() }
        )
        value.reset()
        value.isValuePresent().assertFalse("should be reset")
    }

    @Test
    fun canRecreateAfterReset() {
        var valueCreationCounter = 0
        var resetCounter = 0
        val value = LifecycleValue(
            createValue = {
                valueCreationCounter += 1
                valueCreationCounter
            },
            onResetValue = {
                resetCounter += 1
            }
        )
        value.reset()
        value.value.assert(2, message = "should be created eagerly, thus 1 + after the reset = 2")
        resetCounter.assert(1)
    }
}