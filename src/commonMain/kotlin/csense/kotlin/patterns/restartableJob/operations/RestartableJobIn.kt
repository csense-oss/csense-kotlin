package csense.kotlin.patterns.restartableJob.operations

import csense.kotlin.patterns.restartableJob.*
import kotlinx.coroutines.*

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