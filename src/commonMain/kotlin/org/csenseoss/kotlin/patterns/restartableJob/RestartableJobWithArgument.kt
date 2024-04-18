package org.csenseoss.kotlin.patterns.restartableJob

import kotlinx.coroutines.*
import org.csenseoss.kotlin.patterns.restartableJob.base.*

public typealias RestartableJobWithArgumentAction<First> = suspend CoroutineScope.(First) -> Unit

public class RestartableJobWithArgument<First>(
    scope: CoroutineScope,
    dispatcher: CoroutineDispatcher,
    private val action: RestartableJobWithArgumentAction<First>
) : RestartableJobInterface {
    private val container = RestartableJobContainer(
        scope = scope,
        dispatcher = dispatcher
    )

    public operator fun invoke(first: First) {
        start(first = first)
    }

    public fun start(first: First) {
        container.startJob(invokeAction = { action(first) })
    }


    override fun cancel(cancellationException: CancellationException?) {
        container.cancel(cancellationException = cancellationException)
    }

    override fun hasJob(): Boolean {
        return container.hasJob()
    }

    override suspend fun join() {
        container.join()
    }
}