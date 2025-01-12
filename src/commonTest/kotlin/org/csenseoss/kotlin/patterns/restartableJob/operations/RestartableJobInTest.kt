package org.csenseoss.kotlin.patterns.restartableJob.operations

import kotlinx.coroutines.*
import kotlinx.coroutines.test.*
import org.csenseoss.kotlin.patterns.restartableJob.*
import org.csenseoss.kotlin.tests.assertions.coroutines.*
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
    fun coroutineScopeRestartableJobIn() {
        val dispatcher: TestDispatcher = StandardTestDispatcher()
        runTestAssertCalled(dispatcher) { shouldBeCalled: () -> Unit ->
            val job: RestartableJob = restartableJobIn(dispatcher, assertWithDispatcher(dispatcher, shouldBeCalled))
            job.startTestAndJoin()
        }
    }

    @Test
    fun coroutineScopeRestartableJobInDefault() {
        runTestAssertCalled { shouldBeCalled: () -> Unit ->
            val job: RestartableJob = restartableJobInDefault(assertWithDispatcher(Dispatchers.Default, shouldBeCalled))
            job.startTestAndJoin()
        }
    }

    @Test
    fun coroutineScopeRestartableJobInMain() {
        runTestForMainDispatcherAssertCalled { shouldBeCalled: () -> Unit ->
            val job: RestartableJob = restartableJobInMain(assertWithDispatcher(Dispatchers.Main, shouldBeCalled))
            job.startTestAndJoin()
        }
    }
}