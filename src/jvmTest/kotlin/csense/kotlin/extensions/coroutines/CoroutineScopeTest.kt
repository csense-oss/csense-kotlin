package csense.kotlin.extensions.coroutines

import csense.kotlin.tests.assertions.*
import kotlinx.coroutines.*
import kotlinx.coroutines.test.*
import org.junit.jupiter.api.*

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@OptIn(ObsoleteCoroutinesApi::class, ExperimentalCoroutinesApi::class)
@DelicateCoroutinesApi
class CoroutineScopeTestJvm {

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
            Dispatchers.resetMain() // reset main dispatcher to the original Main dispatcher
            mainThreadSurrogate.close()
        }
    }

    @Test
    fun coroutineScopeWithContextMain() = runTest {
        withContextMain {
            assertDispatcher(Dispatchers.Main)
            "result"
        }.assert("result")
    }

    @Test
    fun coroutineScopeLaunchMain() = runTest {
        assertCalled { shouldBeCalled ->
            launchMain {
                assertDispatcher(Dispatchers.Main)
                shouldBeCalled()
            }.join()
        }
    }

    @Test
    fun coroutineScopeAsyncMain() = runTest {
        val async = asyncMain {
            assertDispatcher(Dispatchers.Main)
            "result"
        }
        async.await().assert("result")
    }
}