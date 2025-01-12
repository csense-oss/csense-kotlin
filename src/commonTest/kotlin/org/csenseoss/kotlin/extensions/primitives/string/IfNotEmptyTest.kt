package org.csenseoss.kotlin.extensions.primitives.string

import org.csenseoss.kotlin.tests.assertions.primitives.charSequence.*
import kotlin.test.*

class IfNotEmptyTest {
    @Test
    fun ifNotEmpty() {
        "".ifNotEmpty { "asd" }.assert("")
        " ".ifNotEmpty { "qwe" }.assert("qwe")
        "abc".ifNotEmpty { "123" }.assert("123")
    }
}