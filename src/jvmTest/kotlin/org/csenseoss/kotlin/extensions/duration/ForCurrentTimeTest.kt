package org.csenseoss.kotlin.extensions.duration

import csense.kotlin.tests.assertions.*
import org.junit.jupiter.api.*
import kotlin.time.*
import kotlin.time.Duration.Companion.seconds

class ForCurrentTimeTest {
    @Test
    fun isApproximatelyNow(){
        val now: Long = System.currentTimeMillis()
        val currentTime: Duration = Duration.forCurrentTime()
        currentTime.inWholeMilliseconds.assertLargerOrEqualTo(now)
        currentTime.inWholeMilliseconds.assertLessThan(now + 2.seconds.inWholeMilliseconds)
    }
}