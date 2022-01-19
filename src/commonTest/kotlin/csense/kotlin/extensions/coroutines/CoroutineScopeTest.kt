@file:OptIn(ExperimentalCoroutinesApi::class)

package csense.kotlin.extensions.coroutines

import csense.kotlin.tests.assertions.*
import kotlinx.coroutines.*
import kotlinx.coroutines.test.*
import kotlin.test.*

class CoroutineScopeTest {

    @Test
    fun coroutineScopeAsyncDefault() = runTest {
        val async = asyncDefault {
            assertDispatcher(Dispatchers.Default)
            "result"
        }
        async.await().assert("result")
    }


    @Test
    fun coroutineScopeWithContextDefault() = runTest {
        withContextDefault {
            assertDispatcher(Dispatchers.Default)
            "result"
        }.assert("result")
    }


    @Test
    fun coroutineScopeLaunchDefault() = runTest {
        assertCalled { shouldBeCalled ->
            launchDefault {
                assertDispatcher(Dispatchers.Default)
                shouldBeCalled()
            }.join()
        }
    }

}