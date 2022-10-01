package csense.kotlin.patterns.expected

import csense.kotlin.tests.assertions.*
import kotlin.test.*

class SuccessValueOrNullTest {
    @Test
    fun successValueOrNull() {
        Expected.Failed(42).asExpected().successValueOrNull().assertNull()
        Expected.Success(42).asExpected().successValueOrNull().assert(42)
    }
}