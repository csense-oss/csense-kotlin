package csense.kotlin.patterns.restartableJob

import kotlinx.coroutines.*


public fun <First, Second, Third> CoroutineScope.restartableJobInIO(
    action: RestartableJobWith3ArgumentsAction<First, Second, Third>
): RestartableJobWith3Arguments<First, Second, Third> = restartableJobIn(dispatcher = Dispatchers.IO, action = action)