package org.csenseoss.kotlin.extensions.general

import org.csenseoss.kotlin.tests.assertions.*
import org.csenseoss.kotlin.tests.assertions.general.*
import org.csenseoss.kotlin.tests.assertions.primitives.charSequence.*
import kotlin.test.*

class TryTest {
    @Test
    fun tryOrNull() {
        val notThrowing: String? = tryOrNull {
            "test"
        }
        notThrowing.assert("test")

        val throwing: String? = tryOrNull {
            throw RuntimeException("bla")
        }
        throwing.assertNull()
    }
}