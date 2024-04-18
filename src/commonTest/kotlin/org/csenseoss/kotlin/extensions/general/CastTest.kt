package org.csenseoss.kotlin.extensions.general

import csense.kotlin.tests.assertions.*
import org.csenseoss.kotlin.extensions.general.*
import kotlin.test.*

class CastTest {
    @Test
    fun cast() {
        val test: Any = ""
        test.cast<String>().assertNotNull()
        test.cast<Number>().assertNull()

    }
}