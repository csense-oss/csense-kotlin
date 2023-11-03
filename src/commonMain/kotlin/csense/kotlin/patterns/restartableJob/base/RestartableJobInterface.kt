package csense.kotlin.patterns.restartableJob.base

import kotlinx.coroutines.*

public interface RestartableJobInterface {
    public fun cancel(cancellationException: CancellationException? = null)
    public fun hasJob(): Boolean
    public suspend fun join()

}