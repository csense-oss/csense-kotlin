@file:OptIn(ExperimentalCoroutinesApi::class)

package csense.kotlin.extensions.duration

import csense.kotlin.tests.assertions.*
import kotlinx.coroutines.*
import kotlinx.coroutines.test.*
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