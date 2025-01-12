@file:OptIn(ExperimentalCoroutinesApi::class)

package org.csenseoss.kotlin.extensions.coroutines

import kotlinx.coroutines.*
import kotlinx.coroutines.test.*
import org.csenseoss.kotlin.extensions.coroutines.*
import org.csenseoss.kotlin.tests.assertions.*
import org.csenseoss.kotlin.tests.assertions.comparable.*
import org.csenseoss.kotlin.tests.assertions.coroutines.*
import org.csenseoss.kotlin.tests.assertions.general.*
import org.csenseoss.kotlin.tests.assertions.primitives.charSequence.*
import org.junit.jupiter.api.*

class CoroutineScopeIOTest {
    @Test
    fun coroutineScopeAsyncIO(): Unit = runBlocking {
        val async = asyncIO {
            assertDispatcher(Dispatchers.IO)
            "test"
        }
        val value = async.await()
        value.assert("test")
    }

    @Nested
    inner class CoroutineScopeLaunchIOWith {

        @Test
        fun isRightReceiver() = runTest {
            assertCalled { shouldBeCalled: () -> Unit ->
                launchIOWith("test") {
                    assert("test")
                    shouldBeCalled()
                }.join()
            }
        }

        @Test
        fun isIOContext() = runTest {
            assertCalled { shouldBeCalled: () -> Unit ->
                launchIOWith("test") {
                    coroutineScope { assertDispatcher(Dispatchers.IO) }
                    shouldBeCalled()
                }.join()
            }
        }

    }


    @Nested
    inner class CoroutineScopeAsyncIOWith {

        @Test
        fun isRightReceiver() = runTest {
            assertCalled { shouldBeCalled: () -> Unit ->
                asyncIOWith("test") {
                    assert("test")
                    shouldBeCalled()
                }.await()
            }
        }

        @Test
        fun isIOContext() = runTest {
            assertCalled { shouldBeCalled: () -> Unit ->
                asyncIOWith("test") {
                    coroutineScope { assertDispatcher(Dispatchers.IO) }
                    shouldBeCalled()
                }.await()
            }
        }

        @Test
        fun returnsRightResult() = runTest {
            asyncIOWith("test") {
                42
            }.await().assert(42)
        }

    }

    @Test
    fun coroutineScopeLaunchIO() = runBlocking {
        assertCalled { shouldBeCalled: () -> Unit ->
            val async = launchIO {
                assertDispatcher(Dispatchers.IO)
                shouldBeCalled()
            }
            async.join()
        }
    }


    @Test
    fun coroutineScopeWithContextIO() = runBlocking {
        assertCalled { shouldBeCalled: () -> Unit ->
            val async = withContextIO {
                assertDispatcher(Dispatchers.IO)
                shouldBeCalled()
                "test"
            }
            async.assert("test")
        }
    }


}