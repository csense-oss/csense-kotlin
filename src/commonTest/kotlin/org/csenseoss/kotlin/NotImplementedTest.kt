package org.csenseoss.kotlin

import org.csenseoss.kotlin.general.*
import csense.kotlin.tests.assertions.*
import org.csenseoss.kotlin.general.*
import kotlin.test.*


class NotImplementedTest {
    @Test
    fun notImplementedTest() = assertThrows<NotImplementedError> {
        notImplemented()
    }
}