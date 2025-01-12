package org.csenseoss.kotlin.datastructures.cachedBy.cachedByConstructor.operations

import kotlinx.coroutines.*
import kotlinx.coroutines.test.*
import org.csenseoss.kotlin.datastructures.cachedBy.cachedByConstructor.*
import org.csenseoss.kotlin.datastructures.cachedBy.timeout.*
import org.csenseoss.kotlin.tests.assertions.*
import org.csenseoss.kotlin.tests.assertions.general.*
import org.csenseoss.kotlin.tests.assertions.primitives.boolean.*
import kotlin.test.*
import kotlin.time.Duration.Companion.days
import kotlin.time.Duration.Companion.nanoseconds

class ByTimeoutViaCurrentTimeTest {
    @Test
    fun alwaysTimedOut(): TestResult = runTest {
        assertCalled(times = 2) { shouldBeCalled: () -> Unit ->
            val cache: CachedByConstructor<CachedItemByTimeout<Int>> = CachedByConstructor.byTimeoutViaCurrentTime(
                getValue = {
                    shouldBeCalled()
                    42
                },
                timeout = 0.nanoseconds
            )
            cache.isCacheValid().assertFalse("is timed out")
            val cached: CachedItemByTimeout<Int> = cache.cachedOrGet()
            cached.isTimedOut().assertTrue()

            delay(1000)

            cache.getCachedValue().assertNull("should be timed out thus not valid / cached")

            val secondCache: CachedItemByTimeout<Int> = cache.cachedOrGet()
            secondCache.isTimedOut().assertTrue()
        }
    }

    @Test
    fun isAlwaysValid(): TestResult = runTest{
        assertCalled(times = 1) { shouldBeCalled: () -> Unit ->
            val cache: CachedByConstructor<CachedItemByTimeout<Int>> = CachedByConstructor.byTimeoutViaCurrentTime(
                getValue = {
                    shouldBeCalled()
                    42
                },
                timeout = 1.days
            )
            cache.isCacheValid().assertFalse("is not populated")
            val cached: CachedItemByTimeout<Int> = cache.cachedOrGet()
            cached.isTimedOut().assertFalse()

            delay(1000)
            cache.getCachedValue().assertNotNull()

            val secondCache: CachedItemByTimeout<Int> = cache.cachedOrGet()
            secondCache.isTimedOut().assertFalse()
        }
    }
}