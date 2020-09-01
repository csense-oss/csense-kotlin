package csense.kotlin.exceptions

import kotlin.Exception

/**
 * An exception without any stacktrace thus its a lot cheaper to create than regular exceptions
 */
public class NoStackTraceException(
        message: String
) : Exception(
        message,
        null, // cause
        false, // suppression
        false // write stack trace
)