package csense.kotlin.logger

import csense.kotlin.logger.loggers.*
import csense.kotlin.logger.models.*
import csense.kotlin.tests.assertions.*
import kotlinx.coroutines.test.*
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