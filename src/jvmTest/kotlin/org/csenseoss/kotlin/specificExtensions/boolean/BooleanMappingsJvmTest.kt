package org.csenseoss.kotlin.specificExtensions.boolean

import csense.kotlin.tests.assertions.*
import org.csenseoss.kotlin.specificExtensions.boolean.*
import org.junit.jupiter.api.*

class BooleanMappingsJvmTest {
    @Test
    fun booleanMappingsToNewlineOrEmpty() {
        false.mappings.toNewlineOrEmpty().assert("")
        true.mappings.toNewlineOrEmpty().assert(System.lineSeparator())
    }
}