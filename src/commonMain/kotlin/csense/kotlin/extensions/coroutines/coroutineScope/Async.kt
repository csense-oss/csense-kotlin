@file:Suppress("unused", "NOTHING_TO_INLINE", "DeferredIsResult")

package csense.kotlin.extensions.coroutines.coroutineScope

import csense.kotlin.*
import csense.kotlin.annotations.threading.*
import kotlinx.coroutines.*

/**
 * Same as [async] ([Dispatchers.Default])
 * @receiver [CoroutineScope]
 * @param block suspend [CoroutineScope].() -> T
 * @return [Deferred]<T>
 */
public inline fun <T> CoroutineScope.asyncDefault(
    @InBackground noinline block: CoroutineScopeFunction0<T>
): Deferred<T> = async(context = Dispatchers.Default, block = block)


/**
 * Uses [asyncDefault] combined with a [with] on the given receiver.
 * @receiver CoroutineScope
 * @param receiver Receiver
 * @param block [@kotlin.ExtensionFunctionType] SuspendFunction1<Receiver, R>
 * @return Deferred<R>
 */
public inline fun <Receiver, R> CoroutineScope.asyncDefaultWith(
    receiver: Receiver,
    @InBackground noinline block: suspend Receiver.() -> R
): Deferred<R> = asyncDefault {
    with(receiver) {
        block()
    }
}

/**
 * same as [async] ([Dispatchers.Main])
 * @receiver [CoroutineScope]
 * @param block suspend [CoroutineScope].() -> T
 * @return [Deferred]<T>
 */
@Suppress("MissingTestFunction") //mpp and main test is still bad (see the JVM test for tests)
public inline fun <T> CoroutineScope.asyncMain(
    @InUi noinline block: CoroutineScopeFunction0<T>
): Deferred<T> = async(context = Dispatchers.Main, block = block)


/**
 * Uses [asyncMain] combined with a [with] on the given receiver.
 * @receiver CoroutineScope
 * @param receiver Receiver
 * @param block [@kotlin.ExtensionFunctionType] SuspendFunction1<Receiver, R>
 * @return Deferred<R>
 */
@Suppress("MissingTestFunction") //mpp and main test is still bad (see the JVM test for tests)
public inline fun <Receiver, R> CoroutineScope.asyncMainWith(
    receiver: Receiver,
    @InUi noinline block: suspend Receiver.() -> R
): Deferred<R> = asyncMain {
    with(receiver) {
        block()
    }
}
