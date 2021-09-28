@file:Suppress("unused", "NOTHING_TO_INLINE")

package csense.kotlin.extensions.coroutines

import csense.kotlin.*
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*
import kotlin.coroutines.*

/**
 * Iterates over the given [Channel], executing the given function each time
 * @receiver [Channel]<E>
 * @param function [FunctionUnit]<E>
 */
public suspend inline fun <E> Channel<E>.forEach(function: FunctionUnit<E>) {
    for (item in this) {
        function(item)
    }
}

/**
 *
 * Be cautious when using this, it requires some hefty timing in the mapper to make sense.. as it might take some time to start all the coroutines
 * and the overhead of coroutines can easily add up
 * @receiver [Iterable]<T>
 * @param coroutineScope [CoroutineScope]
 * @param context [CoroutineContext]
 * @param mapper [AsyncFunction1]<T, U>
 * @return [List]<[Deferred]<U>>
 */
public inline fun <T, U> Iterable<T>.mapAsync(
    coroutineScope: CoroutineScope,
    context: CoroutineContext = Dispatchers.Default,
    crossinline mapper: AsyncFunction1<T, U>
): List<Deferred<U>> = map {
    coroutineScope.async(context) {
        mapper(it)
    }
}

/**
 *
 * @receiver [Iterable]<T>
 * @param coroutineScope [CoroutineScope]
 * @param context [CoroutineContext]
 * @param mapper [AsyncFunction1]<T, U>
 * @return [List]<U>
 */
public suspend fun <T, U> Iterable<T>.mapAsyncAwait(
    coroutineScope: CoroutineScope,
    context: CoroutineContext = Dispatchers.Default,
    mapper: AsyncFunction1<T, U>
): List<U> = this.mapAsync(coroutineScope, context, mapper).awaitAll()

/**
 * Waits for all jobs in an array.
 * Since this is missing from Standard Library.
 * @receiver [Array]<out [Job]>
 */
public suspend fun Array<out Job>.joinAll() {
    forEach { it.join() }
}
