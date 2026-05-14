package org.csenseoss.kotlin.extensions.java.concurrent

import java.util.concurrent.*
import kotlin.time.*


@Throws(TimeoutException::class, InterruptedException::class)
public fun CountDownLatch.awaitOrThrow(
    timeout: Duration,
    timeoutMessage: String = ""
) {
    val didAwait: Boolean = await(timeout)
    if (!didAwait) {
        throw TimeoutException(timeoutMessage)
    }
}

@Throws(InterruptedException::class)
public fun CountDownLatch.await(timeout: Duration): Boolean {
    return await(timeout.inWholeMilliseconds, TimeUnit.MILLISECONDS)
}