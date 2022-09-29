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
        val messageFormat = sensitivity.toLogMessageFormat(message, placeholders, mayLogSensitive = mayLogSensitive)
        log(LogMessage.Debug(tag, messageFormat, throwable = exception))
    }

    public fun logWarning(
        tag: String,
        message: String,
        vararg placeholders: String,
        exception: Throwable? = null,
        sensitivity: LogSensitivity = LogSensitivity.Sensitive
    ) {
        val messageFormat = sensitivity.toLogMessageFormat(message, placeholders, mayLogSensitive = mayLogSensitive)
        log(LogMessage.Warning(tag, messageFormat, throwable = exception))
    }

    public fun logError(
        tag: String,
        message: String,
        vararg placeholders: String,
        exception: Throwable? = null,
        sensitivity: LogSensitivity = LogSensitivity.Sensitive
    ) {
        val messageFormat = sensitivity.toLogMessageFormat(message, placeholders, mayLogSensitive = mayLogSensitive)
        log(LogMessage.Error(tag, messageFormat, throwable = exception))
    }

    public fun log(message: LogMessage) {
        _loggers.tryEmit(message)
    }


    public fun enableSensitiveLogging() {
        mayLogSensitive = true
        logDebug(sensitiveLoggingTag, sensitiveLoggingEnabledMessage)
    }

    public fun disableSensitiveLogging() {
        mayLogSensitive = false
        logDebug(sensitiveLoggingTag, sensitiveLoggingDisabledMessage)
    }

    public val isSensitiveLoggingEnabled: Boolean
        get() = mayLogSensitive


    public companion object {
        public const val sensitiveLoggingTag: String = "CsenseLogger"
        public const val sensitiveLoggingEnabledMessage: String = "Sensitive logging enabled"
        public const val sensitiveLoggingDisabledMessage: String = "Sensitive logging enabled"
    }
}

public object CL : CsenseLogger(maxStoredLogMessages = 100) {
    public val debug: CLLogFunction = CLLogFunctionCallToMethod(this::logDebug)
    public val warning: CLLogFunction = CLLogFunctionCallToMethod(this::logWarning)
    public val error: CLLogFunction = CLLogFunctionCallToMethod(this::logError)
}