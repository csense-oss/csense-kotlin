package org.csenseoss.kotlin.extensions.coroutines.coroutineScope

import csense.kotlin.tests.assertions.*
import kotlinx.coroutines.*
import kotlinx.coroutines.test.*
import kotlin.test.*

class AsyncTest {
    @Test
    fun coroutineScopeAsyncDefault(): TestResult = runTest {
        val async: Deferred<String> = asyncDefault {
            assertDispatcher(Dispatchers.Default)
            "result"
        }
        async.await().assert("result")
    }

    class CoroutineScopeAsyncDefaultWith {

        @Test
        fun isRightReceiver(): TestResult = runTest {
            assertCalled { shouldBeCalled: () -> Unit ->
                asyncDefaultWith("test") {
                    assert("test")
                    shouldBeCalled()
                }.await()
            }
        }

        @Test
        fun isDefaultContext(): TestResult = runTest {
            assertCalled { shouldBeCalled: () -> Unit ->
                asyncDefaultWith("test") {
                    coroutineScope { assertDispatcher(Dispatchers.Default) }
                    shouldBeCalled()
                }.await()
            }
        }

        @Test
        fun returnsRightResult(): TestResult = runTest {
            asyncDefaultWith("test") {
                42
            }.await().assert(42)
        }

    }

    class CoroutineScopeAsyncMainWith {
        @Test
        fun isRightReceiver(): TestResult = runTestForMainDispatcher {
            assertCalled { shouldBeCalled: () -> Unit ->
                asyncMainWith("test") {
                    assert("test")
                    shouldBeCalled()
                }.await()
            }
        }

        @Test
        fun isMainContext() = runTestForMainDispatcherAssertCalled { shouldBeCalled: () -> Unit ->
            asyncMainWith("test") {
                coroutineScope { assertDispatcher(Dispatchers.Main) }
                shouldBeCalled()
            }.await()
        }
    }

}