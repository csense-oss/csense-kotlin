package csense.kotlin.patterns.restartableJob

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

    public fun start() {
        container.startNewRunningJob(invokeAction = action)
    }

    override fun cancel(cancellationException: CancellationException?): Unit =
        container.cancel(cancellationException)

    override fun isRunning(): Boolean =
        container.isRunning()

    override suspend fun join(): Unit =
        container.join()

}
