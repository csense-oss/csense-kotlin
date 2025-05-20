package org.csenseoss.kotlin.extensions.general

import org.csenseoss.kotlin.tests.assertions.general.*
import org.csenseoss.kotlin.tests.assertions.primitives.charSequence.*
import kotlin.test.*

class ValueOrTest {

    @Test
    fun nullable(): Unit = assertCalled { shouldBeCalled: () -> Unit ->
        val string: String? = null
        string.valueOr {
            shouldBeCalled()
            return@assertCalled
        }
        failTest("should never get here")
    }

    @Test
    fun value() {
        val string: String? = "test"
        val value: String = string.valueOr {
            shouldNotBeCalled()
        }
        value.assert("test")
    }
}