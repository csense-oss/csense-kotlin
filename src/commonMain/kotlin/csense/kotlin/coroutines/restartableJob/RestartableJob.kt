package csense.kotlin.coroutines.restartableJob

import csense.kotlin.*
import csense.kotlin.annotations.threading.*
import kotlinx.coroutines.*

/**
 * A restartable job is a job that can be restarted (which means the old will get canceled first)
 */
public class RestartableJob(
    private val scope: CoroutineScope,
    @InSelector("dispatcher")
    private val action: ReceiverFunctionUnit<CoroutineScope>,
    private val dispatcher: CoroutineDispatcher
) {

    private var runningJob: Job? = null

    public fun start() {
        cancel()
        startNewRunningJob()
    }

    private fun startNewRunningJob() {
        runningJob = scope.launch(dispatcher) {
            action()
            runningJob = null
        }
    }

    public fun cancel(
        cancellationException: CancellationException? = null
    ) {
        runningJob?.cancel(cancellationException)
    }

    public fun isRunning(): Boolean {
        return runningJob != null
    }

    public suspend fun join() {
        runningJob?.join()
    }
}


public fun CoroutineScope.restartableJobIn(
    dispatcher: CoroutineDispatcher,
    action: ReceiverFunctionUnit<CoroutineScope>
): RestartableJob = RestartableJob(scope = this, action = action, dispatcher = dispatcher)

public fun CoroutineScope.restartableJobInDefault(
    action: ReceiverFunctionUnit<CoroutineScope>
): RestartableJob = restartableJobIn(dispatcher = Dispatchers.Default, action = action)

public fun CoroutineScope.restartableJobInMain(
    action: ReceiverFunctionUnit<CoroutineScope>
): RestartableJob = restartableJobIn(dispatcher = Dispatchers.Main, action = action)