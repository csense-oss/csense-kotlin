package org.csenseoss.kotlin.patterns.restartableJob.operations

import kotlinx.coroutines.*
import kotlinx.coroutines.test.*
import org.csenseoss.kotlin.patterns.restartableJob.*
import org.csenseoss.kotlin.tests.assertions.coroutines.*
import org.csenseoss.kotlin.tests.assertions.primitives.charSequence.*
import kotlin.test.*

private fun assertWithDispatcher(
    dispatcher: CoroutineDispatcher,
    shouldBeCalled: () -> Unit
): RestartableJobWith2ArgumentsAction<String, String> {
    return { first: String, second: String ->
        first.assert("first")
        second.assert("second")
        assertDispatcher(dispatcher)
        shouldBeCalled()
    }
}

private suspend fun RestartableJobWith2Arguments<String, String>.startTestAndJoin() {
    start("first", "second")
    join()
}

class RestartableJobInWith2ArgumentsTest {
    class CoroutineScopeRestartableJobIn {
        @Test
        fun shouldBeCalledOnGivenDispatcher() {
            val dispatcher: TestDispatcher = StandardTestDispatcher()
            runTestAssertCalled(dispatcher) { shouldBeCalled: () -> Unit ->
                val job: RestartableJobWith2Arguments<String, String> = restartableJobIn(
                    dispatcher,
                    assertWithDispatcher(dispatcher = dispatcher, shouldBeCalled = shouldBeCalled)
                )
                job.startTestAndJoin()
            }
        }


    }

    class CoroutineScopeRestartableJobInDefault {
        @Test
        fun shouldBeCalledOnDefaultDispatcher() {
            runTestAssertCalled { shouldBeCalled: () -> Unit ->
                val job: RestartableJobWith2Arguments<String, String> = restartableJobInDefault(
                    assertWithDispatcher(dispatcher = Dispatchers.Default, shouldBeCalled = shouldBeCalled)
                )
                job.startTestAndJoin()
            }
        }
    }

    class CoroutineScopeRestartableJobInMain {
        @Test
        fun shouldBeCalledOnMainDispatcher() {
            runTestForMainDispatcherAssertCalled { shouldBeCalled: () -> Unit ->
                val job: RestartableJobWith2Arguments<String, String> = restartableJobInMain(
                    assertWithDispatcher(dispatcher = Dispatchers.Main, shouldBeCalled = shouldBeCalled)
                )
                job.startTestAndJoin()
            }
        }
    }
}