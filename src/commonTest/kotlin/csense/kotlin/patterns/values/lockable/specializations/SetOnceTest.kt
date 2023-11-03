package csense.kotlin.patterns.values.lockable.specializations

import csense.kotlin.tests.assertions.*
import kotlin.test.*

class SetOnceTest {
    @Test
    fun locksAfterFirstSet() {
        val setOnce: SetOnce<String> = SetOnce(initialValue = "test")
        setOnce.isLocked().assertFalse()
        setOnce.value.assert("test")
        setOnce.value = "true"
        setOnce.value.assert("true")
        setOnce.value = "false"
        setOnce.value.assert("true")
        setOnce.isLocked().assertTrue()
    }
}