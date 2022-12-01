package csense.kotlin.patterns.restartableJob

import csense.kotlin.patterns.restartableJob.arguments.*
import csense.kotlin.tests.assertions.*
import kotlinx.coroutines.*
import kotlinx.coroutines.test.*
import kotlin.test.*

class RestartableJobWithArgumentTest {

    @Test
    fun start() {

    }


    class CoroutineScopeRestartableJobIn {
//
//        @Test
//        fun CoroutineScopeRestartableJobIn() {
//            val input = CoroutineScope().restartableJobIn(CoroutineDispatcher())
//            val expected = RestartableJobWithArgument(CoroutineScope(), CoroutineDispatcher())
//            input
//        }
//
//
//        @Test
//        fun Dispatcher() {
//            val input = CoroutineScope().restartableJobIn(CoroutineDispatcher())
//            val expected = RestartableJobWithArgument(CoroutineScope(), CoroutineDispatcher())
//            input
//        }

    }

    class CoroutineScopeRestartableJobInDefault {

        @Test
        fun coroutineScopeRestartableJobInDefault() = runTest {
            assertCalled { shouldBeCalled ->
                val job = restartableJobInDefault { first: String ->
                    first.assert("test")
                    assertDispatcher(Dispatchers.Default)
                    shouldBeCalled()
                }
                job.start("test")
                job.join()
            }
        }

    }

    class CoroutineScopeRestartableJobInMain {
        //TODO tested in JVM as main replacement is funcky on mpp
    }
}