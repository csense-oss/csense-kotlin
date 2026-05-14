package org.csenseoss.kotlin.specificExtensions.string.file

import org.csenseoss.kotlin.tests.assertions.primitives.boolean.*
import kotlin.test.*

class FileTest {
    @Test
    fun stringFileExtensions() {
        val x: StringFile = "".fileExtensions
        val y: StringFile = " ".fileExtensions
        (x != y).assertTrue("should have unique instances")
    }
}