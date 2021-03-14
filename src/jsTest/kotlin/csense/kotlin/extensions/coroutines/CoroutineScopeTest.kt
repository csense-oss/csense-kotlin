package csense.kotlin.extensions.coroutines

import csense.kotlin.coroutines.*
import csense.kotlin.test.*
import csense.kotlin.tests.assertions.*
import kotlinx.coroutines.*
import kotlin.test.*

class CoroutineScopeTestJs {
    @Test
    fun coroutineScopeWithContextMain() = runBlockingTest {
        withContextMain {
            assertDispatcher(Dispatchers.Main)
            "result"
        }.assert("result")
    }

    @Test
    fun coroutineScopeLaunchMain() = runBlockingTest {
        assertCalled { shouldBeCalled ->
            launchMain {
                assertDispatcher(Dispatchers.Main)
                shouldBeCalled()
            }.join()
        }
    }

    @Test
    fun coroutineScopeAsyncMain() = runBlockingTest {
        val async = asyncMain {
            assertDispatcher(Dispatchers.Main)
            "result"
        }
        async.await().assert("result")
    }
}