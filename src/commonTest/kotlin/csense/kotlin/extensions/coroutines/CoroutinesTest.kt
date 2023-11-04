@file:OptIn(ExperimentalCoroutinesApi::class)

package csense.kotlin.extensions.coroutines

import csense.kotlin.extensions.collections.iterable.*
import csense.kotlin.extensions.coroutines.channel.*
import csense.kotlin.extensions.coroutines.coroutineScope.*
import csense.kotlin.tests.assertions.*
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*
import kotlinx.coroutines.sync.*
import kotlinx.coroutines.test.*
import kotlin.test.*

class CoroutinesTest {




    class IterableTMapAsyncAwait {
        @Test
        fun empty() = runTest {
            listOf<String>().mapAsyncAwait(this) {
                shouldNotBeCalled()
            }.assertEmpty()
        }

        @Test
        fun single() = runTest {
            listOf("input").mapAsyncAwait(this) {
                it.assert("input")
                "test"
            }.assertSingle("test")
        }

        @Test
        fun multiple() = runTest {
            val lst = listOf("input1", "input2").mapAsyncAwait(this) {
                it
            }
            lst.assertSize(2)
            lst.assertContainsAll("input1", "input2")
        }
    }

    class IterableTMapAsync {
        @Test
        fun empty() = runTest {
            listOf<String>().mapAsync(this) {
                shouldNotBeCalled()
            }.awaitAll().assertEmpty()
        }

        @Test
        fun single() = runTest {
            assertCalled { shouldBeCalled: () -> Unit ->
                listOf("input").mapAsync(this) {
                    shouldBeCalled()
                    it.assert("input")
                    "output"
                }.awaitAll().assertSingle("output")
            }
        }

        @Test
        fun multiple() = runTest {
            assertCalled(times = 2) { shouldBeCalled: () -> Unit ->
                listOf("input1", "input2").mapAsync(this) {
                    shouldBeCalled()
                    it.assertStartsWith("input")
                    "output"
                }.awaitAll().apply {
                    assertSize(2)
                    first().assert("output")
                    last().assert("output")
                }
            }
        }
    }

    class ChannelEForEach {
        @Test
        fun empty() = runTest {
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
            val mutex: Mutex = Mutex(true)
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
            val sem: Semaphore = Semaphore(2, 2)
            val channel: Channel<String> = Channel<String>()
            val job: Job = launchDefault {
                channel.forEach { sem.release() }
            }
            val sendJob = launchDefault {
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