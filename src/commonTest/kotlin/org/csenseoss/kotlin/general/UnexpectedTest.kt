package org.csenseoss.kotlin.general

import csense.kotlin.tests.assertions.*
import org.csenseoss.kotlin.general.*
import kotlin.test.*

class UnexpectedTest {
    @Test
    fun unexpected() {
        assertThrowsCause<UnexpectedException, RuntimeException> {
            unexpected("message", RuntimeException())
        }
    }
}