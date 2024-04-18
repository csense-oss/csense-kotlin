package org.csenseoss.kotlin.patterns.restartableJob

import kotlinx.coroutines.*
import org.csenseoss.kotlin.patterns.restartableJob.*
import org.csenseoss.kotlin.patterns.restartableJob.operations.*


public fun CoroutineScope.restartableJobInIO(
    action: RestartableJobAction
): RestartableJob = restartableJobIn(
    dispatcher = Dispatchers.IO,
    action = action
)