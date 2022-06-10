package csense.kotlin.performance.boolean

import csense.kotlin.specificExtensions.boolean.*
import csense.kotlin.tests.assertions.*
import org.junit.jupiter.api.*

class BooleanMappingsJvmTest {
    @Test
    fun toNewlineOrEmpty() {
        BooleanMappings(false).toNewlineOrEmpty().assert("")
        BooleanMappings(true).toNewlineOrEmpty().assert(System.lineSeparator())
    }
}