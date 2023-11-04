package csense.kotlin.extensions.coroutines.coroutineScope

import csense.kotlin.tests.assertions.*
import kotlinx.coroutines.*
import kotlinx.coroutines.test.*
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
    @Test
    fun todoMain(){
        TODO()
    }
}