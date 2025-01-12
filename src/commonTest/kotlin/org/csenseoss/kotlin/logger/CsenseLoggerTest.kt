package org.csenseoss.kotlin.logger

import kotlinx.coroutines.test.*
import org.csenseoss.kotlin.logger.loggers.*
import org.csenseoss.kotlin.logger.models.*
import org.csenseoss.kotlin.tests.assertions.*
import org.csenseoss.kotlin.tests.assertions.comparable.*
import org.csenseoss.kotlin.tests.assertions.coroutines.flow.*
import org.csenseoss.kotlin.tests.assertions.exceptions.*
import org.csenseoss.kotlin.tests.assertions.primitives.boolean.*
import kotlin.test.*

class CsenseLoggerTest {

    class Log {
        @Test
        fun getsSameMessageBack() = runTest {
            val tag = "tag"
            val message = "message"

            val throwable = RuntimeException("message")
            val csenseLogger = SharedFlowLogMessageLogger()


            var didCall = false
            testFlow(
                collectAction = {
                    val logMessage = csenseLogger.allLoggers.awaitNextItem()
                    logMessage.tag.assert(tag)
                    logMessage.message.message.assert(message)
                    logMessage.exception.assert(throwable)
                    didCall = true
                },
                sendAction = {
                    csenseLogger.log(
                        LogMessage.Warning(
                            tag = tag,
                            message = LogMessageFormat.InsensitiveValues(
                                message = message,
                                placeholders = arrayOf(),
                                expectedSensitivity = LogSensitivity.Insensitive
                            ),
                            exception = throwable
                        )
                    )
                }
            ).join()
            didCall.assertTrue()
        }
    }
}