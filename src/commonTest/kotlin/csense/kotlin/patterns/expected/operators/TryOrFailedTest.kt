package csense.kotlin.patterns.expected.operators

import csense.kotlin.patterns.expected.*
import kotlin.test.*

class TryOrFailedTest {
    @Test
    fun throwing() {
        val exception = RuntimeException("wee")
        val result: Expected<String, Throwable> = tryOrFailed {
            throw exception
        }
        result.assertFailedWithByEquals(exception)
    }

    @Test
    fun returning() {
        val result: Expected<String, Throwable> = tryOrFailed {
            "success"
        }
        result.assertSuccessWith("success")
    }
}