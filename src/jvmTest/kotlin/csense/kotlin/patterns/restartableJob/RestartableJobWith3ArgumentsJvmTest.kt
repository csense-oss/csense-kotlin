@file:OptIn(ExperimentalCoroutinesApi::class)

package csense.kotlin.patterns.restartableJob

import csense.kotlin.tests.assertions.*
import kotlinx.coroutines.*
import kotlinx.coroutines.test.*
import org.junit.jupiter.api.*

class RestartableJobWith3ArgumentsJvmTest {

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
                val job = restartableJobInMain { first: Int, second: String, third: Boolean ->
                    first.assert(42)
                    second.assert("test")
                    assertDispatcher(Dispatchers.Main)
                    shouldBeCalled()
                }
                job.start(42, "test", true)
                job.join()
            }
        }
    }


    class CoroutineScopeRestartableJobInIO {

        @Test
        fun runsActionInIOThread() = runTest {
            assertCalled { shouldBeCalled ->
                val input = restartableJobInIO { first: Int, second: String, third: Boolean ->
                    first.assert(42)
                    second.assert("test")
                    third.assertTrue()
                    this.assertDispatcher(Dispatchers.IO)
                    shouldBeCalled()
                }
                input.start(42, "test", true)
                input.join()
            }
        }

    }
}