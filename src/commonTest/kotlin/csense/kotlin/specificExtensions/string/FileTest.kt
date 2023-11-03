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

        "test.a".fileExtensions.fileExtension().assert("a")
        "test.a.".fileExtensions.fileExtension().assertNull("since the text ends in . then there are no extension.")
        "test.a.b".fileExtensions.fileExtension().assert("b")
        "test..a".fileExtensions.fileExtension().assert("a")

        //more real life examples
        "test.xml".fileExtensions.fileExtension().assert("xml")
        "test.\$java".fileExtensions.fileExtension().assert("\$java")
        "test.\"xml".fileExtensions.fileExtension().assert("\"xml")
    }

    @Test
    fun stringFileWithFileExtension() {
        "".fileExtensions.withFileExtension("test").assert(".test")
        ".".fileExtensions.withFileExtension("test").assert(".test")
        ".t".fileExtensions.withFileExtension("test").assert(".test")
        "t.t".fileExtensions.withFileExtension("test").assert("t.test")
        "i.am.a.long.string".fileExtensions.withFileExtension("long.string").assert("i.am.a.long.long.string")
    }

    @Test
    fun stringFileWithoutFileExtension() {
        "".fileExtensions.withoutFileExtension().assert("")
        "test".fileExtensions.withoutFileExtension().assert("test")
        "random string with some fun".fileExtensions.withoutFileExtension().assert("random string with some fun")
        "test.asd".fileExtensions.withoutFileExtension().assert("test")
        "qwerty.xml".fileExtensions.withoutFileExtension().assert("qwerty")
        "qwerty.xml.js".fileExtensions.withoutFileExtension().assert("qwerty.xml")

    }

    @Test
    fun stringFileExtensions() {
        val x: StringFile = "".fileExtensions
        val y: StringFile = " ".fileExtensions
        (x != y).assertTrue("should have unique instances")
    }
}