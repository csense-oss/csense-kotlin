package org.csenseoss.kotlin.extensions.general

import csense.kotlin.tests.assertions.*
import kotlin.test.*

class KClassTest {
    @Test
    fun simpleNameOrUnknown() {
        KClassTest::class.simpleNameOrUnknown.assert(KClassTest::class.simpleName!!)
        val anonymous = object {
        }
        anonymous::class.simpleNameOrUnknown.assert(value = "<unknown class type>", message = "does not have a name")
    }
}