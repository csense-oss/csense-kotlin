package org.csenseoss.kotlin.extensions.primitives.general

import org.csenseoss.kotlin.tests.assertions.general.*
import org.csenseoss.kotlin.tests.assertions.primitives.charSequence.*
import org.junit.jupiter.api.*

class GeneralJvmTest {

    @Test
    fun typeTest() {
        val x: Class<MyTestClass> = type()
        x.assertIs<Class<MyTestClass>>()
        x.simpleName.assert(MyTestClass::class.simpleName!!)
    }
}

private class MyTestClass