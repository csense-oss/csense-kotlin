@file:Suppress("unused", "NOTHING_TO_INLINE", "DeferredIsResult")

package org.csenseoss.kotlin.extensions.coroutines.coroutineScope

import kotlinx.coroutines.*
import org.csenseoss.kotlin.*
import org.csenseoss.kotlin.annotations.threading.`in`.*

/**
 * same as [withContext] ([Dispatchers.Default])
 * @receiver [CoroutineScope]
 * @param block suspend [CoroutineScope].() -> T
 * @return T
 */
public suspend fun <T> CoroutineScope.withContextDefault(
    @InBackground block: CoroutineScopeFunction0<T>
): T = withContext(context = Dispatchers.Default, block = block)

/**
 * same as [withContext] ([Dispatchers.Main])
 * @receiver [CoroutineScope]
 * @param block suspend [CoroutineScope].() -> T
 * @return T
 */
@Suppress("MissingTestFunction") //mpp and main test is still bad (see the JVM test for tests)
public suspend fun <T> CoroutineScope.withContextMain(
    @InUi block: CoroutineScopeFunction0<T>
): T = withContext(context = Dispatchers.Main, block = block)