@file:OptIn(ExperimentalCoroutinesApi::class)

package csense.kotlin.coroutines.restartableJob

import csense.kotlin.tests.assertions.*
import kotlinx.coroutines.*
import kotlinx.coroutines.test.*
import org.junit.jupiter.api.*

class RestartableJobJvmTest {

    class CoroutineScopeRestartableJobInMain {
        companion object {
            private val mainThreadSurrogate = newSingleThreadContext("UI thread")

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
        fun shouldBeCalledOnMainDispatcher() = runTest {
            assertCalled { shouldBeCalled ->
                val job = restartableJobInMain {
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
        fun shouldBeCalledOnIODispatcher() = runTest {
            val ioDispatcher = Dispatchers.IO
            assertCalled { shouldBeCalled ->
                val job = restartableJobInIO {
                    assertDispatcher(ioDispatcher)
                    shouldBeCalled()
                }
                job.start()
                job.join()
            }
        }
    }
}