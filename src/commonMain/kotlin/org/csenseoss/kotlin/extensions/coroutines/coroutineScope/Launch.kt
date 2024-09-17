@file:Suppress("unused", "NOTHING_TO_INLINE", "DeferredIsResult")

package org.csenseoss.kotlin.extensions.coroutines.coroutineScope

import csense.kotlin.annotations.threading.*
import kotlinx.coroutines.*
import org.csenseoss.kotlin.*


/**
 * same as [launch] ([Dispatchers.Default])
 * @receiver [CoroutineScope]
 * @param block suspend [CoroutineScope].() -> [Unit]
 * @return [Job]
 */
public inline fun CoroutineScope.launchDefault(
    start: CoroutineStart = CoroutineStart.DEFAULT,
    @InBackground noinline block: CoroutineScopeFunction
): Job = launch(
    context = Dispatchers.Default,
    start = start,
    block = block
)

/**
 * Uses [launchDefault] combined with a [with] on the given receiver.
 * @receiver CoroutineScope
 * @param receiver Receiver
 * @param block [@kotlin.ExtensionFunctionType] SuspendFunction1<Receiver, R>
 * @return Deferred<R>
 */
public inline fun <Receiver, R> CoroutineScope.launchDefaultWith(
    receiver: Receiver,
    start: CoroutineStart = CoroutineStart.DEFAULT,
    @InUi noinline block: suspend Receiver.() -> R
): Job = launchDefault(start = start) {
    with(receiver) {
        block()
    }
}


/**
 * same as [launch] ([Dispatchers.Main])
 * @receiver [CoroutineScope]
 * @param block suspend [CoroutineScope].() -> [Unit]
 * @return [Job]
 */
@Suppress("MissingTestFunction") //mpp and main test is still bad (see the JVM test for tests)
public inline fun CoroutineScope.launchMain(
    start: CoroutineStart = CoroutineStart.DEFAULT,
    @InUi noinline block: CoroutineScopeFunction
): Job = launch(
    context = Dispatchers.Main,
    start = start,
    block = block
)


/**
 * Uses [launchMain] combined with a [with] on the given receiver.
 * @receiver CoroutineScope
 * @param receiver Receiver
 * @param block [@kotlin.ExtensionFunctionType] SuspendFunction1<Receiver, R>
 * @return Deferred<R>
 */
@Suppress("MissingTestFunction") //mpp and main test is still bad (see the JVM test for tests)
public inline fun <Receiver, R> CoroutineScope.launchMainWith(
    receiver: Receiver,
    start: CoroutineStart = CoroutineStart.DEFAULT,
    @InUi noinline block: suspend Receiver.() -> R
): Job = launchMain(start = start) {
    with(receiver) {
        block()
    }
}