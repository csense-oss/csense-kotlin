@file:Suppress("unused")

package csense.kotlin.extensions.general

import csense.kotlin.tests.assertions.*
import kotlin.test.*

class ApplyIfTest {
    class TApplyIf {
        @Test
        fun shouldNotApply() {
            "value".applyIf(shouldApply = false) {
                shouldNotBeCalled()
            }.assert("value")
        }

        @Test
        fun shouldApply(): Unit = assertCalled { shouldBeCalled: () -> Unit ->
            "test".applyIf(shouldApply = true) {
                shouldBeCalled()
                assert("test")
            }.assert("test")
        }
    }

}