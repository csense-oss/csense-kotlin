package csense.kotlin.patterns.restartableJob

import kotlinx.coroutines.*

public typealias RestartableJobWithArgumentAction<First> = suspend CoroutineScope.(First) -> Unit

public class RestartableJobWithArgument<First>(
    scope: CoroutineScope,
    dispatcher: CoroutineDispatcher,
    private val action: RestartableJobWithArgumentAction<First>
) : AbstractRestartableJob(
    scope = scope,
    dispatcher = dispatcher
) {
    public fun start(First: First) {
        startNewRunningJob(invokeAction = { action(First) })
    }
}

public fun <First> CoroutineScope.restartableJobIn(
    dispatcher: CoroutineDispatcher,
    action: RestartableJobWithArgumentAction<First>
): RestartableJobWithArgument<First> =
    RestartableJobWithArgument(scope = this, dispatcher = dispatcher, action = action)

public fun <First> CoroutineScope.restartableJobInDefault(
    action: RestartableJobWithArgumentAction<First>
): RestartableJobWithArgument<First> = restartableJobIn(dispatcher = Dispatchers.Default, action = action)

public fun <First> CoroutineScope.restartableJobInMain(
    action: RestartableJobWithArgumentAction<First>
): RestartableJobWithArgument<First> = restartableJobIn(dispatcher = Dispatchers.Main, action = action)