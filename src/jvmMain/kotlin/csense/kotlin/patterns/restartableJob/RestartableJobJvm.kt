package csense.kotlin.patterns.restartableJob

import kotlinx.coroutines.*


public fun CoroutineScope.restartableJobInIO(
    action: RestartableJobAction
): RestartableJob = restartableJobIn(dispatcher = Dispatchers.IO, action = action)