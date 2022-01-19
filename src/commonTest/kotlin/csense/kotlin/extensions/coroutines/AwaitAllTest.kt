@file:OptIn(ExperimentalCoroutinesApi::class, ExperimentalCsenseApi::class)

package csense.kotlin.extensions.coroutines

import csense.kotlin.annotations.*
import csense.kotlin.tests.assertions.*
import kotlinx.coroutines.*
import kotlinx.coroutines.test.*
import kotlin.test.*

class AwaitAllTest {
    class AwaitAllT1T2 {

        @Test
        fun sanity() = runTest {
            val input = awaitAll(CompletableDeferred("first"), CompletableDeferred("second"))
            input.first.assert("first")
            input.second.assert("second")
        }

        @Test
        fun awaitsAllSequential() = runTest {
            val firstTask = async {
                delay(500)
                42
            }
            val secondTask = async {
                delay(200)
                "42"
            }
            val input = awaitAll(firstTask, secondTask)
            currentTime.assertLargerOrEqualTo(500, "should not be completed before all tasks are done")
            input.first.assert(42)
            input.second.assert("42")
        }

    }

    class AwaitAllT1T2T3 {

        @Test
        fun sanity() = runTest {
            val input = awaitAll(
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
            val firstTask = async {
                delay(500)
                42
            }
            val secondTask = async {
                delay(200)
                "42"
            }
            val thirdTask = async {
                delay(700)
                77L
            }
            val input = awaitAll(firstTask, secondTask, thirdTask)
            currentTime.assertLargerOrEqualTo(700, "should not be completed before all tasks are done")
            input.first.assert(42)
            input.second.assert("42")
            input.third.assert(77L)
        }

    }
}