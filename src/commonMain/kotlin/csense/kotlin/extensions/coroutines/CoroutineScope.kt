@file:Suppress("unused", "NOTHING_TO_INLINE")

package csense.kotlin.extensions.coroutines

import csense.kotlin.*
import csense.kotlin.annotations.threading.*
import kotlinx.coroutines.*

/**
 * Same as [async] ([Dispatchers.Default])
 * @receiver [CoroutineScope]
 * @param action suspend [CoroutineScope].() -> T
 * @return [Deferred]<T>
 */
public inline fun <T> CoroutineScope.asyncDefault(
    @InBackground noinline action: CoroutineScopeFunction0<T>
): Deferred<T> = async(Dispatchers.Default, block = action)

/**
 * same as [async] ([Dispatchers.Main])
 * @receiver [CoroutineScope]
 * @param action suspend [CoroutineScope].() -> T
 * @return [Deferred]<T>
 */
public inline fun <T> CoroutineScope.asyncMain(
    @InUi noinline action: CoroutineScopeFunction0<T>
): Deferred<T> = async(Dispatchers.Main, block = action)

/**
 * same as [launch] ([Dispatchers.Default])
 * @receiver [CoroutineScope]
 * @param action suspend [CoroutineScope].() -> [Unit]
 * @return [Job]
 */
public inline fun CoroutineScope.launchDefault(
    @InBackground noinline action: CoroutineScopeFunction
): Job = launch(Dispatchers.Default, block = action)

/**
 * same as [launch] ([Dispatchers.Main])
 * @receiver [CoroutineScope]
 * @param action suspend [CoroutineScope].() -> [Unit]
 * @return [Job]
 */
public inline fun CoroutineScope.launchMain(
    @InUi noinline action: CoroutineScopeFunction
): Job = launch(Dispatchers.Main, block = action)

/**
 * same as [withContext] ([Dispatchers.Default])
 * @receiver [CoroutineScope]
 * @param action suspend [CoroutineScope].() -> T
 * @return T
 */
public suspend fun <T> CoroutineScope.withContextDefault(
    @InBackground action: CoroutineScopeFunction0<T>
): T = withContext(Dispatchers.Default, action)

/**
 * same as [withContext] ([Dispatchers.Main])
 * @receiver [CoroutineScope]
 * @param action suspend [CoroutineScope].() -> T
 * @return T
 */
public suspend fun <T> CoroutineScope.withContextMain(
    @InUi action: CoroutineScopeFunction0<T>
): T = withContext(Dispatchers.Main, action)

