package csense.kotlin.general

import csense.kotlin.tests.assertions.*
import kotlin.test.*

class NotImplementedTest {
    @Test
    fun notImplemented(): Unit = assertThrows<NotImplementedError>{
        notImplemented("my bad")
    }
}