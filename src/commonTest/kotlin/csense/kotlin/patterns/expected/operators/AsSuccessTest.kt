package csense.kotlin.patterns.expected.operators

import csense.kotlin.patterns.expected.*
import kotlin.test.*

class AsSuccessTest {
    @Test
    fun success(){
        Expected.Success(42).asExpectedValue<Int, Int>().asSuccess().assertSuccessWith(42)
    }

    @Test
    fun failed(){
        Expected.Failed(42).asExpectedValue<Int, Int>().asSuccess().assertSuccessWith(42)
    }
}