package org.csenseoss.kotlin.specificExtensions.string

import org.csenseoss.kotlin.tests.assertions.collections.array.typed.short.*
import org.csenseoss.kotlin.tests.assertions.general.*
import org.csenseoss.kotlin.tests.assertions.primitives.boolean.*
import kotlin.test.*

class StringConversionTest {

    @Test
    fun stringConversion() {
        ("".conversion == "a".conversion).assertFalse("should not be same instances.")
        ("a".conversion == "".conversion).assertFalse("should not be same instances.")
    }


    @Test
    fun fromHexStringToByteArray() {
        "".conversion.fromHexStringToByteArray().assertNull("not hex")
        " ".conversion.fromHexStringToByteArray().assertNull("not hex")

        val valid: ShortArray? = "0xFF".conversion.fromHexStringToByteArray()
        valid!!.assert(shortArrayOf(0xFF))
    }
}