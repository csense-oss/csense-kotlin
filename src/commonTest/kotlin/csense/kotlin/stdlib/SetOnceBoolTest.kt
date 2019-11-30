package csense.kotlin.stdlib

import csense.kotlin.tests.assertions.assert
import kotlin.test.Test

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