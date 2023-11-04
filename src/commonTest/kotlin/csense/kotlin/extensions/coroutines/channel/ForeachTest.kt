package csense.kotlin.extensions.coroutines.channel

import csense.kotlin.extensions.coroutines.coroutineScope.*
import csense.kotlin.tests.assertions.*
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*
import kotlinx.coroutines.sync.*
import kotlinx.coroutines.test.*
import kotlin.test.*

class ForeachTest {
    class ChannelEForEach {
        @Test
        fun empty(): TestResult = runTest {
            var didCallForeach = false
            val channel: Channel<String> = Channel()
            val job: Job = launchDefault {
                channel.forEach { didCallForeach = true }
            }
            channel.close()
            job.join()
            didCallForeach.assertFalse("should not call on empty")
        }

        @Test
        fun single(): TestResult = runTest {
            val mutex: Mutex = Mutex(locked = true)
            val channel: Channel<String> = Channel()
            val job: Job = launchDefault {
                channel.forEach { mutex.unlock() }
            }
            val sendJob: Job = launchDefault {
                channel.send("test")
            }
            sendJob.join()
            mutex.lock()
            channel.close()
            job.join()
        }

        @Test
        fun multiple(): TestResult = runTest {
            val sem: Semaphore = Semaphore(permits = 2, acquiredPermits = 2)
            val channel: Channel<String> = Channel()
            val job: Job = launchDefault {
                channel.forEach { sem.release() }
            }
            val sendJob: Job = launchDefault {
                channel.send("test")
                channel.send("1234")
            }
            sendJob.join()
            sem.acquire()
            sem.acquire()
            channel.close()
            job.join()
        }
    }
}