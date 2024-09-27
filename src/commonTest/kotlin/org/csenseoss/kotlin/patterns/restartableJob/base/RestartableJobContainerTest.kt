package org.csenseoss.kotlin.patterns.restartableJob.base

import csense.kotlin.tests.assertions.*
import kotlinx.coroutines.*
import kotlinx.coroutines.test.*
import kotlin.test.*

class RestartableJobContainerTest {
    @Test
    fun startJob() {
        val testDispatcher: TestDispatcher = StandardTestDispatcher()
        runTestAssertCalled(testDispatcher) { shouldBeCalled: () -> Unit ->
            val job = RestartableJobContainer(
                scope = this,
                dispatcher = testDispatcher
            )
            job.startJob { shouldBeCalled() }
            job.join()
        }
    }

    @Test
    fun hasJob() {
        val testDispatcher: TestDispatcher = StandardTestDispatcher()
        runTestAssertCalled(testDispatcher) { shouldBeCalled: () -> Unit ->
            val job = RestartableJobContainer(scope = this, dispatcher = testDispatcher)

            job.hasJob().assertFalse("should be lazy by default")
            job.startJob {
                delay(500)
                shouldBeCalled()
                delay(500)
            }
            job.hasJob().assertTrue("should start when asked to")
            advanceTimeBy(100)
            job.hasJob().assertTrue("should be running while not done")
            advanceUntilIdle()
            job.hasJob().assertFalse("should remove job when done")

            job.startJob {
                delay(500)
                shouldBeCalled()
                delay(500)
            }
            job.hasJob().assertTrue("should start when asked to")
            job.cancel()
            job.hasJob().assertFalse("should stop when cancel is called")
        }
    }

    @Test
    fun cancel() {
        val testDispatcher: TestDispatcher = StandardTestDispatcher()
        runTest(testDispatcher) {
            val job = RestartableJobContainer(scope = this, dispatcher = testDispatcher)
            job.startJob {
                shouldNotBeCalled()
            }
            job.cancel()
            job.join()
        }
    }
}