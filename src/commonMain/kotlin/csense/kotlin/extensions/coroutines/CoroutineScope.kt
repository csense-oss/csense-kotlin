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
): Deferred<T> = async(Dispatchers.Main, block = block)


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
 * Uses [launchDefault] combined with a [with] on the given receiver.
 * @receiver CoroutineScope
 * @param receiver Receiver
 * @param block [@kotlin.ExtensionFunctionType] SuspendFunction1<Receiver, R>
 * @return Deferred<R>
 */
public inline fun <Receiver, R> CoroutineScope.launchDefaultWith(
    receiver: Receiver,
    @InUi noinline block: suspend Receiver.() -> R
): Job = launchDefault {
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
    @InUi noinline block: CoroutineScopeFunction
): Job = launch(Dispatchers.Main, block = block)



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
    @InUi noinline block: suspend Receiver.() -> R
): Job = launchMain {
    with(receiver) {
        block()
    }
}


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
@Suppress("MissingTestFunction") //mpp and main test is still bad (see the JVM test for tests)
public suspend fun <T> CoroutineScope.withContextMain(
    @InUi block: CoroutineScopeFunction0<T>
): T = withContext(Dispatchers.Main, block)


