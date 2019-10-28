@file:Suppress("unused")

package csense.kotlin.logger

import csense.kotlin.test.assertions.assert
import csense.kotlin.test.assertions.failTest
import kotlin.test.Test

class LLoggerExtensionsTest {

    object IterableTInvokeEachWithLoggingLazyTest {

        @Test
        fun empty() {
            val loggers = mutableListOf<LoggingFunctionType<*>>()
            loggers.invokeEachWithLoggingLazy(
                    "tag",
                    { failTest() },
                    null)
        }

        @Test
        fun single() {
            var messageComputeTimes = 0
            var counter1 = 0
            val loggers: MutableList<LoggingFunctionType<*>> = mutableListOf(
                    { _, _, _ ->
                        counter1 += 1
                        Unit
                    })
            loggers.invokeEachWithLoggingLazy(
                    "tag",
                    { messageComputeTimes += 1;"" },
                    null)

            messageComputeTimes.assert(1)
            counter1.assert(1)
        }

        @Test
        fun multiple() {
            var messageComputeTimes = 0
            var counter1 = 0
            var counter2 = 0

            val loggers: MutableList<LoggingFunctionType<*>> = mutableListOf(
                    { _, _, _ ->
                        counter1 += 1
                        Unit
                    },
                    { _, _, _ ->
                        counter2 += 1
                        Unit
                    }
            )
            loggers.invokeEachWithLoggingLazy(
                    "tag",
                    { messageComputeTimes += 1;"" },
                    null)
            messageComputeTimes.assert(1)
            counter1.assert(1)
            counter2.assert(1)
        }


    }
}