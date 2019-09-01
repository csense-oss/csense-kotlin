package csense.kotlin.extensions

import csense.kotlin.test.assertions.*
import kotlinx.coroutines.*
import org.junit.jupiter.api.Test

internal class TimingExtensionsKtTest {

    @Test
    fun testLogMeasureTimeInMillis() {
        var logCount = 0
        logMeasureTimeInMillis("Timing", { _, _, _ -> logCount += 1 }) { }
        logCount.assert(1)

        val constant = logMeasureTimeInMillis("Timing", { _, _, _ -> logCount += 1 }) { 42 }
        constant.assert(42)
        logCount.assert(2)

    }

    @Test
    fun testMeasureTimeMillisResult() = runBlocking {
        val empty = measureTimeMillisResult { }
        empty.first.assertLessThan(100L, "should take no time to run.. ")

        val constant = measureTimeMillisResult { 42 }
        constant.first.assertLessThan(100L, "should be fast")
        constant.second.assert(42, "should give correct value out")

        val wait100Ms = measureTimeMillisResult { delay(100);"42" }
        wait100Ms.first.assertLargerOrEqualTo(100L, "should take at least the given time we wait.")
        wait100Ms.second.assert("42")
    }
}