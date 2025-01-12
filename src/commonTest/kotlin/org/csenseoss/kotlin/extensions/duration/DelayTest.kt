@file:OptIn(ExperimentalCoroutinesApi::class)

package org.csenseoss.kotlin.extensions.duration

import kotlinx.coroutines.*
import kotlinx.coroutines.test.*
import org.csenseoss.kotlin.tests.assertions.*
import org.csenseoss.kotlin.tests.assertions.comparable.*
import kotlin.test.*
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.seconds

class DelayTest {
    @Test
    fun delay(): TestResult = runTest {
        0.seconds.delay()
        currentTime.assert(0)
        110.milliseconds.delay()
        currentTime.assert(110)
    }
}