package org.csenseoss.kotlin.extensions.general

import org.csenseoss.kotlin.tests.assertions.*
import org.csenseoss.kotlin.tests.assertions.comparable.*
import org.csenseoss.kotlin.tests.assertions.exceptions.*
import org.csenseoss.kotlin.tests.assertions.general.*
import kotlin.test.*

class CastTest {
    @Test
    fun cast() {
        val test: Any = ""
        test.cast<String>().assertNotNull()
        test.cast<Number>().assertNull()

    }

    @Test
    fun castOr() {
        val test: Any = "test"
        val string: String = test.castOr { "notAString" }
        string.assert("test")

        val number: Int = test.castOr { 42 }
        number.assert(42)
    }

    @Test
    fun castOrReturn() {
        val test: Any = "test"
        val string: String = test.castOrReturn { shouldNotBeCalled() }
        string.assert("test")

        assertThrows<Throwable> {
            test.castOrReturn { throw Exception("failed test") }
        }
    }
}