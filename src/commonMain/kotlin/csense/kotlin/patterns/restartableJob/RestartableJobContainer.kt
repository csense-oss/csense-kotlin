package csense.kotlin.patterns.restartableJob

import kotlinx.coroutines.*

public class RestartableJobContainer(
    private val scope: CoroutineScope,
    private val dispatcher: CoroutineDispatcher
) : RestartableJobInterface {

    private var runningJob: Job? = null

    public override fun cancel(
        cancellationException: CancellationException?
    ) {
        runningJob?.cancel(cancellationException)
        resetRunningJob()

    }

    public override fun isRunning(): Boolean {
        return runningJob != null
    }

    public override suspend fun join() {
        runningJob?.join()
    }

    public fun startNewRunningJob(
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