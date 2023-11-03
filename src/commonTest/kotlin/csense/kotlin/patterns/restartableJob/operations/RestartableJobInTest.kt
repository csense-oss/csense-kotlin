package csense.kotlin.patterns.restartableJob.operations

import csense.kotlin.patterns.restartableJob.*
import csense.kotlin.tests.assertions.*
import kotlinx.coroutines.*
import kotlinx.coroutines.test.*
import kotlin.test.*

private fun assertWithDispatcher(
    dispatcher: CoroutineDispatcher,
    shouldBeCalled: () -> Unit
): RestartableJobAction {
    return {
        assertDispatcher(dispatcher)
        shouldBeCalled()
    }
}

private suspend fun RestartableJob.startTestAndJoin() {
    start()
    join()
}

class RestartableJobInTest {
    @Test
    fun CoroutineScopeRestartableJobIn(): TestResult {
        val dispatcher: TestDispatcher = StandardTestDispatcher()
        return runTestAssertCalled(dispatcher) { shouldBeCalled: () -> Unit ->
            val job: RestartableJob = restartableJobIn(dispatcher, assertWithDispatcher(dispatcher, shouldBeCalled))
            job.startTestAndJoin()
        }
    }

    @Test
    fun CoroutineScopeRestartableJobInDefault(): TestResult = runTestAssertCalled { shouldBeCalled: () -> Unit ->
        val job: RestartableJob = restartableJobInDefault(assertWithDispatcher(Dispatchers.Default, shouldBeCalled))
        job.startTestAndJoin()
    }

    @Test
    fun CoroutineScopeRestartableJobInMain(

    ): Unit = runTestForMainDispatcherAssertCalled { shouldBeCalled: () -> Unit ->
        val job: RestartableJob = restartableJobInMain(assertWithDispatcher(Dispatchers.Main, shouldBeCalled))
        job.startTestAndJoin()
    }
}
