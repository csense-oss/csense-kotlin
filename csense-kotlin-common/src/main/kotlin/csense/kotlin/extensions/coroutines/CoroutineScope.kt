@file:Suppress("unused", "NOTHING_TO_INLINE")

package csense.kotlin.extensions.coroutines

import csense.kotlin.*
import kotlinx.coroutines.*

/**
 * Same as Async(Dispatchers.Default)
 * @receiver CoroutineScope
 * @param action suspend CoroutineScope.() -> T
 * @return Deferred<T>
 */
fun <T> CoroutineScope.asyncDefault(
        action: CoroutineScopeFunction0<T>
): Deferred<T> = async(Dispatchers.Default, block = action)

/**
 * same as Async(Dispatchers.Main)
 * @receiver CoroutineScope
 * @param action suspend CoroutineScope.() -> T
 * @return Deferred<T>
 */
fun <T> CoroutineScope.asyncMain(
        action: CoroutineScopeFunction0<T>
): Deferred<T> = async(Dispatchers.Main, block = action)

/**
 * same as launch(Dispatchers.Default)
 * @receiver CoroutineScope
 * @param action suspend CoroutineScope.() -> Unit
 * @return Job
 */
fun CoroutineScope.launchDefault(
        action: CoroutineScopeFunction
): Job = launch(Dispatchers.Default, block = action)

/**
 * same as launch(Dispatchers.Main)
 * @receiver CoroutineScope
 * @param action suspend CoroutineScope.() -> Unit
 * @return Job
 */
fun CoroutineScope.launchMain(
        action: CoroutineScopeFunction
): Job = launch(Dispatchers.Main, block = action)

/**
 * same as withContext(Dispatchers.Default)
 * @receiver CoroutineScope
 * @param action suspend CoroutineScope.() -> T
 * @return T
 */
suspend fun <T> CoroutineScope.withContextDefault(action: CoroutineScopeFunction0<T>): T =
        withContext(Dispatchers.Default, action)

/**
 * same as withContext(Dispatchers.Main)
 * @receiver CoroutineScope
 * @param action suspend CoroutineScope.() -> T
 * @return T
 */
suspend fun <T> CoroutineScope.withContextMain(action: CoroutineScopeFunction0<T>): T =
        withContext(Dispatchers.Main, action)

