package csense.kotlin.extensions.coroutines

import csense.kotlin.coroutines.*
import csense.kotlin.tests.assertions.*
import kotlinx.coroutines.*
import kotlin.test.*

//see https://github.com/Kotlin/kotlinx.coroutines/issues/1996 for Main issue(s)
class CoroutineScopeTest {


    @Test
    fun coroutineScopeAsyncDefault() = runBlockingTest {
        val async = asyncDefault {
            assertDispatcher(Dispatchers.Default)
            "result"
        }
        async.await().assert("result")
    }


    @Test
    fun coroutineScopeWithContextDefault() = runBlockingTest {
        withContextDefault {
            assertDispatcher(Dispatchers.Default)
            "result"
        }.assert("result")
    }


    @Test
    fun coroutineScopeLaunchDefault() = runBlockingTest {
        assertCalled { shouldBeCalled ->
            launchDefault {
                assertDispatcher(Dispatchers.Default)
                shouldBeCalled()
            }.join()
        }
    }

    @Test
    fun coroutineScopeWithContextMain() {
        @Suppress("UNUSED_VARIABLE") val x = 0 //Due to platform dependent tests
    }

    @Test
    fun coroutineScopeLaunchMain() = runBlockingTest {
        @Suppress("UNUSED_VARIABLE") val x = 0 //Due to platform dependent tests
    }

    @Test
    fun coroutineScopeAsyncMain() = runBlockingTest {
        @Suppress("UNUSED_VARIABLE") val x = 0 //Due to platform dependent tests
    }

}