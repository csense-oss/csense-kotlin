package csense.kotlin.patterns.restartableJob

import csense.kotlin.patterns.restartableJob.base.*
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

    public operator fun invoke(
        first: First,
        second: Second
    ) {
        start(
            first = first,
            second = second
        )
    }

    public fun start(
        first: First,
        second: Second
    ) {
        container.startJob(
            invokeAction = { action(first, second) }
        )
    }

    override fun cancel(cancellationException: CancellationException?): Unit =
        container.cancel(cancellationException = cancellationException)

    override fun hasJob(): Boolean =
        container.hasJob()

    override suspend fun join(): Unit =
        container.join()
}