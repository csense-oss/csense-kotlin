package csense.kotlin.patterns.restartableJob.operations

import csense.kotlin.patterns.restartableJob.*
import csense.kotlin.tests.assertions.*
import kotlinx.coroutines.*
import kotlinx.coroutines.test.*
import kotlin.test.*

class RestartableJobInTest {
    class CoroutineScopeRestartableJobIn {
        @Test
        fun shouldBeCalledOnGivenDispatcher(): TestResult {
            val dispatcher: TestDispatcher = StandardTestDispatcher()
            return runTestAssertCalled(dispatcher) { shouldBeCalled: () -> Unit ->
                val job: RestartableJob = restartableJobIn(dispatcher) {
                    assertDispatcher(dispatcher)
                    shouldBeCalled()
                }
                job.start()
                job.join()
            }
        }
    }

    class CoroutineScopeRestartableJobInDefault {
        @Test
        fun shouldBeCalledOnDefaultDispatcher(): TestResult = runTestAssertCalled { shouldBeCalled: () -> Unit ->
            val job: RestartableJob = restartableJobInDefault {
                assertDispatcher(Dispatchers.Default)
                shouldBeCalled()
            }
            job.start()
            job.join()
        }
    }

    class CoroutineScopeRestartableJobInMain {
        @Test
        fun shouldBeCalledOnMainDispatcher(

        ): Unit = runTestForMainDispatcherAssertCalled { shouldBeCalled: () -> Unit ->
            val job: RestartableJob = restartableJobInMain {
                assertDispatcher(Dispatchers.Main)
                shouldBeCalled()
            }
            job.start()
            job.join()
        }
    }
}
