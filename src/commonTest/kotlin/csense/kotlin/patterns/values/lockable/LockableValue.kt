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
    fun lockWith() {
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

    @Test
    fun lockableValueTGetValue() {
        val value by LockableValue(0, "test")
        value.assert(value = "test", message = "should get real value")
    }

    @Test
    fun lockableValueTSetValue() {
        var locked by LockableValue(0, "test")
        locked.assert("test")
        locked = "test2"
        locked.assert(value = "test", message = "should still respect update count")
        var unlocked by LockableValue(1, "1234")
        unlocked.assert("1234")
        unlocked = "qwerty"
        unlocked.assert("qwerty")
        unlocked = "shouldFail"
        unlocked.assert(value = "qwerty", message = "should still respect update count")
    }

    @Test
    fun lockableValueTWhenUnlocked() {
        val locked = LockableValue(0, "")
        assertNotCalled {
            locked.whenUnlocked { it() }
        }
        val unlocked = LockableValue(1, "")
        assertCalled {
            unlocked.whenUnlocked { it() }
        }
    }

    @Test
    fun lockableValueTLockWithAction() {
        val locked = LockableValue(0, "")
        assertNotCalled {
            locked.lockWithAction(it)
        }
        val unlocked = LockableValue(1, "")
        assertCalled("should be allowed first time") {
            unlocked.lockWithAction(it)
        }
        assertNotCalled("should still lock after first use") {
            unlocked.lockWithAction(it)
        }
    }

    @Test
    fun lockableValueTIfLocked() {
        val locked = LockableValue(0, "")
        assertCalled {
            locked.ifLocked(it)
        }

        val unlocked = LockableValue(1, "")
        assertNotCalled("should still lock after first use") {
            unlocked.ifLocked(it)
        }
    }

    class LockableValueTLockOrReturn {

        @Test
        fun callsWhenUnlocked() {
            val unlocked = LockableValue(
                maxUpdateCount = 1,
                initialValue = "test",
                onUpdateRejected = { shouldNotBeCalled() }
            )
            unlocked.lockOrReturn {
                shouldNotBeCalled()
            }
            unlocked.isLocked().assertTrue()
        }

        @Test
        fun returnsWhenLocked() = assertCalled { shouldBeCalled ->
            val locked = LockableValue(
                maxUpdateCount = 1,
                initialValue = "test",
                onUpdateRejected = { shouldNotBeCalled() }
            )
            locked.lock()
            locked.lockOrReturn {
                shouldBeCalled()
                return@assertCalled
            }
            failTest("should not get here.")
        }

    }
}