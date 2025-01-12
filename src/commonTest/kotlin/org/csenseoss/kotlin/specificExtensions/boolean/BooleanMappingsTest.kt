package org.csenseoss.kotlin.specificExtensions.boolean

import org.csenseoss.kotlin.tests.assertions.*
import org.csenseoss.kotlin.tests.assertions.primitives.boolean.*
import kotlin.test.*

class BooleanMappingsTest {
    @Test
    fun booleanMappings() {
        val first: BooleanMappings = true.mappings
        val second: BooleanMappings = false.mappings
        (first != second).assertTrue("should have different instances")
    }
}