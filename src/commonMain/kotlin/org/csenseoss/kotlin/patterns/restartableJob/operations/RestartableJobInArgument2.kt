package org.csenseoss.kotlin.patterns.restartableJob.operations

import kotlinx.coroutines.*
import org.csenseoss.kotlin.annotations.threading.*
import org.csenseoss.kotlin.patterns.restartableJob.*


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