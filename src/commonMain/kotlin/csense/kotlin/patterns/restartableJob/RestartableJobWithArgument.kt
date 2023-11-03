package csense.kotlin.patterns.restartableJob

import csense.kotlin.patterns.restartableJob.base.*
import kotlinx.coroutines.*

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