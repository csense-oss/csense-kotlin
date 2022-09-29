package csense.kotlin.patterns.restartableJob

import kotlinx.coroutines.*

public typealias RestartableJobWith3ArgumentsAction<First, Second, Third> = suspend CoroutineScope.(First, Second, Third) -> Unit

public class RestartableJobWith3Arguments<First, Second, Third>(
    scope: CoroutineScope,
    dispatcher: CoroutineDispatcher,
    private val action: RestartableJobWith3ArgumentsAction<First, Second, Third>
) : AbstractRestartableJob(
    scope = scope,
    dispatcher = dispatcher
) {
    public fun start(
        first: First,
        second: Second,
        third: Third
    ) {
        startNewRunningJob(invokeAction = { action(first, second, third) })
    }
}

public fun <First, Second, Third> CoroutineScope.restartableJobIn(
    dispatcher: CoroutineDispatcher,
    action: RestartableJobWith3ArgumentsAction<First, Second, Third>
): RestartableJobWith3Arguments<First, Second, Third> =
    RestartableJobWith3Arguments(scope = this, dispatcher = dispatcher, action = action)

public fun <First, Second, Third> CoroutineScope.restartableJobInDefault(
    action: RestartableJobWith3ArgumentsAction<First, Second, Third>
): RestartableJobWith3Arguments<First, Second, Third> =
    restartableJobIn(dispatcher = Dispatchers.Default, action = action)

public fun <First, Second, Third> CoroutineScope.restartableJobInMain(
    action: RestartableJobWith3ArgumentsAction<First, Second, Third>
): RestartableJobWith3Arguments<First, Second, Third> =
    restartableJobIn(dispatcher = Dispatchers.Main, action = action)