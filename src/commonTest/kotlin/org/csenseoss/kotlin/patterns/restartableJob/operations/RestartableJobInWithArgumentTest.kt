package org.csenseoss.kotlin.patterns.restartableJob.operations

import kotlinx.coroutines.*
import kotlinx.coroutines.test.*
import org.csenseoss.kotlin.patterns.restartableJob.*
import org.csenseoss.kotlin.tests.assertions.*
import org.csenseoss.kotlin.tests.assertions.coroutines.*
import org.csenseoss.kotlin.tests.assertions.primitives.charSequence.*
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
    fun coroutineScopeRestartableJobIn() {
        val dispatcher: TestDispatcher = StandardTestDispatcher()
        runTestAssertCalled(dispatcher) { shouldBeCalled: () -> Unit ->
            val job: RestartableJobWithArgument<String> =
                restartableJobIn(dispatcher, assertWithDispatcher(dispatcher, shouldBeCalled))
            job.startTestAndJoin()
        }
    }

    @Test
    fun coroutineScopeRestartableJobInDefault() {
        runTestAssertCalled { shouldBeCalled: () -> Unit ->
            val job: RestartableJobWithArgument<String> = restartableJobInDefault(
                assertWithDispatcher(
                    Dispatchers.Default,
                    shouldBeCalled
                )
            )
            job.startTestAndJoin()
        }
    }

    @Test
    fun coroutineScopeRestartableJobInMain() {
        runTestForMainDispatcherAssertCalled { shouldBeCalled: () -> Unit ->
            val job: RestartableJobWithArgument<String> = restartableJobInMain(
                assertWithDispatcher(
                    Dispatchers.Main,
                    shouldBeCalled
                )
            )
            job.startTestAndJoin()
        }
    }
}