package csense.kotlin.extensions.coroutines

import csense.kotlin.*
import kotlinx.coroutines.*


fun <T> CoroutineScope.asyncIO(
        action: CoroutineScopeFunction0<T>
): Deferred<T> = async(Dispatchers.IO, block = action)

fun CoroutineScope.launchIO(
        action: CoroutineScopeFunction0<Unit>
): Job = launch(Dispatchers.IO, block = action)

