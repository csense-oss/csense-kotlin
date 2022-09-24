package csense.kotlin.coroutines.restartableJob

import csense.kotlin.*
import kotlinx.coroutines.*

public fun CoroutineScope.restartableJobInIO(
    action: ReceiverFunctionUnit<CoroutineScope>
): RestartableJob = restartableJobIn(dispatcher = Dispatchers.IO, action = action)