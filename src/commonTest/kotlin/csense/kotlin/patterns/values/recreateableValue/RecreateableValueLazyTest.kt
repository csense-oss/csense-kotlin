package csense.kotlin.patterns.values.recreateableValue

import csense.kotlin.tests.assertions.*
import kotlin.test.*

class RecreateableValueLazyTest {

    class Value {
        @Test
        fun initialValueIsPreserved() = assertCalled { shouldBeCalled ->
            RecreateableValue {
                shouldBeCalled()
                "test"
            }.value.assert("test")
        }

        @Test
        fun valueIsRecreatedAfterReset() {
            var counter = 0
            val value = RecreateableValue {
                counter += 1
                counter
            }
            value.value.assert(1)
            value.reset()
            value.value.assert(2)
        }
    }


    @Test
    fun reset() {
        val value = RecreateableValue {
            "test"
        }
        value.reset()
        value.isValuePresent().assertFalse(message = "reset should remove any present values")
    }

    @Test
    fun isValuePresent() {
        RecreateableValue {
            "test"
        }.isValuePresent().assertTrue(message = "Should construct eagerly")
    }
}