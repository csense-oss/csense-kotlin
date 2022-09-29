@file:OptIn(ExperimentalCoroutinesApi::class)

package csense.kotlin.coroutines.restartableJob

import csense.kotlin.patterns.restartableJob.*
import csense.kotlin.tests.assertions.*
import kotlinx.coroutines.*
import kotlinx.coroutines.test.*
import kotlin.test.*

class RestartableJobTest {

    class CoroutineScopeRestartableJobIn {
        @Test
        fun shouldBeCalledOnGivenDispatcher(): TestResult {
            val dispatcher = StandardTestDispatcher()
            return runTest(dispatcher) {
                assertCalled { shouldBeCalled ->
                    val job = restartableJobIn(dispatcher) {
                        assertDispatcher(dispatcher)
                        shouldBeCalled()
                    }
                    job.start()
                    job.join()
                }
            }
        }
    }

    class CoroutineScopeRestartableJobInDefault {
        @Test
        fun shouldBeCalledOnDefaultDispatcher() = runTest {
            assertCalled { shouldBeCalled ->
                val job = restartableJobInDefault {
                    assertDispatcher(Dispatchers.Default)
                    shouldBeCalled()
                }
                job.start()
                job.join()
            }
        }
    }
}