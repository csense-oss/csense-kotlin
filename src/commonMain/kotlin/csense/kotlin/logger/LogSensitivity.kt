package csense.kotlin.logger

public enum class LogSensitivity {
    Sensitive,
    Insensitive
}

public fun LogSensitivity.toLogMessageFormat(
    message: String,
    placeholders: Array<out String>,
    mayLogSensitive: Boolean
): LogMessageFormat {
    if (mayLogSensitive) {
        return LogMessageFormat.InsensitiveValues(message, placeholders, this)
    }
    return toLogMessageFormat(message, placeholders)
}

public fun LogSensitivity.toLogMessageFormat(
    message: String,
    placeholders: Array<out String>
): LogMessageFormat = when (this) {
    LogSensitivity.Sensitive -> LogMessageFormat.SensitiveValues(message)
    LogSensitivity.Insensitive -> LogMessageFormat.InsensitiveValues(message, placeholders, LogSensitivity.Insensitive)
}