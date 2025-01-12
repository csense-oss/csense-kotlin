package org.csenseoss.kotlin.patterns.expected.operators

import org.csenseoss.kotlin.patterns.expected.*
import org.csenseoss.kotlin.tests.assertions.*
import org.csenseoss.kotlin.tests.assertions.comparable.*
import org.csenseoss.kotlin.tests.assertions.general.*
import kotlin.test.*

class SuccessValueOrNullTest {
    @Test
    fun successValueOrNull() {
        Expected.Failed(42).asExpected().successValueOrNull().assertNull()
        Expected.Success(42).asExpected().successValueOrNull().assert(42)
    }
}