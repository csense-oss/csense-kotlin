package org.csenseoss.kotlin.specificExtensions.string.file.operations

import org.csenseoss.kotlin.specificExtensions.string.file.fileExtensions
import org.csenseoss.kotlin.tests.assertions.primitives.charSequence.assert
import kotlin.test.Test

class WithoutFileExtensionTest {
    @Test
    fun stringFileWithoutFileExtension() {
        "".fileExtensions.withoutFileExtension().assert("")
        "test".fileExtensions.withoutFileExtension().assert("test")
        "random string with some fun".fileExtensions.withoutFileExtension().assert("random string with some fun")
        "test.asd".fileExtensions.withoutFileExtension().assert("test")
        "qwerty.xml".fileExtensions.withoutFileExtension().assert("qwerty")
        "qwerty.xml.js".fileExtensions.withoutFileExtension().assert("qwerty.xml")
    }
}