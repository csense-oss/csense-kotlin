package csense.kotlin.extensions.collections.array

import csense.kotlin.test.assertions.*
import kotlin.test.*

class SharedArrayTests {

    @Test
    fun testFillArray() {
        val count = 10
        var runningCounter = 0

        fillArray(count, 5) { index, value ->
            index.assert(runningCounter, "index and the running counter should be equal.")
            runningCounter += 1
            value.assert(5, "should be the initial value")
        }
        runningCounter.assert(10, "should run each count, starting from 0")

        fillArray(0, 0) { _, _ ->
            failTest("should not run when count is empty / 0")
        }
    }
}