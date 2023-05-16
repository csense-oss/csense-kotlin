package csense.kotlin.extensions.collections.array.typed.Job

import kotlinx.coroutines.*

/**
 * Waits for all jobs in an array.
 * Since this is missing from Standard Library...
 * @receiver [Array]<out [Job]>
 */
public suspend fun Array<out Job>.joinAll() {
    forEach { it.join() }
}

