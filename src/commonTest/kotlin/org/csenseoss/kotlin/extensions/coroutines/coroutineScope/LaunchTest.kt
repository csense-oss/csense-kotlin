package org.csenseoss.kotlin.extensions.coroutines.coroutineScope

import kotlinx.coroutines.*
import kotlinx.coroutines.test.*
import org.csenseoss.kotlin.tests.assertions.coroutines.*
import org.csenseoss.kotlin.tests.assertions.general.*
import org.csenseoss.kotlin.tests.assertions.primitives.charSequence.*
import kotlin.test.*

class LaunchTest {

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


    class CoroutineScopeLaunchMainWith {

        @Test
        fun isRightReceiver() = runTestForMainDispatcherAssertCalled { shouldBeCalled: () -> Unit ->
            launchMainWith("test") {
                assert("test")
                shouldBeCalled()
            }.join()
        }

        @Test
        fun isMainContext() = runTestForMainDispatcher {
            assertCalled { shouldBeCalled: () -> Unit ->
                launchDefaultWith("test") {
                    coroutineScope { assertDispatcher(Dispatchers.Default) }
                    shouldBeCalled()
                }.join()
            }
        }
    }

}