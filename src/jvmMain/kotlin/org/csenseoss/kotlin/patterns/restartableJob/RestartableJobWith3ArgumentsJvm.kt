package org.csenseoss.kotlin.patterns.restartableJob

import kotlinx.coroutines.*
import org.csenseoss.kotlin.patterns.restartableJob.operations.*


public fun <First, Second, Third> CoroutineScope.restartableJobInIO(
    action: RestartableJobWith3ArgumentsAction<First, Second, Third>
): RestartableJobWith3Arguments<First, Second, Third> = restartableJobIn(dispatcher = Dispatchers.IO, action = action)