package csense.kotlin.patterns.restartableJob

import csense.kotlin.tests.assertions.*
import kotlinx.coroutines.*
import kotlinx.coroutines.test.*
import kotlin.test.*

class RestartableJobTest {

    @Test
    fun start() = runTest {
        assertCalled { shouldBeCalled ->
            val job = RestartableJob(this, Dispatchers.Default) {
                shouldBeCalled()
            }
            job.start()
            job.join()
        }
    }

    @Test
    fun isRunning() = runTest {
        assertCalled { shouldBeCalled ->
            val job = RestartableJob(this, Dispatchers.Default) {
                shouldBeCalled()
                delay(5000)
            }
            job.isRunning().assertFalse("should be lazy by default")
            job.start()
            job.isRunning().assertTrue("should start when asked to")
            advanceTimeBy(1000)
            job.isRunning().assertTrue("should be running while not done")
            advanceUntilIdle()
            job.isRunning().assertFalse("should stop when done")

            job.start()
            job.isRunning().assertTrue("should start when asked to")
            job.cancel()
            job.isRunning().assertFalse("should stop when cancel is called")
        }
    }

    @Test
    fun cancel() = runTest {
        assertCalled { shouldBeCalled ->
            val job = RestartableJob(this, Dispatchers.Default) {
                shouldBeCalled()
            }
        }
    }

    @Test
    fun join() = runTest {
        assertCalled { shouldBeCalled ->
            val job = RestartableJob(this, Dispatchers.Default) {
                shouldBeCalled()
            }
        }
    }
}