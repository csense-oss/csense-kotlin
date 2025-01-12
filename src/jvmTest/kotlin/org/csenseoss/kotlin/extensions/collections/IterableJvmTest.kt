package org.csenseoss.kotlin.extensions.collections

import org.csenseoss.kotlin.tests.assertions.primitives.charSequence.*
import org.junit.jupiter.api.*

class IterableJvmTest {
    @Nested
    inner class IterableTJoinToStringNewLine {
        @Test
        fun empty() {
            listOf<String>().joinToStringNewLine().assertEmpty()
        }

        @Test
        fun single() {
            listOf("test").joinToStringNewLine().assert("test")
        }

        @Test
        fun multiple() {
            listOf("test", "1234").joinToStringNewLine()
                .assert("test${System.lineSeparator()}1234")
        }
    }
}