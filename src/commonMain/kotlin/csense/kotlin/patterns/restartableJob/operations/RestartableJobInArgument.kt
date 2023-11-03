package csense.kotlin.patterns.restartableJob.operations

import csense.kotlin.annotations.threading.*
import csense.kotlin.patterns.restartableJob.*
import kotlinx.coroutines.*


public fun <First> CoroutineScope.restartableJobIn(
    dispatcher: CoroutineDispatcher,
    @InSelector(selectorName = "dispatcher")
    action: RestartableJobWithArgumentAction<First>
): RestartableJobWithArgument<First> =
    RestartableJobWithArgument(scope = this, dispatcher = dispatcher, action = action)

public fun <First> CoroutineScope.restartableJobInDefault(
    @InBackground
    action: RestartableJobWithArgumentAction<First>
): RestartableJobWithArgument<First> = restartableJobIn(dispatcher = Dispatchers.Default, action = action)

public fun <First> CoroutineScope.restartableJobInMain(
    @InUi
    action: RestartableJobWithArgumentAction<First>
): RestartableJobWithArgument<First> = restartableJobIn(dispatcher = Dispatchers.Main, action = action)