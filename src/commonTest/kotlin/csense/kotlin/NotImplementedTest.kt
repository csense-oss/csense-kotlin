package csense.kotlin

import csense.kotlin.general.*
import csense.kotlin.tests.assertions.*
import kotlin.test.*


class NotImplementedTest {
    @Test
    fun notImplementedTest() = assertThrows<NotImplementedError> {
        notImplemented()
    }
}