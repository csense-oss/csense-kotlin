package csense.kotlin.patterns.restartableJob

import csense.kotlin.tests.assertions.*
import kotlinx.coroutines.*
import kotlinx.coroutines.test.*
import kotlin.test.*

class RestartableJobWith3ArgumentsTest {
    class Start {

        @Test
        fun First() {

        }


        @Test
        fun Second() {

        }


        @Test
        fun Third() {

        }

    }

    class CoroutineScopeRestartableJobIn {
//
//        @Test
//        fun CoroutineScopeRestartableJobIn() {
//            val input = CoroutineScope().restartableJobIn(CoroutineDispatcher())
//            val expected = RestartableJobWith3Arguments(CoroutineScope(), CoroutineDispatcher())
//            input
//        }
//
//
//        @Test
//        fun Dispatcher() {
//            val input = CoroutineScope().restartableJobIn(CoroutineDispatcher())
//            val expected = RestartableJobWith3Arguments(CoroutineScope(), CoroutineDispatcher())
//            input
//        }

    }

    class CoroutineScopeRestartableJobInDefault {

        @Test
        fun CoroutineScopeRestartableJobInDefault() = runTest {
            assertCalled { shouldBeCalled ->
                val job = restartableJobInDefault { first: String, second: Int, third: Boolean ->
                    first.assert("test")
                    second.assert(11)
                    third.assertFalse()
                    assertDispatcher(Dispatchers.Default)
                    shouldBeCalled()
                }
                job.start("test", 11, false)
                job.join()
            }
        }

    }

    class CoroutineScopeRestartableJobInMain {
        //TODO tested in JVM as main replacement is funcky on mpp
    }
}