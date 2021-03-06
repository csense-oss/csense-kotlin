package csense.kotlin.extensions.coroutines

import csense.kotlin.tests.assertions.*
import kotlinx.coroutines.*
import org.junit.*
import kotlin.coroutines.*

class CoroutineScopeIOTest {
    @Test
    fun coroutineScopeAsyncIO() = runBlocking {
        val async = asyncIO {
            val dispatcher = coroutineContext[ContinuationInterceptor]
                    as CoroutineDispatcher
            dispatcher.assertAs(Dispatchers.IO)
            "test"
        }
        val value = async.await()
        value.assert("test")
    }

    @Test
    fun coroutineScopeLaunchIO() = runBlocking {
        assertCalled { shouldBeCalled ->
            val async = launchIO {
                val dispatcher = coroutineContext[ContinuationInterceptor]
                        as CoroutineDispatcher
                dispatcher.assertAs(Dispatchers.IO)
                shouldBeCalled()
            }
            async.join()
        }
    }
}