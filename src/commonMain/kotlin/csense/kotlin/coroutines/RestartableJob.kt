package csense.kotlin.coroutines

import csense.kotlin.*
import kotlinx.coroutines.*

/**
 * A restartable job is a job that can be restarted (which means the old will get canceled first)
 *
 */
public class RestartableJob(
    private val action: EmptyFunction,
    private val dispatcher: CoroutineDispatcher
) {

    private var runningJob: Job? = null

    public fun CoroutineScope.start() {
        cancel()
        startNewRunningJob()
    }

    private fun CoroutineScope.startNewRunningJob() {
        runningJob = launch(dispatcher) {
            action()
        }
    }

    public fun cancel(
        cancellationException: CancellationException? = null
    ) {
        runningJob?.cancel(cancellationException)
    }
}