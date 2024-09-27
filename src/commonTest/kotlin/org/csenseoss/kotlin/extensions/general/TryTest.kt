package org.csenseoss.kotlin.extensions.general

import csense.kotlin.tests.assertions.*
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