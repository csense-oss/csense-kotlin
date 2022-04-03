package csense.kotlin.extensions.coroutines

import csense.kotlin.tests.assertions.*
import kotlinx.coroutines.*
import org.junit.jupiter.api.*

class CoroutineScopeIOTest {
    @Test
    fun coroutineScopeAsyncIO() = runBlocking {
        val async = asyncIO {
            assertDispatcher(Dispatchers.IO)
            "test"
        }
        val value = async.await()
        value.assert("test")
    }

    @Test
    fun coroutineScopeLaunchIO() = runBlocking {
        assertCalled { shouldBeCalled ->
            val async = launchIO {
                assertDispatcher(Dispatchers.IO)
                shouldBeCalled()
            }
            async.join()
        }
    }

    @Test
    fun coroutineScopeWithContextIO() = runBlocking {
        assertCalled { shouldBeCalled ->
            val async = withContextIO {
                assertDispatcher(Dispatchers.IO)
                shouldBeCalled()
                "test"
            }
            async.assert("test")

        }
    }
}