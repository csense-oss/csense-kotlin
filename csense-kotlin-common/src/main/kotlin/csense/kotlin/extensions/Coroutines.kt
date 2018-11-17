@file:Suppress("unused", "NOTHING_TO_INLINE")

package csense.kotlin.extensions

import csense.kotlin.*
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*

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
 * Waits for all the given jobs to finish.
 * @receiver List<Job> the jobs to wait for
 */
suspend fun List<Job>.awaitAll() {
    this.forEach { it.join() }
}

/**
 * Iterates over the given channel, executing the given function each time
 * @receiver Channel<E>
 * @param function FunctionUnit<E>
 */
suspend fun <E> Channel<E>.forEach(function: FunctionUnit<E>) {
    for (item in this) {
        function(item)
    }
}

/**
 * Iterates over the given channel, executing the given function each time
 * @receiver Channel<E>
 * @param function AsyncFunctionUnit<E>
 */
suspend fun <E> Channel<E>.forEachAsync(function: AsyncFunctionUnit<E>) {
    for (item in this) {
        function(item)
    }
}