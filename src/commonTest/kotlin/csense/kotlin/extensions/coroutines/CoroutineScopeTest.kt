@file:OptIn(ExperimentalCoroutinesApi::class)

package csense.kotlin.extensions.coroutines

import csense.kotlin.tests.assertions.*
import kotlinx.coroutines.*
import kotlinx.coroutines.test.*
import kotlin.jvm.*
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
//
//    @Test
//    fun coroutineScopeWithContextMain() = runTest {
//        withContextMain {
//            assertDispatcher(Dispatchers.Main)
//            "result"
//        }.assert("result")
//        mainThreadSurrogate.scheduler.runCurrent()
//    }
//
//    @Test
//    fun coroutineScopeLaunchMain() = runTest {
//        assertCalled { shouldBeCalled ->
//            launchMain {
//                assertDispatcher(Dispatchers.Main)
//                shouldBeCalled()
//            }.join()
//        }
//        mainThreadSurrogate.scheduler.runCurrent()
//    }
//
//    @Test
//    fun coroutineScopeAsyncMain() = runTest {
//        val async = asyncMain {
//            assertDispatcher(Dispatchers.Main)
//            "result"
//        }
//        async.await().assert("result")
//        mainThreadSurrogate.scheduler.runCurrent()
//    }

}