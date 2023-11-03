package csense.kotlin.patterns.restartableJob

import csense.kotlin.patterns.restartableJob.base.*
import kotlinx.coroutines.*

public typealias RestartableJobAction = suspend CoroutineScope.() -> Unit

public class RestartableJob(
    scope: CoroutineScope,
    dispatcher: CoroutineDispatcher,
    private val action: RestartableJobAction
) : RestartableJobInterface {

    private val container = RestartableJobContainer(
        scope = scope,
        dispatcher = dispatcher
    )

    public operator fun invoke() {
        start()
    }

    public fun start() {
        container.startJob(invokeAction = action)
    }

    override fun cancel(cancellationException: CancellationException?): Unit =
        container.cancel(cancellationException = cancellationException)

    override fun hasJob(): Boolean =
        container.hasJob()

    override suspend fun join(): Unit =
        container.join()

}
