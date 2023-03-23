package csense.kotlin.logger.extensions

import csense.kotlin.logger.models.*


public fun LogSensitivity.stackTraceOf(throwable: Throwable): String = when (this) {
    LogSensitivity.Sensitive -> throwable.senstiveStackTraceToString()
    LogSensitivity.Insensitive -> throwable.stackTraceToString()
}

public fun LogSensitivity.stackTraceOfOrNull(throwable: Throwable?): String? {
    throwable ?: return null
    return stackTraceOf(throwable)
}

public fun LogSensitivity.toLogMessageFormat(
    message: String,
    placeholders: Array<out String>
): LogMessageFormat = when (this) {
    LogSensitivity.Sensitive -> LogMessageFormat.SensitiveValues(message = message)
    LogSensitivity.Insensitive -> LogMessageFormat.InsensitiveValues(
        message = message,
        placeholders = placeholders,
        expectedSensitivity = LogSensitivity.Insensitive
    )
}

public fun LogSensitivity.toLogMessageFormatForLogging(
    mayLogSensitive: Boolean,
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