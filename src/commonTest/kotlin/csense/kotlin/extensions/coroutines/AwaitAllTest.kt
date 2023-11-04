@file:OptIn(ExperimentalCoroutinesApi::class, ExperimentalCsenseApi::class)
@file:Suppress("unused")

package csense.kotlin.extensions.coroutines

import csense.kotlin.annotations.*
import csense.kotlin.tests.assertions.*
import kotlinx.coroutines.*
import kotlinx.coroutines.test.*
import kotlin.test.*

class AwaitAllTest {
    class AwaitAllT1T2 {

        @Test
        fun sanity(): TestResult = runTest {
            val input: Pair<String, String> = awaitAll(CompletableDeferred("first"), CompletableDeferred("second"))
            input.first.assert("first")
            input.second.assert("second")
        }

        @Test
        fun awaitsAllSequential(): TestResult = runTest {
            val firstTask: Deferred<Int> = async {
                delay(timeMillis = 500)
                42
            }
            val secondTask: Deferred<String> = async {
                delay(timeMillis = 200)
                "42"
            }
            val input: Pair<Int, String> = awaitAll(firstTask, secondTask)
            currentTime.assertLargerOrEqualTo(500, "should not be completed before all tasks are done")
            input.first.assert(42)
            input.second.assert("42")
        }

    }

    class AwaitAllT1T2T3 {

        @Test
        fun sanity(): TestResult = runTest {
            val input: Triple<String, String, String> = awaitAll(
                CompletableDeferred("first"),
                CompletableDeferred("second"),
                CompletableDeferred("third")
            )
            input.first.assert("first")
            input.second.assert("second")
            input.third.assert("third")
        }

        @Test
        fun awaitsAllSequential() = runTest {
            val firstTask: Deferred<Int> = async {
                delay(timeMillis = 500)
                42
            }

            val secondTask: Deferred<String> = async {
                delay(timeMillis = 200)
                "42"
            }

            val thirdTask: Deferred<Long> = async {
                delay(timeMillis = 700)
                77L
            }
            val input: Triple<Int, String, Long> = awaitAll(firstTask, secondTask, thirdTask)
            currentTime.assertLargerOrEqualTo(
                expected = 700,
                optMessage = "should not be completed before all tasks are done"
            )
            input.first.assert(42)
            input.second.assert("42")
            input.third.assert(77L)
        }

    }
}