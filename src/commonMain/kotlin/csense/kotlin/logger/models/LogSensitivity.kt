package csense.kotlin.logger.models

public enum class LogSensitivity {
    Sensitive,
    Insensitive
}

public inline fun LogSensitivity.isSensitive(): Boolean =
    this != LogSensitivity.Insensitive

