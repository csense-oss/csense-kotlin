package org.csenseoss.kotlin.specificExtensions.string.conversion

import org.csenseoss.kotlin.tests.assertions.collections.array.typed.short.assert
import org.csenseoss.kotlin.tests.assertions.general.assertNull
import org.csenseoss.kotlin.tests.assertions.primitives.boolean.assertFalse
import kotlin.test.Test

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