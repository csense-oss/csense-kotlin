package csense.kotlin.logger

import kotlinx.coroutines.channels.*
import kotlinx.coroutines.flow.*


public class CsenseLogger(
    maxStoredLogMessages: Int = 100
) {

    private var mayLogSensitive = false

    private val logs = MutableSharedFlow<LogMessage>(
        extraBufferCapacity = maxStoredLogMessages,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    public val allLogs: Flow<LogMessage> = logs

    public val debugLogs: Flow<LogMessage.Debug> = logs.filterIsInstance()

    public val warningLogs: Flow<LogMessage.Warning> = logs.filterIsInstance()

    public val errorLogs: Flow<LogMessage.Error> = logs.filterIsInstance()


    public fun logDebug(
        tag: String,
        message: String,
        vararg placeholders: String,
        throwable: Throwable? = null,
        privacy: LogSensitivity = LogSensitivity.Sensitive
    ) {
        val messageFormat = privacy.toLogMessageFormat(message, placeholders, mayLogSensitive = mayLogSensitive)
        logs.tryEmit(
            LogMessage.Debug(tag, messageFormat, throwable = throwable)
        )
    }

    public fun logWarning(
        tag: String,
        message: String,
        vararg placeholders: String,
        throwable: Throwable? = null,
        privacy: LogSensitivity = LogSensitivity.Sensitive
    ) {
        val messageFormat = privacy.toLogMessageFormat(message, placeholders, mayLogSensitive = mayLogSensitive)
        logs.tryEmit(
            LogMessage.Warning(tag, messageFormat, throwable = throwable)
        )
    }

    public fun logError(
        tag: String,
        message: String,
        vararg placeholders: String,
        throwable: Throwable? = null,
        privacy: LogSensitivity = LogSensitivity.Sensitive
    ) {
        val messageFormat = privacy.toLogMessageFormat(message, placeholders, mayLogSensitive = mayLogSensitive)
        logs.tryEmit(
            LogMessage.Error(tag, messageFormat, throwable = throwable)
        )
    }

    public fun enableSensitiveLogging() {
        mayLogSensitive = true
    }

}

public val CLogger: CsenseLogger by lazy {
    CsenseLogger(maxStoredLogMessages = 100)
}