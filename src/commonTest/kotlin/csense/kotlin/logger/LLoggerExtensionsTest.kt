@file:Suppress("unused")

package csense.kotlin.logger

import csense.kotlin.tests.assertions.assert
import kotlin.test.Test

class LLoggerExtensionsTest {

    class IterableTInvokeEachWithLoggingLazyTest {

        @Test
        fun empty() {
            var failedCounter = 0
            val loggers = mutableListOf<LoggingFunctionType<*>>()
            loggers.invokeEachWithLoggingLazy(
                "tag",
                { failedCounter += 1; "" }, // failTest("test") fails in js
                null
            )
//            failedCounter.assert(0, "should really be 0.")
        }

        @Test
        fun single() {
            var messageComputeTimes = 0
            var counter1 = 0
            val loggers: MutableList<LoggingFunctionType<*>> = mutableListOf(
                { _, _, _ ->
                    counter1 += 1
                })
            loggers.invokeEachWithLoggingLazy(
                "tag",
                { messageComputeTimes += 1;"" },
                null
            )

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
                },
                { _, _, _ ->
                    counter2 += 1
                }
            )
            loggers.invokeEachWithLoggingLazy(
                "tag",
                { messageComputeTimes += 1;"" },
                null
            )
            messageComputeTimes.assert(1)
            counter1.assert(1)
            counter2.assert(1)
        }


    }
}