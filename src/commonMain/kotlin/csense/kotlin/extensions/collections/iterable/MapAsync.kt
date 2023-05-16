package csense.kotlin.extensions.collections.iterable

import csense.kotlin.*
import kotlinx.coroutines.*
import kotlin.coroutines.*


/**
 *
 * Be cautious when using this, it requires some hefty timing in the mapper to make sense. as it might take some time to start all the coroutines
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
): List<Deferred<U>> = map { it: T ->
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
): List<U> = this.mapAsync(
    coroutineScope = coroutineScope,
    context = context,
    mapper = mapper
).awaitAll()


