package csense.kotlin.patterns.restartableJob.operations

import csense.kotlin.annotations.threading.*
import csense.kotlin.patterns.restartableJob.*
import kotlinx.coroutines.*


public fun <First, Second, Third> CoroutineScope.restartableJobIn(
    dispatcher: CoroutineDispatcher,
    @InSelector(selectorName = "dispatcher")
    action: RestartableJobWith3ArgumentsAction<First, Second, Third>
): RestartableJobWith3Arguments<First, Second, Third> =
    RestartableJobWith3Arguments(scope = this, dispatcher = dispatcher, action = action)

public fun <First, Second, Third> CoroutineScope.restartableJobInDefault(
    @InBackground
    action: RestartableJobWith3ArgumentsAction<First, Second, Third>
): RestartableJobWith3Arguments<First, Second, Third> =
    restartableJobIn(dispatcher = Dispatchers.Default, action = action)

public fun <First, Second, Third> CoroutineScope.restartableJobInMain(
    @InUi
    action: RestartableJobWith3ArgumentsAction<First, Second, Third>
): RestartableJobWith3Arguments<First, Second, Third> =
    restartableJobIn(dispatcher = Dispatchers.Main, action = action)