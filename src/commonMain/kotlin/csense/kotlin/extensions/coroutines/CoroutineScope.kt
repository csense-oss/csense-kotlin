@file:Suppress("unused", "NOTHING_TO_INLINE")

package csense.kotlin.extensions.coroutines

import csense.kotlin.CoroutineScopeFunction
import csense.kotlin.CoroutineScopeFunction0
import csense.kotlin.annotations.threading.InBackground
import csense.kotlin.annotations.threading.InUi
import kotlinx.coroutines.*

/**
 * Same as Async(Dispatchers.Default)
 * @receiver CoroutineScope
 * @param action suspend CoroutineScope.() -> T
 * @return Deferred<T>
 */
inline fun <T> CoroutineScope.asyncDefault(
        @InBackground noinline action: CoroutineScopeFunction0<T>
): Deferred<T> = async(Dispatchers.Default, block = action)

/**
 * same as Async(Dispatchers.Main)
 * @receiver CoroutineScope
 * @param action suspend CoroutineScope.() -> T
 * @return Deferred<T>
 */
inline fun <T> CoroutineScope.asyncMain(
        @InUi noinline action: CoroutineScopeFunction0<T>
): Deferred<T> = async(Dispatchers.Main, block = action)

/**
 * same as launch(Dispatchers.Default)
 * @receiver CoroutineScope
 * @param action suspend CoroutineScope.() -> Unit
 * @return Job
 */
inline fun CoroutineScope.launchDefault(
        @InBackground noinline action: CoroutineScopeFunction
): Job = launch(Dispatchers.Default, block = action)

/**
 * same as launch(Dispatchers.Main)
 * @receiver CoroutineScope
 * @param action suspend CoroutineScope.() -> Unit
 * @return Job
 */
inline fun CoroutineScope.launchMain(
        @InUi noinline action: CoroutineScopeFunction
): Job = launch(Dispatchers.Main, block = action)

/**
 * same as withContext(Dispatchers.Default)
 * @receiver CoroutineScope
 * @param action suspend CoroutineScope.() -> T
 * @return T
 */
suspend fun <T> CoroutineScope.withContextDefault(
        @InBackground action: CoroutineScopeFunction0<T>
): T = withContext(Dispatchers.Default, action)

/**
 * same as withContext(Dispatchers.Main)
 * @receiver CoroutineScope
 * @param action suspend CoroutineScope.() -> T
 * @return T
 */
suspend fun <T> CoroutineScope.withContextMain(
        @InUi action: CoroutineScopeFunction0<T>
): T = withContext(Dispatchers.Main, action)

