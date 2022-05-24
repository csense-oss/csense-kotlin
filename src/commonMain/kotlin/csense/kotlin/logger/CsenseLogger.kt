package csense.kotlin.logger

import kotlinx.coroutines.channels.*
import kotlinx.coroutines.flow.*


public class CsenseLogger(
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
        throwable: Throwable? = null,
        sensitivity: LogSensitivity = LogSensitivity.Sensitive
    ) {
        val messageFormat = sensitivity.toLogMessageFormat(message, placeholders, mayLogSensitive = mayLogSensitive)
        log(LogMessage.Debug(tag, messageFormat, throwable = throwable))
    }

    public fun logWarning(
        tag: String,
        message: String,
        vararg placeholders: String,
        throwable: Throwable? = null,
        sensitivity: LogSensitivity = LogSensitivity.Sensitive
    ) {
        val messageFormat = sensitivity.toLogMessageFormat(message, placeholders, mayLogSensitive = mayLogSensitive)
        log(LogMessage.Warning(tag, messageFormat, throwable = throwable))
    }

    public fun logError(
        tag: String,
        message: String,
        vararg placeholders: String,
        throwable: Throwable? = null,
        sensitivity: LogSensitivity = LogSensitivity.Sensitive
    ) {
        val messageFormat = sensitivity.toLogMessageFormat(message, placeholders, mayLogSensitive = mayLogSensitive)
        log(LogMessage.Error(tag, messageFormat, throwable = throwable))
    }

    public fun log(message: LogMessage) {
        _loggers.tryEmit(message)
    }


    public fun enableSensitiveLogging() {
        mayLogSensitive = true
    }

}

public val CL: CsenseLogger by lazy {
    CsenseLogger(maxStoredLogMessages = 100)
}