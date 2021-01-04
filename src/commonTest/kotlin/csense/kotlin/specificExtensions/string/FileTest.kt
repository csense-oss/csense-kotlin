package csense.kotlin.specificExtensions.string

import csense.kotlin.tests.assertions.*
import kotlin.test.*

class FileTest {
    @Test
    fun stringFileFileExtension() {
        val noExtensionMessage = "there are no extensions in this string"
        "".fileExtensions.fileExtension().assertNull(noExtensionMessage)
        "test".fileExtensions.fileExtension().assertNull(noExtensionMessage)
        "test.".fileExtensions.fileExtension().assertNull(noExtensionMessage)
        "test..".fileExtensions.fileExtension().assertNull(noExtensionMessage)

        "test.a".fileExtensions.fileExtension().assertNotNullAndEquals("a")
        "test.a.".fileExtensions.fileExtension().assertNull("since the text ends in . then there are no extension.")
        "test.a.b".fileExtensions.fileExtension().assertNotNullAndEquals("b")
        "test..a".fileExtensions.fileExtension().assertNotNullAndEquals("a")

        //more real life examples
        "test.xml".fileExtensions.fileExtension().assertNotNullAndEquals("xml")
        "test.\$java".fileExtensions.fileExtension().assertNotNullAndEquals("\$java")
        "test.\"xml".fileExtensions.fileExtension().assertNotNullAndEquals("\"xml")

    }

    @Test
    fun stringFileRemoveFileExtension() {
        "".fileExtensions.removeFileExtension().assert("")
        "test".fileExtensions.removeFileExtension().assert("test")
        "random string with some fun".fileExtensions.removeFileExtension().assert("random string with some fun")
        "test.asd".fileExtensions.removeFileExtension().assert("test")
        "qwerty.xml".fileExtensions.removeFileExtension().assert("qwerty")
        "qwerty.xml.js".fileExtensions.removeFileExtension().assert("qwerty.xml")
    }
}