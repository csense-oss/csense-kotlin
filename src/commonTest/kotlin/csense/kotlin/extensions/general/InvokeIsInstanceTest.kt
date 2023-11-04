package csense.kotlin.extensions.general

import csense.kotlin.tests.assertions.*
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