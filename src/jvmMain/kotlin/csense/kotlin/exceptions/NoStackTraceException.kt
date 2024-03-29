@file:Suppress("unused")
package csense.kotlin.exceptions

/**
 * An exception without any stacktrace thus it's a lot cheaper to create than regular exceptions
 */
public class NoStackTraceException(
    message: String
) : Exception(
    message,
    null, // cause
    false, // suppression
    false // write stack trace
)