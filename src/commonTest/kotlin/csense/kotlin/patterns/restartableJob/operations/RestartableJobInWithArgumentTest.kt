package csense.kotlin.patterns.restartableJob.operations

import csense.kotlin.patterns.restartableJob.*
import csense.kotlin.tests.assertions.*
import kotlinx.coroutines.*
import kotlinx.coroutines.test.*
import kotlin.test.*

private fun assertWithDispatcher(
    dispatcher: CoroutineDispatcher,
    shouldBeCalled: () -> Unit
): RestartableJobWithArgumentAction<String> {
    return { first: String ->
        first.assert("first")
        assertDispatcher(dispatcher)
        shouldBeCalled()
    }
}

private suspend fun RestartableJobWithArgument<String>.startTestAndJoin() {
    start("first")
    join()
}

class RestartableJobInWithArgumentTest {
    @Test
    fun CoroutineScopeRestartableJobIn(): TestResult {
        val dispatcher: TestDispatcher = StandardTestDispatcher()
        return runTestAssertCalled(dispatcher) { shouldBeCalled: () -> Unit ->
            val job: RestartableJobWithArgument<String> =
                restartableJobIn(dispatcher, assertWithDispatcher(dispatcher, shouldBeCalled))
            job.startTestAndJoin()
        }
    }

    @Test
    fun CoroutineScopeRestartableJobInDefault(

    ): TestResult = runTestAssertCalled { shouldBeCalled: () -> Unit ->
        val job: RestartableJobWithArgument<String> = restartableJobInDefault(
            assertWithDispatcher(
                Dispatchers.Default,
                shouldBeCalled
            )
        )
        job.startTestAndJoin()

    }

    @Test
    fun CoroutineScopeRestartableJobInMain(

    ): Unit = runTestForMainDispatcherAssertCalled { shouldBeCalled: () -> Unit ->
        val job: RestartableJobWithArgument<String> = restartableJobInMain(
            assertWithDispatcher(
                Dispatchers.Main,
                shouldBeCalled
            )
        )
        job.startTestAndJoin()
    }
}
