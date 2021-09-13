package csense.kotlin

import csense.kotlin.logger.*

/**
 * Indicates that something was unexpected (say an enum case, etc.)
 *
 * @param message [String] a description of why this is unexpected
 * @param relatedCause [Throwable] in the case there are any causes related to this unexpected
 * @throws [UnexpectedException]
 */
@Throws(UnexpectedException::class)
public fun unexpected(
    message: String = "Unexpected condition occurred.",
    relatedCause: Throwable? = null
): Nothing {
    throw UnexpectedException(message, relatedCause)
}

/**
 * Indicates that something was unexpected (say an enum case, etc.) With logging
 * @param message [String] a description of why this is unexpected
 * @param logger [Function3]<[String], [String], [Throwable]?, *> The logger to use
 * @throws [UnexpectedException]
 */
@Throws(UnexpectedException::class)
public fun unexpectedWithLogging(
    message: String = "Unexpected condition occurred.",
    logger: LoggingFunctionType<*> = L::error,
    relatedCause: Throwable? = null
): Nothing = unexpectedWithLogging(
    tag = "Unexpected",
    message = message,
    logger = logger,
    relatedCause = relatedCause
)

/**
 * Indicates that something was unexpected (say an enum case, etc.) With logging
 * @param tag [String]
 * @param message [String] a description of why this is unexpected
 * @param logger [Function3]<[String], [String], [Throwable]?, *>
 * @throws [UnexpectedException]
 */
@Throws(UnexpectedException::class)
public fun unexpectedWithLogging(
    tag: String,
    message: String = "Unexpected condition occurred.",
    logger: LoggingFunctionType<*> = L::error,
    relatedCause: Throwable? = null
): Nothing {
    val exception = UnexpectedException(message, relatedCause)
    logger(tag, message, exception)
    throw exception
}

/**
 * Indicates that a non-reachable / fatal / unexpected action/event has occurred.
 */
public class UnexpectedException(message: String, relatedCause: Throwable? = null) : Error(message, relatedCause)