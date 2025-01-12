@file:Suppress("unused")

package org.csenseoss.kotlin.extensions.general

import org.csenseoss.kotlin.tests.assertions.comparable.*
import org.csenseoss.kotlin.tests.assertions.general.*
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