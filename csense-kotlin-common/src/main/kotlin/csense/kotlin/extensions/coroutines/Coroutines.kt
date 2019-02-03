@file:Suppress("unused", "NOTHING_TO_INLINE")

package csense.kotlin.extensions.coroutines

import csense.kotlin.*
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*
import kotlin.coroutines.*

/**
 * Awaits a list of deferred computations.
 * and returns the resulting list.
 *
 * @receiver List<Deferred<T>> the deferred jobs to wait for.
 * @return List<T> the computed results awaited.
 */
suspend fun <T> List<Deferred<T>>.await(): List<T> {
    return this.map { it.await() }
}

/**
 * Iterates over the given channel, executing the given function each time
 * @receiver Channel<E>
 * @param function FunctionUnit<E>
 */
suspend inline fun <E> Channel<E>.forEach(function: FunctionUnit<E>) {
    for (item in this) {
        function(item)
    }
}


/**
 *
 * Be cautious when using this, it requires some hefty timing in the mapper to make sense.. as it might take some time to start all the coroutines
 * and the overhead of coroutines can easily add up
 * @receiver Iterable<T>
 * @param coroutineScope CoroutineScope
 * @param context CoroutineContext
 * @param mapper Function1<T, U>
 * @return List<Deferred<U>>
 */
fun <T, U> Iterable<T>.mapAsync(
        coroutineScope: CoroutineScope,
        context: CoroutineContext = Dispatchers.Default,
        mapper: AsyncFunction1<T, U>
): List<Deferred<U>> = map {
    coroutineScope.async(context) {
        mapper(it)
    }
}

/**
 *
 * @receiver Iterable<T>
 * @param coroutineScope CoroutineScope
 * @param context CoroutineContext
 * @param mapper Function1<T, U>
 * @return List<U>
 */
suspend fun <T, U> Iterable<T>.mapAsyncAwait(
        coroutineScope: CoroutineScope,
        context: CoroutineContext = Dispatchers.Default,
        mapper: AsyncFunction1<T, U>
): List<U> = this.mapAsync(coroutineScope, context, mapper).awaitAll()
