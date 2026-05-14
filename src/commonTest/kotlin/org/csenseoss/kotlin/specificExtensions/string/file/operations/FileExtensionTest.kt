package org.csenseoss.kotlin.specificExtensions.string.file.operations

import org.csenseoss.kotlin.specificExtensions.string.file.fileExtensions
import org.csenseoss.kotlin.tests.assertions.general.assertNull
import org.csenseoss.kotlin.tests.assertions.primitives.charSequence.assert
import kotlin.test.Test

class FileExtensionTest {
    @Test
    fun stringFileFileExtension() {
        val noExtensionMessage = "there are no extensions in this string"
        "".fileExtensions.fileExtension().assertNull(noExtensionMessage)
        "test".fileExtensions.fileExtension().assertNull(noExtensionMessage)
        "test.".fileExtensions.fileExtension().assertNull(noExtensionMessage)
        "test..".fileExtensions.fileExtension().assertNull(noExtensionMessage)

        "test.a".fileExtensions.fileExtension().assert("a")
        "test.a.".fileExtensions.fileExtension().assertNull("since the text ends in . then there are no extension.")
        "test.a.b".fileExtensions.fileExtension().assert("b")
        "test..a".fileExtensions.fileExtension().assert("a")

        //more real life examples
        "test.xml".fileExtensions.fileExtension().assert("xml")
        "test.\$java".fileExtensions.fileExtension().assert("\$java")
        "test.\"xml".fileExtensions.fileExtension().assert("\"xml")
    }
}