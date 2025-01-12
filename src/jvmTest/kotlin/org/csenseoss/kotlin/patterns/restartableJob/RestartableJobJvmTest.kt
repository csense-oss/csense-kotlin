@file:OptIn(ExperimentalCoroutinesApi::class, DelicateCoroutinesApi::class)

package org.csenseoss.kotlin.patterns.restartableJob

import kotlinx.coroutines.*
import kotlinx.coroutines.test.*
import org.csenseoss.kotlin.patterns.restartableJob.operations.*
import org.csenseoss.kotlin.tests.assertions.*
import org.csenseoss.kotlin.tests.assertions.coroutines.*
import org.csenseoss.kotlin.tests.assertions.general.*
import org.junit.jupiter.api.*

class RestartableJobJvmTest {

    class CoroutineScopeRestartableJobInMain {
        companion object {
            private val mainThreadSurrogate: CloseableCoroutineDispatcher = newSingleThreadContext("UI thread")

            @BeforeAll
            @JvmStatic
            fun setUp() {
                Dispatchers.setMain(mainThreadSurrogate)
            }

            @AfterAll
            @JvmStatic
            fun tearDown() {
                Dispatchers.resetMain()
                mainThreadSurrogate.close()
            }
        }

        @Test
        fun shouldBeCalledOnMainDispatcher(): TestResult = runTest {
            assertCalled { shouldBeCalled: () -> Unit ->
                val job: RestartableJob = restartableJobInMain {
                    assertDispatcher(Dispatchers.Main)
                    shouldBeCalled()
                }
                job.start()
                job.join()
            }
        }
    }


    class CoroutineScopeRestartableJobInIO {
        @Test
        fun shouldBeCalledOnIODispatcher(): TestResult = runTest {
            val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
            assertCalled { shouldBeCalled: () -> Unit ->
                val job: RestartableJob = restartableJobInIO {
                    assertDispatcher(ioDispatcher)
                    shouldBeCalled()
                }
                job.start()
                job.join()
            }
        }
    }
}