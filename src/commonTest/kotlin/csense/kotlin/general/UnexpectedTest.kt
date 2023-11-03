package csense.kotlin.general

import csense.kotlin.tests.assertions.*
import kotlin.test.*

class UnexpectedTest {
    @Test
    fun unexpected() {
        assertThrowsCause<UnexpectedException, RuntimeException> {
            unexpected("message", RuntimeException())
        }
    }
}