@file:OptIn(ExperimentalCoroutinesApi::class)

package csense.kotlin.patterns.restartableJob

import csense.kotlin.tests.assertions.*
import kotlinx.coroutines.*
import kotlinx.coroutines.test.*
import kotlin.test.*

class RestartableJobTest {

    @Test
    fun start(): TestResult {
        val testDispatcher = StandardTestDispatcher()
        return runTest(testDispatcher) {
            assertCalled { shouldBeCalled ->
                val job = RestartableJob(this, testDispatcher) {
                    shouldBeCalled()
                }
                job.start()
                job.join()
            }
        }
    }

    @Test
    fun isRunning(): TestResult {
        val testDispatcher = StandardTestDispatcher()
        return runTest(testDispatcher) {
            assertCalled(times = 1) { shouldBeCalled ->
                val job = RestartableJob(this, testDispatcher) {
                    delay(500)
                    shouldBeCalled()
                    delay(500)
                }
                job.isRunning().assertFalse("should be lazy by default")
                job.start()
                job.isRunning().assertTrue("should start when asked to")
                advanceTimeBy(100)
                job.isRunning().assertTrue("should be running while not done")
                advanceUntilIdle()
                job.isRunning().assertFalse("should stop when done")

                job.start()
                job.isRunning().assertTrue("should start when asked to")
                job.cancel()
                job.isRunning().assertFalse("should stop when cancel is called")
            }
        }
    }

    @Test
    fun cancel(): TestResult {
        val testDispatcher = StandardTestDispatcher()
        return runTest(testDispatcher) {
            val job = RestartableJob(this, testDispatcher) {
                shouldNotBeCalled()
            }
            job.cancel()
            job.join()
        }
    }

    @Test
    fun join(): TestResult {
        val testDispatcher = StandardTestDispatcher()
        return runTest(testDispatcher) {
            assertCalled { shouldBeCalled ->
                val job = RestartableJob(this, testDispatcher) {
                    shouldBeCalled()
                }
                job.start()
                job.join()
            }
        }
    }
}