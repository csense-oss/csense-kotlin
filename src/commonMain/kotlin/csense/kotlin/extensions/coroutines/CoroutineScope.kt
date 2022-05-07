@file:Suppress("unused", "NOTHING_TO_INLINE", "DeferredIsResult")
package csense.kotlin.extensions.coroutines

import csense.kotlin.*
import csense.kotlin.annotations.threading.*
import kotlinx.coroutines.*
import kotlin.contracts.*

/**
 * Same as [async] ([Dispatchers.Default])
 * @receiver [CoroutineScope]
 * @param block suspend [CoroutineScope].() -> T
 * @return [Deferred]<T>
 */
public inline fun <T> CoroutineScope.asyncDefault(
    @InBackground noinline block: CoroutineScopeFunction0<T>
): Deferred<T> = async(Dispatchers.Default, block = block)

/**
 * same as [async] ([Dispatchers.Main])
 * @receiver [CoroutineScope]
 * @param block suspend [CoroutineScope].() -> T
 * @return [Deferred]<T>
 */
@Suppress("MissingTestFunction") //mpp and main test is still bad
public inline fun <T> CoroutineScope.asyncMain(
    @InUi noinline block: CoroutineScopeFunction0<T>
): Deferred<T> = async(Dispatchers.Main, block = block)

/**
 * same as [launch] ([Dispatchers.Default])
 * @receiver [CoroutineScope]
 * @param block suspend [CoroutineScope].() -> [Unit]
 * @return [Job]
 */
public inline fun CoroutineScope.launchDefault(
    @InBackground noinline block: CoroutineScopeFunction
): Job = launch(Dispatchers.Default, block = block)

/**
 * same as [launch] ([Dispatchers.Main])
 * @receiver [CoroutineScope]
 * @param block suspend [CoroutineScope].() -> [Unit]
 * @return [Job]
 */
@Suppress("MissingTestFunction") //mpp and main test is still bad
public inline fun CoroutineScope.launchMain(
    @InUi noinline block: CoroutineScopeFunction
): Job = launch(Dispatchers.Main, block = block)

/**
 * same as [withContext] ([Dispatchers.Default])
 * @receiver [CoroutineScope]
 * @param block suspend [CoroutineScope].() -> T
 * @return T
 */
public suspend fun <T> CoroutineScope.withContextDefault(
    @InBackground block: CoroutineScopeFunction0<T>
): T = withContext(Dispatchers.Default, block)

/**
 * same as [withContext] ([Dispatchers.Main])
 * @receiver [CoroutineScope]
 * @param block suspend [CoroutineScope].() -> T
 * @return T
 */
@Suppress("MissingTestFunction") //mpp and main test is still bad
public suspend fun <T> CoroutineScope.withContextMain(
    @InUi block: CoroutineScopeFunction0<T>
): T = withContext(Dispatchers.Main, block)

