package csense.kotlin.patterns.restartableJob

import kotlinx.coroutines.*

public typealias RestartableJobWith2ArgumentsAction<First, Second> = suspend CoroutineScope.(First, Second) -> Unit

public class RestartableJobWith2Arguments<First, Second>(
    scope: CoroutineScope,
    dispatcher: CoroutineDispatcher,
    private val action: RestartableJobWith2ArgumentsAction<First, Second>
) : AbstractRestartableJob(
    scope = scope,
    dispatcher = dispatcher
) {
    public fun start(
        first: First,
        second: Second
    ) {
        startNewRunningJob(
            invokeAction = { action(first, second) }
        )
    }
}

public fun <First, Second> CoroutineScope.restartableJobIn(
    dispatcher: CoroutineDispatcher,
    action: RestartableJobWith2ArgumentsAction<First, Second>
): RestartableJobWith2Arguments<First, Second> =
    RestartableJobWith2Arguments(scope = this, dispatcher = dispatcher, action = action)

public fun <First, Second> CoroutineScope.restartableJobInDefault(
    action: RestartableJobWith2ArgumentsAction<First, Second>
): RestartableJobWith2Arguments<First, Second> =
    restartableJobIn(dispatcher = Dispatchers.Default, action = action)

public fun <First, Second> CoroutineScope.restartableJobInMain(
    action: RestartableJobWith2ArgumentsAction<First, Second>
): RestartableJobWith2Arguments<First, Second> =
    restartableJobIn(dispatcher = Dispatchers.Main, action = action)