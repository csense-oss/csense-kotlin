package csense.kotlin.patterns.restartableJob.operations

import csense.kotlin.patterns.restartableJob.*
import csense.kotlin.tests.assertions.*
import kotlinx.coroutines.*
import kotlinx.coroutines.test.*
import kotlin.test.*

private fun assertWithDispatcher(
    dispatcher: CoroutineDispatcher,
    shouldBeCalled: () -> Unit
): RestartableJobWith3ArgumentsAction<String, String, String> {
    return { first: String, second: String, third: String ->
        first.assert("first")
        second.assert("second")
        third.assert("third")
        assertDispatcher(dispatcher)
        shouldBeCalled()
    }
}

private suspend fun RestartableJobWith3Arguments<String, String, String>.startTestAndJoin() {
    start("first", "second", "third")
    join()
}

class RestartableJobInWith3ArgumentsTest {


    class CoroutineScopeRestartableJobIn {
        @Test
        fun shouldBeCalledOnGivenDispatcher(): TestResult {
            val dispatcher: TestDispatcher = StandardTestDispatcher()
            return runTestAssertCalled(dispatcher) { shouldBeCalled: () -> Unit ->
                val job: RestartableJobWith3Arguments<String, String, String> = restartableJobIn(
                    dispatcher,
                    assertWithDispatcher(dispatcher = dispatcher, shouldBeCalled = shouldBeCalled)
                )
                job.startTestAndJoin()
            }
        }


    }

    class CoroutineScopeRestartableJobInDefault {
        @Test
        fun shouldBeCalledOnDefaultDispatcher(): TestResult = runTestAssertCalled { shouldBeCalled: () -> Unit ->
            val job: RestartableJobWith3Arguments<String, String, String> = restartableJobInDefault(
                assertWithDispatcher(dispatcher = Dispatchers.Default, shouldBeCalled = shouldBeCalled)
            )
            job.startTestAndJoin()
        }
    }

    class CoroutineScopeRestartableJobInMain {
        @Test
        fun shouldBeCalledOnMainDispatcher(

        ): Unit = runTestForMainDispatcherAssertCalled { shouldBeCalled: () -> Unit ->
            val job: RestartableJobWith3Arguments<String, String, String> = restartableJobInMain(
                assertWithDispatcher(dispatcher = Dispatchers.Main, shouldBeCalled = shouldBeCalled)
            )
            job.startTestAndJoin()
        }
    }
}
