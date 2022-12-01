package csense.kotlin.patterns.restartableJob.operations

import csense.kotlin.patterns.restartableJob.arguments.*
import kotlinx.coroutines.*


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