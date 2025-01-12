package org.csenseoss.kotlin.patterns.values.lockable.specializations

import org.csenseoss.kotlin.tests.assertions.primitives.boolean.*
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