package org.csenseoss.kotlin.general

import org.csenseoss.kotlin.tests.assertions.exceptions.*
import org.csenseoss.kotlin.tests.assertions.primitives.charSequence.*
import kotlin.test.*

class NotImplementedTest {
    @Test
    fun notImplemented(): Unit {
        assertThrows<NotImplementedError>(
            testCode = {
                notImplemented(reason = "reason")
            },
            validateThrows = { it: NotImplementedError ->
                it.message.assert("reason")
            }
        )
    }
}