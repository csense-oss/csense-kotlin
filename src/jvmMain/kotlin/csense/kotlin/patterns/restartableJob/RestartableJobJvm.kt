package csense.kotlin.patterns.restartableJob

import csense.kotlin.patterns.restartableJob.operations.*
import kotlinx.coroutines.*


public fun CoroutineScope.restartableJobInIO(
    action: RestartableJobAction
): RestartableJob = restartableJobIn(
    dispatcher = Dispatchers.IO,
    action = action
)