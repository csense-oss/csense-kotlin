package csense.kotlin.patterns.restartableJob

import kotlinx.coroutines.*


public fun <First, Second> CoroutineScope.restartableJobInIO(
    action: RestartableJobWith2ArgumentsAction<First, Second>
): RestartableJobWith2Arguments<First, Second> = restartableJobIn(dispatcher = Dispatchers.IO, action = action)