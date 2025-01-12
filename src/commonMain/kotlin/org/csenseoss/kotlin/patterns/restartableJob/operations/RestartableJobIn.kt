package org.csenseoss.kotlin.patterns.restartableJob.operations

import kotlinx.coroutines.*
import org.csenseoss.kotlin.annotations.threading.*
import org.csenseoss.kotlin.patterns.restartableJob.*

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