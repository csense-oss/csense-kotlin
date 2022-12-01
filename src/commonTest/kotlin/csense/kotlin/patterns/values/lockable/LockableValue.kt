package csense.kotlin.patterns.values.lockable

import csense.kotlin.tests.assertions.*
import kotlin.test.*

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

    @Test
    fun lock() {
        val lockableValue = LockableValue(1, "")
        lockableValue.isLocked().assertFalse()
        lockableValue.lock()
        lockableValue.isLocked().assertTrue()
        lockableValue.value = "test"
        lockableValue.value.assert("")
    }


    @Test
    fun isLocked() {
        val unlocked = LockableValue(1, "")
        unlocked.isLocked().assertFalse()
        val locked = LockableValue(0, "")
        locked.isLocked().assertTrue()
    }

    @Test
    fun isUnlocked() {
        val unlocked = LockableValue(1, "")
        unlocked.isUnlocked().assertTrue()
        val locked = LockableValue(0, "")
        locked.isUnlocked().assertFalse()
    }

}