package csense.kotlin.patterns.restartableJob

import csense.kotlin.patterns.restartableJob.arguments.*
import kotlinx.coroutines.*


public fun <Input> CoroutineScope.restartableJobInIO(
    action: RestartableJobWithArgumentAction<Input>
): RestartableJobWithArgument<Input> = restartableJobIn(dispatcher = Dispatchers.IO, action = action)