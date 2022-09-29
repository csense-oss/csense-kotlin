package csense.kotlin.patterns.restartableJob

import kotlinx.coroutines.*

public abstract class AbstractRestartableJob(
    private val scope: CoroutineScope,
    private val dispatcher: CoroutineDispatcher
) {

    private var runningJob: Job? = null

    public fun cancel(
        cancellationException: CancellationException? = null
    ) {
        runningJob?.cancel(cancellationException)
        resetRunningJob()

    }

    public fun isRunning(): Boolean {
        return runningJob != null
    }

    public suspend fun join() {
        runningJob?.join()
    }

    protected fun startNewRunningJob(
        invokeAction: suspend CoroutineScope.() -> Unit,
    ) {
        cancel(null)
        runningJob = scope.launch(dispatcher) {
            invokeAction()
            resetRunningJob()
        }
    }

    private fun resetRunningJob() {
        runningJob = null
    }
}