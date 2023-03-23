package csense.kotlin.logger

import csense.kotlin.extensions.mapping.*
import csense.kotlin.logger.extensions.*
import csense.kotlin.logger.models.*
import kotlinx.coroutines.channels.*
import kotlinx.coroutines.flow.*

public open class CsenseLogger(
    maxStoredLogMessages: Int = 100
) {

    public var isSensitiveLoggingEnabled: Boolean = false
        set(newValue) {
            field = newValue
            val logMessage: String = newValue.map(
                ifTrue = sensitiveLoggingEnabledMessage,
                ifFalse = sensitiveLoggingDisabledMessage
            )
            logDebug(tag = sensitiveLoggingTag, message = logMessage)
        }


    private val _loggers: MutableSharedFlow<LogMessage> = MutableSharedFlow(
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
        val logMessage: LogMessageFormat = sensitivity.toLogMessageFormatForLogging(
            mayLogSensitive = isSensitiveLoggingEnabled,
            message = message,
            placeholders = placeholders
        )
        logDebug(tag = tag, logMessage = logMessage, exception = exception)
    }

    public fun logDebug(
        tag: String,
        logMessage: LogMessageFormat,
        exception: Throwable?
    ) {
        log(LogMessage.Debug(tag = tag, message = logMessage, exception = exception))
    }

    public fun logWarning(
        tag: String,
        message: String,
        vararg placeholders: String,
        exception: Throwable? = null,
        sensitivity: LogSensitivity = LogSensitivity.Sensitive
    ) {
        val logMessage: LogMessageFormat = sensitivity.toLogMessageFormatForLogging(
            mayLogSensitive = isSensitiveLoggingEnabled,
            message = message,
            placeholders = placeholders
        )
        logWarning(tag = tag, logMessage = logMessage, exception = exception)
    }

    public fun logWarning(
        tag: String,
        logMessage: LogMessageFormat,
        exception: Throwable?
    ) {
        log(LogMessage.Warning(tag = tag, message = logMessage, exception = exception))
    }


    public fun logError(
        tag: String,
        message: String,
        vararg placeholders: String,
        exception: Throwable? = null,
        sensitivity: LogSensitivity = LogSensitivity.Sensitive
    ) {
        val logMessage: LogMessageFormat = sensitivity.toLogMessageFormatForLogging(
            mayLogSensitive = isSensitiveLoggingEnabled,
            message = message,
            placeholders = placeholders
        )
        logError(tag = tag, logMessage = logMessage, exception = exception)
    }

    public fun logError(
        tag: String,
        logMessage: LogMessageFormat,
        exception: Throwable?
    ) {
        log(LogMessage.Error(tag = tag, message = logMessage, exception = exception))
    }


    public fun log(message: LogMessage) {
        _loggers.tryEmit(message)
    }

    public companion object {
        public const val sensitiveLoggingTag: String = "CsenseLogger"
        public const val sensitiveLoggingEnabledMessage: String = "Sensitive logging enabled"
        public const val sensitiveLoggingDisabledMessage: String = "Sensitive logging enabled"
    }
}
