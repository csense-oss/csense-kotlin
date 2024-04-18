package org.csenseoss.kotlin.patterns.restartableJob

import kotlinx.coroutines.*
import org.csenseoss.kotlin.patterns.restartableJob.operations.*


public fun <Input> CoroutineScope.restartableJobInIO(
    action: RestartableJobWithArgumentAction<Input>
): RestartableJobWithArgument<Input> = restartableJobIn(dispatcher = Dispatchers.IO, action = action)