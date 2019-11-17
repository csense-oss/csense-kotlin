package csense.kotlin.exceptions

actual class NoStackTraceException actual constructor(message: String) : Exception(message, null, false, false)