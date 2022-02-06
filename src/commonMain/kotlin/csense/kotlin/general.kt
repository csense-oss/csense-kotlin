@file:OptIn(ExperimentalContracts::class)
@file:Suppress("Annotator")

package csense.kotlin

import csense.kotlin.annotations.sideEffect.*
import csense.kotlin.logger.*
import kotlin.contracts.*

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
 * Defaults the tag to "Unexpected"
 * @param message [String] a description of why this is unexpected
 * @param logger [Function3]<[String], [String], [Throwable]?, *> The logger to use
 * @throws [UnexpectedException]
 */
@Throws(UnexpectedException::class)
public fun unexpectedWithLogging(
    message: String = UnexpectedException.unexpectedDefaultMessage,
    logger: LoggingFunctionType<*> = L::error,
    relatedCause: Throwable? = null
): Nothing {
    contract {
        callsInPlace(logger, InvocationKind.EXACTLY_ONCE)
    }
    unexpectedWithLogging(
        tag = UnexpectedException.unexpectedDefaultTag,
        message = message,
        logger = logger,
        relatedCause = relatedCause
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
    tag: String,
    message: String = UnexpectedException.unexpectedDefaultMessage,
    logger: LoggingFunctionType<*> = L::error,
    relatedCause: Throwable? = null
): Nothing {
    contract {
        callsInPlace(logger, InvocationKind.EXACTLY_ONCE)
    }
    val exception = logUnexpected(tag, message, logger, relatedCause)
    throw exception
}

/**
 * Only logs the unexpected situation
 * @param message [String] a description of why this is unexpected
 * @param logger [Function3]<[String], [String], [Throwable]?, *> the logger that should be invoked
 * @return [UnexpectedException] the logged unexpected exception
 */
@DiscardableResult
public fun logUnexpected(
    message: String = UnexpectedException.unexpectedDefaultMessage,
    logger: LoggingFunctionType<*> = L::error,
    relatedCause: Throwable? = null
): UnexpectedException {
    contract {
        callsInPlace(logger, InvocationKind.EXACTLY_ONCE)
    }
    return logUnexpected(UnexpectedException.unexpectedDefaultTag, message, logger, relatedCause)
}


/**
 * Only logs the unexpected situation
 * @param message [String] a description of why this is unexpected
 * @param logger [Function3]<[String], [String], [Throwable]?, *> the logger that should be invoked
 * @return [UnexpectedException] the logged unexpected exception
 */
@DiscardableResult
public fun logUnexpected(
    tag: String,
    message: String = UnexpectedException.unexpectedDefaultMessage,
    logger: LoggingFunctionType<*> = L::error,
    relatedCause: Throwable? = null
): UnexpectedException {
    contract {
        callsInPlace(logger, InvocationKind.EXACTLY_ONCE)
    }
    val exception = UnexpectedException(message, relatedCause)
    logger(tag, message, exception)
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