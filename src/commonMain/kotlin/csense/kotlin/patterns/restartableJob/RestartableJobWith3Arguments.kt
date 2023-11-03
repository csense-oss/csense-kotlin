package csense.kotlin.patterns.restartableJob

import csense.kotlin.patterns.restartableJob.base.*
import kotlinx.coroutines.*

public typealias RestartableJobWith3ArgumentsAction<First, Second, Third> = suspend CoroutineScope.(First, Second, Third) -> Unit

public class RestartableJobWith3Arguments<First, Second, Third>(
    scope: CoroutineScope,
    dispatcher: CoroutineDispatcher,
    private val action: RestartableJobWith3ArgumentsAction<First, Second, Third>
) : RestartableJobInterface {

    private val container = RestartableJobContainer(
        scope = scope,
        dispatcher = dispatcher
    )

    public operator fun invoke(
        first: First,
        second: Second,
        third: Third
    ) {
        start(first = first, second = second, third = third)
    }

    public fun start(
        first: First,
        second: Second,
        third: Third
    ) {
        container.startJob(invokeAction = { action(first, second, third) })
    }

    override fun cancel(cancellationException: CancellationException?): Unit =
        container.cancel(cancellationException = cancellationException)

    override fun hasJob(): Boolean =
        container.hasJob()

    override suspend fun join(): Unit =
        container.join()
}