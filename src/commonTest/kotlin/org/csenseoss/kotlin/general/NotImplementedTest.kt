package org.csenseoss.kotlin.general

import org.csenseoss.kotlin.tests.assertions.*
import org.csenseoss.kotlin.tests.assertions.exceptions.*
import kotlin.test.*

class NotImplementedTest {
    @Test
    fun notImplemented(): Unit = assertThrows<NotImplementedError>{
        notImplemented("my bad")
    }
}