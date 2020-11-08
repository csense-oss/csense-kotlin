@file:Suppress("unused")

package csense.kotlin.extensions.coroutines

import csense.kotlin.CoroutineScopeFunction0
import kotlinx.coroutines.*

/**
 * Wrapper for [async] ([Dispatchers.IO])
 * @receiver [CoroutineScope]
 * @param action [CoroutineScopeFunction0]<T>
 * @return [Deferred]<T>
 */
public fun <T> CoroutineScope.asyncIO(
    action: CoroutineScopeFunction0<T>
): Deferred<T> = async(Dispatchers.IO, block = action)

/**
 * Wrapper for [launch] ([Dispatchers.IO])
 * @receiver [CoroutineScope]
 * @param action [CoroutineScopeFunction0]<[Unit]>
 * @return [Job]
 */
public fun CoroutineScope.launchIO(
    action: CoroutineScopeFunction0<Unit>
): Job = launch(Dispatchers.IO, block = action)

