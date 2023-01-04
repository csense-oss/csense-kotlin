@file:Suppress("unused", "DeferredIsResult", "NOTHING_TO_INLINE")

package csense.kotlin.extensions.coroutines

import csense.kotlin.*
import csense.kotlin.annotations.threading.*
import kotlinx.coroutines.*

/**
 * Wrapper for [async] ([Dispatchers.IO])
 * @receiver [CoroutineScope]
 * @param block [CoroutineScopeFunction0]<T>
 * @return [Deferred]<T>
 */
public inline fun <T> CoroutineScope.asyncIO(
    noinline block: CoroutineScopeFunction0<T>
): Deferred<T> = async(Dispatchers.IO, block = block)


/**
 * Uses [asyncIO] combined with a [with] on the given receiver.
 * @receiver CoroutineScope
 * @param receiver Receiver
 * @param block [@kotlin.ExtensionFunctionType] SuspendFunction1<Receiver, R>
 * @return Deferred<R>
 */
public inline fun <Receiver, R> CoroutineScope.asyncIOWith(
    receiver: Receiver,
    @InBackground noinline block: suspend Receiver.() -> R
): Deferred<R> = asyncIO {
    with(receiver) {
        block()
    }
}


/**
 * Wrapper for [launch] ([Dispatchers.IO])
 * @receiver [CoroutineScope]
 * @param block [CoroutineScopeFunction0]<[Unit]>
 * @return [Job]
 */
public inline fun CoroutineScope.launchIO(
    noinline block: CoroutineScopeFunction0<Unit>
): Job = launch(Dispatchers.IO, block = block)


/**
 * Uses [launchIO] combined with a [with] on the given receiver.
 * @receiver CoroutineScope
 * @param receiver Receiver
 * @param block [@kotlin.ExtensionFunctionType] SuspendFunction1<Receiver, R>
 * @return Deferred<R>
 */
public inline fun <Receiver, R> CoroutineScope.launchIOWith(
    receiver: Receiver,
    @InBackground noinline block: suspend Receiver.() -> R
): Job = launchIO {
    with(receiver) {
        block()
    }
}


/**
 * Wrapper for [withContext] ([Dispatchers.IO])
 * @receiver [CoroutineScope]
 * @param block [CoroutineScopeFunction0]<[T]>
 * @return T the result for calling [block] (after suspension)
 */
public suspend fun <T> CoroutineScope.withContextIO(
    block: CoroutineScopeFunction0<T>
): T = withContext(context = Dispatchers.IO, block = block)
