package csense.kotlin.patterns.restartableJob

import kotlinx.coroutines.*

public typealias RestartableJobAction = suspend CoroutineScope.() -> Unit

public class RestartableJob(
    scope: CoroutineScope,
    dispatcher: CoroutineDispatcher,
    private val action: RestartableJobAction
) : AbstractRestartableJob(
    scope = scope,
    dispatcher = dispatcher
) {

    public fun start() {
        startNewRunningJob(invokeAction = action)
    }
}

public fun CoroutineScope.restartableJobIn(
    dispatcher: CoroutineDispatcher,
    action: RestartableJobAction
): RestartableJob = RestartableJob(scope = this, dispatcher = dispatcher, action = action)

public fun CoroutineScope.restartableJobInDefault(
    action: RestartableJobAction
): RestartableJob = restartableJobIn(dispatcher = Dispatchers.Default, action = action)

public fun CoroutineScope.restartableJobInMain(
    action: RestartableJobAction
): RestartableJob = restartableJobIn(dispatcher = Dispatchers.Main, action = action)