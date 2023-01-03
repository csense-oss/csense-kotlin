package csense.kotlin.logger.models

public enum class LogSensitivity {
    Sensitive,
    Insensitive
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