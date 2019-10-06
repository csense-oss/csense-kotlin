package csense.kotlin.exceptions

/**
 * A exception that on the platform should avoid recording the stack trace
 */
expect class NoStackTraceException(message: String) : Exception