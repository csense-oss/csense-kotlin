package csense.kotlin.extensions

import csense.kotlin.AsyncFunctionUnit
import csense.kotlin.FunctionUnit
import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.channels.Channel


/**
 * Awaits a list of deferred computations.
 * and returns the resulting list.
 * @return the computed results awaited.
 */
suspend fun <T> List<Deferred<T>>.await(): List<T> {
    return this.map { it.await() }
}

/**
 * Waits for all the given jobs to finish.
 */
suspend fun List<Job>.awaitAll() {
    this.forEach { it.join() }
}

suspend fun <E> Channel<E>.forEach(function: FunctionUnit<E>) {
    for (item in this) {
        function(item)
    }
}

suspend fun <E> Channel<E>.forEachAsync(function: AsyncFunctionUnit<E>) {
    for (item in this) {
        function(item)
    }
}