package csense.kotlin.extensions.coroutines

import csense.kotlin.tests.assertions.*
import kotlinx.coroutines.*
import kotlinx.coroutines.test.*
import org.junit.jupiter.api.*

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@OptIn(ExperimentalCoroutinesApi::class)
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

    @Test
    fun CoroutineScopeAsyncMainWith() {


        fun isRightReceiver() = runTest {
            assertCalled { shouldBeCalled ->
                asyncMainWith("test") {
                    assert("test")
                    shouldBeCalled()
                }.await()
            }
        }


        fun isMainContext() = runTest {
            assertCalled { shouldBeCalled ->
                asyncMainWith("test") {
                    coroutineScope { assertDispatcher(Dispatchers.Main) }
                    shouldBeCalled()
                }.await()
            }
        }


        fun returnsRightResult() = runTest {
            asyncMainWith("test") {
                42
            }.await().assert(42)
        }

        isRightReceiver()
        isMainContext()
        returnsRightResult()
    }

    @Test
    fun CoroutineScopeLaunchMainWith() {
        @Test
        fun isRightReceiver() = runTest {
            assertCalled { shouldBeCalled ->
                launchMainWith("test") {
                    assert("test")
                    shouldBeCalled()
                }.join()
            }
        }

        @Test
        fun isMainContext() = runTest {
            assertCalled { shouldBeCalled ->
                launchMainWith("test") {
                    coroutineScope { assertDispatcher(Dispatchers.Main) }
                    shouldBeCalled()
                }.join()
            }
        }
        isRightReceiver()
        isMainContext()
    }
}