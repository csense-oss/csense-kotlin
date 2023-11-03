package csense.kotlin.patterns.restartableJob.operations

import csense.kotlin.annotations.threading.*
import csense.kotlin.patterns.restartableJob.*
import kotlinx.coroutines.*


public fun <First, Second> CoroutineScope.restartableJobIn(
    dispatcher: CoroutineDispatcher,
    @InSelector(selectorName = "dispatcher")
    action: RestartableJobWith2ArgumentsAction<First, Second>
): RestartableJobWith2Arguments<First, Second> =
    RestartableJobWith2Arguments(scope = this, dispatcher = dispatcher, action = action)

public fun <First, Second> CoroutineScope.restartableJobInDefault(
    @InBackground
    action: RestartableJobWith2ArgumentsAction<First, Second>
): RestartableJobWith2Arguments<First, Second> =
    restartableJobIn(dispatcher = Dispatchers.Default, action = action)

public fun <First, Second> CoroutineScope.restartableJobInMain(
    @InUi
    action: RestartableJobWith2ArgumentsAction<First, Second>
): RestartableJobWith2Arguments<First, Second> =
    restartableJobIn(dispatcher = Dispatchers.Main, action = action)