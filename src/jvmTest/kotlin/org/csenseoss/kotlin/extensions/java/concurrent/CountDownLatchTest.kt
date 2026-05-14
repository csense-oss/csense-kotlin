package org.csenseoss.kotlin.extensions.java.concurrent

import kotlinx.coroutines.*
import org.csenseoss.kotlin.tests.assertions.comparable.assert
import org.csenseoss.kotlin.tests.assertions.exceptions.assertThrowsCause
import org.csenseoss.kotlin.tests.assertions.primitives.boolean.*
import org.junit.jupiter.api.*
import java.util.concurrent.*
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.seconds

class CountDownLatchTest {
    private val backgroundScope: CoroutineScope = CoroutineScope(Dispatchers.Default + Job())

    @Nested
    inner class Await {
        @Test
        fun awaitSuccessZero() {
            val countDownLatch = CountDownLatch(0)
            val didAwait: Boolean = countDownLatch.await(1.seconds)
            didAwait.assertTrue()
        }

        @Test
        fun awaitSuccessRealTime() {
            val countDownLatch = CountDownLatch(1)
            backgroundScope.launch {
                delay(100.milliseconds)
                countDownLatch.countDown()
            }
            val didAwait: Boolean = countDownLatch.await(1.seconds)
            didAwait.assertTrue()
        }

        @Test
        fun awaitFailed() {
            val countDownLatch = CountDownLatch(1)
            val didAwait: Boolean = countDownLatch.await(10.milliseconds)
            didAwait.assertFalse()
        }

    }

    @Nested
    inner class AwaitOrThrow {
        @Test
        fun awaitSuccessZero() {
            val countDownLatch = CountDownLatch(0)
            countDownLatch.awaitOrThrow(1.seconds)
        }

        @Test
        fun awaitSuccessRealTime() {
            val countDownLatch = CountDownLatch(1)
            print("!?!??!")

            backgroundScope.launch {
                delay(100.milliseconds)
                countDownLatch.countDown()
            }
            countDownLatch.awaitOrThrow(1.seconds)
        }

        @Test
        fun awaitFailed() {
            val countDownLatch = CountDownLatch(1)
            val exception: Throwable = assertThrows<Throwable> {
                countDownLatch.awaitOrThrow(10.milliseconds, timeoutMessage = "timeout")
            }
            exception.message.assert("timeout")
        }
    }

}