package org.csenseoss.kotlin.extensions.general

import org.csenseoss.kotlin.tests.assertions.*
import org.csenseoss.kotlin.tests.assertions.comparable.*
import org.csenseoss.kotlin.tests.assertions.general.*
import kotlin.test.*

class InvokeIsInstanceTest {
    @Test
    fun anyInvokeIsInstanceActionUnit() {
        "test".invokeIsInstance<Int, Any> { _: Int ->
            failTest("should not be called")
        }
        assertCalled { didCall: () -> Unit ->
            "testString".invokeIsInstance<String, Any> { it: String ->
                it.assert("testString")
                didCall()
            }
        }
    }


}