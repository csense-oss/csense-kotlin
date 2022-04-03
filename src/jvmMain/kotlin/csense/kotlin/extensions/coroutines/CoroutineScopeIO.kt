@file:Suppress("unused", "DeferredIsResult")

package csense.kotlin.extensions.coroutines

import csense.kotlin.*
import csense.kotlin.annotations.threading.*
import kotlinx.coroutines.*
import kotlin.contracts.*

/**
 * Wrapper for [async] ([Dispatchers.IO])
 * @receiver [CoroutineScope]
 * @param block [CoroutineScopeFunction0]<T>
 * @return [Deferred]<T>
 */
public fun <T> CoroutineScope.asyncIO(
    action: CoroutineScopeFunction0<T>
): Deferred<T> = async(Dispatchers.IO, block = action)

/**
 * Wrapper for [launch] ([Dispatchers.IO])
 * @receiver [CoroutineScope]
 * @param block [CoroutineScopeFunction0]<[Unit]>
 * @return [Job]
 */
public fun CoroutineScope.launchIO(
    block: CoroutineScopeFunction0<Unit>
): Job = launch(Dispatchers.IO, block = block)

/**
 * Wrapper for [withContext] ([Dispatchers.IO])
 * @receiver [CoroutineScope]
 * @param block [CoroutineScopeFunction0]<[T]>
 * @return T the result for calling [block] (after suspension)
 */
public suspend fun <T> CoroutineScope.withContextIO(
    block: CoroutineScopeFunction0<T>
): T = withContext(Dispatchers.IO, block)
