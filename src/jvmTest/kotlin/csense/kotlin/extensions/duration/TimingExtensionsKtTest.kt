package csense.kotlin.extensions.duration

import csense.kotlin.logger.*
import csense.kotlin.tests.assertions.*
import kotlinx.coroutines.*
import org.junit.jupiter.api.*

internal class TimingExtensionsKtTest {

    @Test
    fun testLogMeasureTimeInMillis() {
        var logCount = 0
        logMeasureTimeInMillis("Timing", CLLogFunction { _, _, _, _, _ -> logCount += 1 }) { }
        logCount.assert(1)

        val constant = logMeasureTimeInMillis("Timing", CLLogFunction { _, _, _, _, _ -> logCount += 1 }) { 42 }
        constant.assert(42)
        logCount.assert(2)

    }

    @Test
    fun measureTimeResult() = runBlocking {
        val empty = measureTimeResult { }
        empty.first.inWholeMilliseconds.assertLessThan(100L, "should take no time to run.. ")

        val constant = measureTimeResult { 42 }
        constant.first.inWholeMilliseconds.assertLessThan(100L, "should be fast")
        constant.second.assert(42, "should give correct value out")

        val wait = measureTimeResult {
            delay(50) //actually needs to be a "real" delay to "measure" the time. (cannot as of writing use runTest)
            "42"
        }
        wait.first.inWholeMilliseconds.assertLargerOrEqualTo(50L, "should take at least the given time we wait.")
        wait.second.assert("42")
    }
}