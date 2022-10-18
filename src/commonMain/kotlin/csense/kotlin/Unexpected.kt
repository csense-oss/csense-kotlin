package csense.kotlin

import csense.kotlin.annotations.sideEffect.*
import csense.kotlin.logger.*
import csense.kotlin.logger.models.*


/**
 * Indicates that something was unexpected (say an enum case, etc.)
 *
 * @param message [String] a description of why this is unexpected
 * @param relatedCause [Throwable] in the case there are any causes related to this unexpected
 * @throws [UnexpectedException]
 */
@Throws(UnexpectedException::class)
public fun unexpected(
    message: String = UnexpectedException.unexpectedDefaultMessage,
    relatedCause: Throwable? = null
): Nothing {
    throw UnexpectedException(message, relatedCause)
}


/**
 * Indicates that something was unexpected (say an enum case, etc.) With logging
 * @param message [String] a description of why this is unexpected
 * @param logger [Function3]<[String], [String], [Throwable]?, *> the logger that should be invoked before throwing.
 * @throws [UnexpectedException]
 */
@Throws(UnexpectedException::class)
public fun unexpectedWithLogging(
    message: String = UnexpectedException.unexpectedDefaultMessage,
    vararg placeholders: String = arrayOf(),
    relatedCause: Throwable? = null,
    sensitivity: LogSensitivity = LogSensitivity.Sensitive,
    logger: CLLogFunction = CL.error
): Nothing {
    unexpectedWithLogging(
        tag = UnexpectedException.unexpectedDefaultTag,
        message = message,
        placeholders = placeholders,
        relatedCause = relatedCause,
        sensitivity = sensitivity,
        logger = logger
    )
}

/**
 * Indicates that something was unexpected (say an enum case, etc.) With logging
 * @param tag [String] a user defined tag to use for the logging
 * @param message [String] a description of why this is unexpected
 * @param logger [Function3]<[String], [String], [Throwable]?, *> the logger that should be invoked before throwing.
 * @throws [UnexpectedException]
 */
@Throws(UnexpectedException::class)
public fun unexpectedWithLogging(
    tag: String = UnexpectedException.unexpectedDefaultTag,
    message: String = UnexpectedException.unexpectedDefaultMessage,
    vararg placeholders: String = arrayOf(),
    relatedCause: Throwable? = null,
    sensitivity: LogSensitivity = LogSensitivity.Sensitive,
    logger: CLLogFunction = CL.error
): Nothing {
    throw logUnexpected(
        tag = tag, message = message,
        placeholders = placeholders,
        relatedCause = relatedCause,
        sensitivity = sensitivity,
        logger = logger
    )
}

/**
 * Only logs the unexpected situation
 * @param message [String] a description of why this is unexpected
 * @param logger [Function3]<[String], [String], [Throwable]?, *> the logger that should be invoked
 * @return [UnexpectedException] the logged unexpected exception
 */
@DiscardableResult
public fun logUnexpected(
    tag: String = UnexpectedException.unexpectedDefaultTag,
    message: String = UnexpectedException.unexpectedDefaultMessage,
    vararg placeholders: String = arrayOf(),
    relatedCause: Throwable? = null,
    sensitivity: LogSensitivity = LogSensitivity.Sensitive,
    logger: CLLogFunction = CL.error
): UnexpectedException {
    val exception = UnexpectedException(message, relatedCause)
    logger(
        tag = tag,
        message = message,
        placeholders = placeholders,
        exception = exception,
        sensitivity = sensitivity
    )
    return exception
}

/**
 * Indicates that a non-reachable / fatal / unexpected action/event has occurred.
 */
public class UnexpectedException(
    message: String,
    relatedCause: Throwable? = null
) : Error(message, relatedCause) {
    public companion object {
        public const val unexpectedDefaultTag: String = "Unexpected"
        public const val unexpectedDefaultMessage: String = "Unexpected"
    }
}

