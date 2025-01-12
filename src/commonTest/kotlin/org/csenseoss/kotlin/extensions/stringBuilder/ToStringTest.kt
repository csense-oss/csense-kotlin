package org.csenseoss.kotlin.extensions.stringBuilder

import org.csenseoss.kotlin.tests.assertions.*
import org.csenseoss.kotlin.tests.assertions.comparable.*
import kotlin.test.*

class ToStringTest {
    @Test
    fun toStringAndClear() {
        val builder = StringBuilder()
        builder.toStringAndClear().assert("")
        builder.length.assert(0)

        builder.append("test")
        builder.toStringAndClear().assert("test")
        builder.length.assert(0)
    }
}