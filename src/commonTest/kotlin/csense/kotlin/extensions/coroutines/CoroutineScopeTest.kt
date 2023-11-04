@file:OptIn(ExperimentalCoroutinesApi::class)

package csense.kotlin.extensions.coroutines

import csense.kotlin.extensions.coroutines.coroutineScope.*
import csense.kotlin.tests.assertions.*
import kotlinx.coroutines.*
import kotlinx.coroutines.test.*
import kotlin.test.*

class CoroutineScopeTest {

    //region Default dispatcher
    @Test
    fun coroutineScopeAsyncDefault() = runTest {
        val async = asyncDefault {
            assertDispatcher(Dispatchers.Default)
            "result"
        }
        async.await().assert("result")
    }

    class CoroutineScopeAsyncDefaultWith {

        @Test
        fun isRightReceiver() = runTest {
            assertCalled { shouldBeCalled: () -> Unit ->
                asyncDefaultWith("test") {
                    assert("test")
                    shouldBeCalled()
                }.await()
            }
        }

        @Test
        fun isDefaultContext() = runTest {
            assertCalled { shouldBeCalled: () -> Unit ->
                asyncDefaultWith("test") {
                    coroutineScope { assertDispatcher(Dispatchers.Default) }
                    shouldBeCalled()
                }.await()
            }
        }

        @Test
        fun returnsRightResult() = runTest {
            asyncDefaultWith("test") {
                42
            }.await().assert(42)
        }

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
        assertCalled { shouldBeCalled: () -> Unit ->
            launchDefault {
                assertDispatcher(Dispatchers.Default)
                shouldBeCalled()
            }.join()
        }
    }

    class CoroutineScopeLaunchDefaultWith {

        @Test
        fun isRightReceiver() = runTest {
            assertCalled { shouldBeCalled: () -> Unit ->
                launchDefaultWith("test") {
                    assert("test")
                    shouldBeCalled()
                }.join()
            }
        }

        @Test
        fun isDefaultContext() = runTest {
            assertCalled { shouldBeCalled: () -> Unit ->
                launchDefaultWith("test") {
                    coroutineScope { assertDispatcher(Dispatchers.Default) }
                    shouldBeCalled()
                }.join()
            }
        }
    }
    //endregion

}