package org.csenseoss.kotlin.patterns.restartableJob.operations

import org.csenseoss.kotlin.annotations.threading.*
import kotlinx.coroutines.*
import org.csenseoss.kotlin.patterns.restartableJob.*


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