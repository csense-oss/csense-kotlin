@file:Suppress("unused")

package csense.kotlin.stdlib

import csense.kotlin.tests.assertions.assert
import kotlin.test.Test

class LockableValueTest {

    class Value {

        @Test
        fun testValue() {
            val lockableValue = LockableValue(1, "")
            lockableValue.value.assert("")
            lockableValue.value = "test"
            lockableValue.value.assert("test")
            lockableValue.value = "test2"
            lockableValue.value.assert("test", "should not allow updating more times than specified")
        }

        @Test
        fun testValueZeroCount() {
            val lockableValue = LockableValue(0, "")
            lockableValue.value.assert("")
            lockableValue.value = "test"
            lockableValue.value.assert("")
        }
    }
}
