package org.csenseoss.kotlin.datastructures.cachedBy.timeout

import csense.kotlin.tests.assertions.*
import kotlin.test.*
import kotlin.time.*
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.seconds

class CachedItemByTimeoutTest {
    @Test
    fun isTimedOut() {
        val testTime = TestTimeSource()
        val item: CachedItemByTimeout<String> = cachedItemByOneMinuteTimeout(testTime)
        item.isTimedOut().assertFalse()
        testTime += 50.seconds
        item.isTimedOut().assertFalse()
        testTime += 10.seconds
        item.isTimedOut().assertTrue()
        testTime += 1.hours
        item.isTimedOut().assertTrue()
    }

    @Test
    fun isNotTimedOut() {
        val testTime = TestTimeSource()
        val item: CachedItemByTimeout<String> = cachedItemByOneMinuteTimeout(testTime)
        item.isNotTimedOut().assertTrue()
        testTime += 50.seconds
        item.isNotTimedOut().assertTrue()
        testTime += 10.seconds
        item.isNotTimedOut().assertFalse()
        testTime += 1.hours
        item.isNotTimedOut().assertFalse()
    }

    private fun cachedItemByOneMinuteTimeout(
        testTime: TestTimeSource
    ): CachedItemByTimeout<String> {
        return CachedItemByTimeout(
            timeout = 1.minutes,
            start = testTime.markNow(),
            value = "test"
        )
    }
}