package org.csenseoss.kotlin.patterns.restartableJob

import kotlinx.coroutines.*
import kotlinx.coroutines.test.*
import org.csenseoss.kotlin.tests.assertions.comparable.*
import org.csenseoss.kotlin.tests.assertions.coroutines.*
import org.csenseoss.kotlin.tests.assertions.general.*
import org.junit.jupiter.api.*

class RestartableJobWithArgumentJvmTest {
    @Nested
    inner class CoroutineScopeRestartableJobInIO {

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