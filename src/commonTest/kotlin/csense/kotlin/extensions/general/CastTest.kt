package csense.kotlin.extensions.general

import csense.kotlin.tests.assertions.*
import kotlin.test.*

class CastTest {
    @Test
    fun cast() {
        val test: Any = ""
        test.cast<String>().assertNotNull()
        test.cast<Number>().assertNull()

    }
}