package csense.kotlin.patterns.values.lockable

import csense.kotlin.tests.assertions.*
import kotlin.test.*

class SetOnceBoolTest {

    @Test
    fun testUpdateCount() {
        val bool = SetOnceBool(false)
        bool.value.assert(false)
        bool.value = true
        bool.value.assert(true)
        bool.value = false
        bool.value.assert(true)
    }

}