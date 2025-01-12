package org.csenseoss.kotlin.specificExtensions.boolean

import org.csenseoss.kotlin.tests.assertions.primitives.charSequence.*
import org.junit.jupiter.api.*

class BooleanMappingsJvmTest {
    @Test
    fun booleanMappingsToNewlineOrEmpty() {
        false.mappings.toNewlineOrEmpty().assert("")
        true.mappings.toNewlineOrEmpty().assert(System.lineSeparator())
    }
}