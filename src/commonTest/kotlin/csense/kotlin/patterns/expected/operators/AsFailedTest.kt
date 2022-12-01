package csense.kotlin.patterns.expected.operators

import csense.kotlin.patterns.expected.*
import kotlin.test.*

class AsFailedTest {
    @Test
    fun success(){
        Expected.Success(42).asExpectedValue<Int, Int>().asFailed().assertFailedWith(42)
    }

    @Test
    fun failed(){
        Expected.Failed(42).asExpectedValue<Int, Int>().asFailed().assertFailedWith(42)
    }
}