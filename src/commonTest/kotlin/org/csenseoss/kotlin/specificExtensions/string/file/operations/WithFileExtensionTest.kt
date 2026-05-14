package org.csenseoss.kotlin.specificExtensions.string.file.operations

import org.csenseoss.kotlin.specificExtensions.string.file.fileExtensions
import org.csenseoss.kotlin.tests.assertions.primitives.charSequence.assert
import kotlin.test.Test

class WithFileExtensionTest {
    @Test
    fun stringFileWithFileExtension() {
        "".fileExtensions.withFileExtension("test").assert(".test")
        ".".fileExtensions.withFileExtension("test").assert(".test")
        ".t".fileExtensions.withFileExtension("test").assert(".test")
        "t.t".fileExtensions.withFileExtension("test").assert("t.test")
        "i.am.a.long.string".fileExtensions.withFileExtension("long.string").assert("i.am.a.long.long.string")
    }
}
