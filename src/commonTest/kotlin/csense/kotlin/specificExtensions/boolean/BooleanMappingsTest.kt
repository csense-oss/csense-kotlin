package csense.kotlin.specificExtensions.boolean

import csense.kotlin.tests.assertions.*
import kotlin.test.*

class BooleanMappingsTest {
    @Test
    fun booleanMappings() {
        val first: BooleanMappings = true.mappings
        val second: BooleanMappings = false.mappings
        (first != second).assertTrue("should have different instances")
    }
}