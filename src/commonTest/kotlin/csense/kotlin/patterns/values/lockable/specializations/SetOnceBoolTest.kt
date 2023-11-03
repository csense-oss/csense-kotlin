package csense.kotlin.patterns.values.lockable.specializations

import csense.kotlin.tests.assertions.*
import kotlin.test.*

class SetOnceBoolTest {

    @Test
    fun testLocksAfterFirstSet() {
        val bool = SetOnceBool(initialValue = false)
        bool.isLocked().assertFalse()
        bool.value.assert(false)
        bool.value = true
        bool.value.assert(true)
        bool.value = false
        bool.value.assert(true)
        bool.isLocked().assertTrue()
    }

}