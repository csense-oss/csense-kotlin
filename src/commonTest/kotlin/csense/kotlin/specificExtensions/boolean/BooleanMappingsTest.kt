package csense.kotlin.specificExtensions.boolean

import csense.kotlin.tests.assertions.*
import kotlin.test.*

class BooleanMappingsTest {
    @Test
    fun booleanMappings() {
        val first = true.mappings
        val second = false.mappings
        (first != second).assertTrue("should have different instances")
    }
}