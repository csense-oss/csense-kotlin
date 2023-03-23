package csense.kotlin.logger

import csense.kotlin.logger.models.*
import csense.kotlin.tests.assertions.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.test.*
import kotlin.test.*

class CsenseLoggerTest {

    @Test
    fun defaultSensitiveLoggingShouldBeDisabled() {
        val csenseLogger = CsenseLogger()
        csenseLogger.isSensitiveLoggingEnabled.assertFalse()
    }

    @Test
    fun enableSensitiveLogging() {
        val csenseLogger = CsenseLogger()
        csenseLogger.disableSensitiveLogging()
        csenseLogger.isSensitiveLoggingEnabled.assertFalse()
        csenseLogger.enableSensitiveLogging()
        csenseLogger.isSensitiveLoggingEnabled.assertTrue()
    }


    @Test
    fun disableSensitiveLogging() {
        val csenseLogger = CsenseLogger()
        csenseLogger.enableSensitiveLogging()
        csenseLogger.isSensitiveLoggingEnabled.assertTrue()
        csenseLogger.disableSensitiveLogging()
        csenseLogger.isSensitiveLoggingEnabled.assertFalse()
    }

    @Test
    fun isSensitiveLoggingEnabled() {
        val csenseLogger = CsenseLogger()
        csenseLogger.disableSensitiveLogging()
        csenseLogger.isSensitiveLoggingEnabled.assertFalse()
        csenseLogger.enableSensitiveLogging()
        csenseLogger.isSensitiveLoggingEnabled.assertTrue()
        csenseLogger.disableSensitiveLogging()
        csenseLogger.isSensitiveLoggingEnabled.assertFalse()
    }

    class Log {
        @Test
        fun getsSameMessageBack() = runTest {
            val tag = "tag"
            val message = "message"

            val throwable = RuntimeException("message")
            val csenseLogger = CsenseLogger()


            var didCall = false
            testFlow(
                collectAction = {
                    val logMessage = csenseLogger.allLoggers.awaitNextItem()
                    logMessage.tag.assert(tag)
                    logMessage.message.message.assert(message)
                    logMessage.throwable.assert(throwable)
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
                            throwable = throwable
                        )
                    )
                }
            ).join()
            didCall.assertTrue()
        }
    }
}

//TODO csense tests
fun CoroutineScope.testFlow(
    collectAction: suspend () -> Unit,
    sendAction: suspend () -> Unit
): Job = launch {

    launch(start = CoroutineStart.UNDISPATCHED, context = Dispatchers.Unconfined) {
        collectAction()
    }
    launch { sendAction() }.join()
}

//TODO csense tests
suspend fun <T> Flow<T>.awaitNextItem(): T {
    return first { it: T ->
        true
    }
}