package org.csenseoss.kotlin.extensions.general

import org.csenseoss.kotlin.tests.assertions.*
import org.csenseoss.kotlin.tests.assertions.primitives.charSequence.*
import kotlin.test.*

class KClassTest {
    @Test
    fun simpleNameOrUnknown() {
        KClassTest::class.simpleNameOrUnknown.assert(KClassTest::class.simpleName!!)
        val anonymous = object {
        }
        anonymous::class.simpleNameOrUnknown.assert(expected = "<unknown class type>", message = "does not have a name")
    }
}