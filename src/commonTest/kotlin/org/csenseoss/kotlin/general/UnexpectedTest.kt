package org.csenseoss.kotlin.general

import org.csenseoss.kotlin.tests.assertions.*
import org.csenseoss.kotlin.tests.assertions.exceptions.*
import kotlin.test.*

class UnexpectedTest {
    @Test
    fun unexpected() {
        assertThrowsCause<UnexpectedException, RuntimeException> {
            unexpected("message", RuntimeException())
        }
    }
}