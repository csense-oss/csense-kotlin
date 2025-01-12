package org.csenseoss.kotlin

import org.csenseoss.kotlin.general.*
import org.csenseoss.kotlin.tests.assertions.exceptions.*
import kotlin.test.*


class NotImplementedTest {
    @Test
    fun notImplementedTest() = assertThrows<NotImplementedError> {
        notImplemented()
    }
}