package csense.kotlin.specificExtensions.string

import csense.kotlin.tests.assertions.*
import kotlin.test.*

class StringConversionTest {
    
    @Test
    fun stringConversion() {
        ("".conversion == "".conversion).assertFalse("should not be same instances.")
    }


    @Test
    fun fromHexStringToByteArray() {
        "".conversion.fromHexStringToByteArray().assertNull("not hex")
        " ".conversion.fromHexStringToByteArray().assertNull("not hex")
        "0xFF".conversion.fromHexStringToByteArray().assertNotNullApply {
            assertSize(1)
            first().assert(0xFF)
        }
    }
}