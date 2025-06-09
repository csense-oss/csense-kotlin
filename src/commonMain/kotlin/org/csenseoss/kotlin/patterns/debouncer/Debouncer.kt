package org.csenseoss.kotlin.patterns.debouncer

import kotlinx.coroutines.*
import org.csenseoss.kotlin.patterns.restartableJob.*
import kotlin.time.*

/**
 * De-bounces a given job, after not being invoked the given duration.
 */
public class Debouncer<R>(
    scope: CoroutineScope,
    private val delay: Duration,
    private val block: suspend CoroutineScope.() -> R,
    dispatcher: CoroutineDispatcher = Dispatchers.Unconfined,
) {
    private val job: RestartableJob = RestartableJob(
        scope = scope,
        dispatcher = dispatcher
    ) {
        delay(delay)
        block()
    }

    public fun start() {
        job.start()
    }

    public fun stop(cancellationException: CancellationException? = null) {
        job.cancel(cancellationException)
    }

    public suspend fun join() {
        job.join()
    }
}