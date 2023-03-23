package csense.kotlin.logger

import csense.kotlin.logger.models.*
import kotlinx.coroutines.channels.*
import kotlinx.coroutines.flow.*

public open class CsenseLogger(
    maxStoredLogMessages: Int = 100
) {

    private var mayLogSensitive = false

    private val _loggers = MutableSharedFlow<LogMessage>(
        extraBufferCapacity = maxStoredLogMessages,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    public val allLoggers: Flow<LogMessage> = _loggers

    public val debugLoggers: Flow<LogMessage.Debug> = _loggers.filterIsInstance()

    public val warningLoggers: Flow<LogMessage.Warning> = _loggers.filterIsInstance()

    public val errorLoggers: Flow<LogMessage.Error> = _loggers.filterIsInstance()


    public fun logDebug(
        tag: String,
        message: String,
        vararg placeholders: String,
        exception: Throwable? = null,
        sensitivity: LogSensitivity = LogSensitivity.Sensitive
    ) {
        val messageFormat = sensitivity.toLogMessageFormatForLogging(message, placeholders)
        log(LogMessage.Debug(tag = tag, message = messageFormat, throwable = exception))
    }

    public fun logWarning(
        tag: String,
        message: String,
        vararg placeholders: String,
        exception: Throwable? = null,
        sensitivity: LogSensitivity = LogSensitivity.Sensitive
    ) {
        val messageFormat = sensitivity.toLogMessageFormatForLogging(message, placeholders)
        log(LogMessage.Warning(tag = tag, message = messageFormat, throwable = exception))
    }

    public fun logError(
        tag: String,
        message: String,
        vararg placeholders: String,
        exception: Throwable? = null,
        sensitivity: LogSensitivity = LogSensitivity.Sensitive
    ) {
        val messageFormat = sensitivity.toLogMessageFormatForLogging(message, placeholders)
        log(LogMessage.Error(tag = tag, message = messageFormat, throwable = exception))
    }

    public fun log(message: LogMessage) {
        _loggers.tryEmit(message)
    }


    public fun enableSensitiveLogging() {
        mayLogSensitive = true
        logDebug(tag = sensitiveLoggingTag, message = sensitiveLoggingEnabledMessage)
    }

    public fun disableSensitiveLogging() {
        mayLogSensitive = false
        logDebug(tag = sensitiveLoggingTag, message = sensitiveLoggingDisabledMessage)
    }

    public val isSensitiveLoggingEnabled: Boolean
        get() = mayLogSensitive



    //TODO consider this..
    private fun LogSensitivity.toLogMessageFormatForLogging(
        message: String,
        placeholders: Array<out String>
    ): LogMessageFormat {
        if (mayLogSensitive) {
            return LogMessageFormat.InsensitiveValues(
                message = message,
                placeholders = placeholders,
                expectedSensitivity = this
            )
        }
        return toLogMessageFormat(message = message, placeholders = placeholders)
    }

    public companion object {
        public const val sensitiveLoggingTag: String = "CsenseLogger"
        public const val sensitiveLoggingEnabledMessage: String = "Sensitive logging enabled"
        public const val sensitiveLoggingDisabledMessage: String = "Sensitive logging enabled"
    }
}
