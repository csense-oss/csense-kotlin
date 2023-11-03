package csense.kotlin.patterns.restartableJob

import csense.kotlin.patterns.restartableJob.operations.*
import kotlinx.coroutines.*


public fun <First, Second> CoroutineScope.restartableJobInIO(
    action: RestartableJobWith2ArgumentsAction<First, Second>
): RestartableJobWith2Arguments<First, Second> = restartableJobIn(dispatcher = Dispatchers.IO, action = action)