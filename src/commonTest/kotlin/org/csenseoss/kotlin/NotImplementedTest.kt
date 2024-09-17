package org.csenseoss.kotlin

import csense.kotlin.tests.assertions.*
import org.csenseoss.kotlin.general.*
import kotlin.test.*


class NotImplementedTest {
    @Test
    fun notImplementedTest() = assertThrows<NotImplementedError> {
        notImplemented()
    }
}