package csense.kotlin.patterns.restartableJob.arguments

import csense.kotlin.patterns.restartableJob.*
import kotlinx.coroutines.*

public typealias RestartableJobWith2ArgumentsAction<First, Second> = suspend CoroutineScope.(First, Second) -> Unit

public class RestartableJobWith2Arguments<First, Second>(
    scope: CoroutineScope,
    dispatcher: CoroutineDispatcher,
    private val action: RestartableJobWith2ArgumentsAction<First, Second>
) : RestartableJobInterface {

    private val container = RestartableJobContainer(
        scope = scope,
        dispatcher = dispatcher
    )

    public fun start(
        first: First,
        second: Second
    ) {
        container.startNewRunningJob(
            invokeAction = { action(first, second) }
        )
    }

    override fun cancel(cancellationException: CancellationException?): Unit =
        container.cancel(cancellationException)

    override fun isRunning(): Boolean =
        container.isRunning()

    override suspend fun join(): Unit =
        container.join()
}
