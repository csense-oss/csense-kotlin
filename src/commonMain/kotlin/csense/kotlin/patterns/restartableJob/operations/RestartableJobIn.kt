package csense.kotlin.patterns.restartableJob.operations

import csense.kotlin.annotations.threading.*
import csense.kotlin.patterns.restartableJob.*
import kotlinx.coroutines.*

public fun CoroutineScope.restartableJobIn(
    dispatcher: CoroutineDispatcher,
    @InSelector(selectorName = "dispatcher")
    action: RestartableJobAction
): RestartableJob = RestartableJob(scope = this, dispatcher = dispatcher, action = action)

public fun CoroutineScope.restartableJobInDefault(
    @InBackground
    action: RestartableJobAction
): RestartableJob = restartableJobIn(dispatcher = Dispatchers.Default, action = action)

public fun CoroutineScope.restartableJobInMain(
    @InUi
    action: RestartableJobAction
): RestartableJob = restartableJobIn(dispatcher = Dispatchers.Main, action = action)