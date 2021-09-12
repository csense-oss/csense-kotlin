package csense.kotlin.extensions.coroutines

import csense.kotlin.coroutines.*
import csense.kotlin.tests.assertions.*
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*
import kotlinx.coroutines.sync.*
import kotlin.test.*

class CoroutinesTest {

    class ArrayoutJobJoinAll {
        @Test
        fun empty() = runBlockingTest {
            arrayOf<Job>().joinAll()
        }

        @Test
        fun single() = runBlockingTest {
            assertCalled { shouldBeCalled ->
                val jobs: Array<Job> = arrayOf(launch { shouldBeCalled() })
                jobs.joinAll()
            }
        }

        @Test
        fun multiple() = runBlockingTest {
            assertCalled(times = 2) { shouldBeCalled ->
                val jobs: Array<Job> = arrayOf(
                    launch { shouldBeCalled() },
                    launch { shouldBeCalled() }
                )
                jobs.joinAll()
            }
        }
    }


    class IterableTMapAsyncAwait {
        @Test
        fun empty() = runBlockingTest {
            listOf<String>().mapAsyncAwait(this) {
                shouldNotBeCalled()
            }.assertEmpty()
        }

        @Test
        fun single() = runBlockingTest {
            listOf("input").mapAsyncAwait(this) {
                it.assert("input")
                "test"
            }.assertSingle("test")
        }

        @Test
        fun multiple() = runBlockingTest {
            val items = listOf("input1", "input2")
            val itt = items.iterator()
            val lst = items.mapAsyncAwait(this) {
                itt.next().assert(it)
                it
            }
            lst.assertSize(2)
            lst.assertContainsAll("input1", "input2")
        }
    }

    class IterableTMapAsync {
        @Test
        fun empty() = runBlockingTest {
            listOf<String>().mapAsync(this) {
                shouldNotBeCalled()
            }.awaitAll().assertEmpty()
        }

        @Test
        fun single() = runBlockingTest {
            assertCalled { shouldBeCalled ->
                listOf("input").mapAsync(this) {
                    shouldBeCalled()
                    it.assert("input")
                    "output"
                }.awaitAll().assertSingle("output")
            }
        }

        @Test
        fun multiple() = runBlockingTest {
            assertCalled(times = 2) { shouldBeCalled ->
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
        fun empty() = runBlockingTest {
            var didCallForeach = false
            val channel = Channel<String>()
            val job = launchDefault {
                channel.forEach { didCallForeach = true }
            }
            channel.close()
            job.join()
            didCallForeach.assertFalse("should not call on empty")
        }

        @Test
        fun single() = runBlockingTest {
            val mutex = Mutex(true)
            val channel = Channel<String>()
            val job = launchDefault {
                channel.forEach { mutex.unlock() }
            }
            val sendJob = launchDefault {
                channel.send("test")
            }
            sendJob.join()
            mutex.lock()
            channel.close()
            job.join()
        }

        @Test
        fun multiple() = runBlockingTest {
            val sem = Semaphore(2, 2)
            val channel = Channel<String>()
            val job = launchDefault {
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