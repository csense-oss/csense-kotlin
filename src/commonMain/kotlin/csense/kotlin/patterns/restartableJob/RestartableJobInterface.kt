package csense.kotlin.patterns.restartableJob

import kotlinx.coroutines.*

public interface RestartableJobInterface {
    public fun cancel(cancellationException: CancellationException? = null)
    public fun isRunning(): Boolean
    public suspend fun join()
}