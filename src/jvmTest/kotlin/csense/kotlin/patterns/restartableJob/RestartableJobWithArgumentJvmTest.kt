package csense.kotlin.patterns.restartableJob

import csense.kotlin.tests.assertions.*
import kotlinx.coroutines.*
import kotlinx.coroutines.test.*
import org.junit.jupiter.api.*

class RestartableJobWithArgumentJvmTest {
    class CoroutineScopeRestartableJobInIO {

        @Test
        fun runsActionInIOThread() = runTest {
            assertCalled { shouldBeCalled: () -> Unit ->
                val input = restartableJobInIO { first: Int ->
                    first.assert(42)
                    this.assertDispatcher(Dispatchers.IO)
                    shouldBeCalled()
                }
                input.start(42)
                input.join()
            }
        }
    }
}