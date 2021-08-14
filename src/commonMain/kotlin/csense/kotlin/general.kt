package csense.kotlin

import csense.kotlin.logger.*

/**
 * Indicates that something was unexpected (say an enum case, etc)
 *
 * @param message String
 * @return Nothing
 */
@Throws(UnexpectedException::class)
public fun unexpected(message: String = "Unexpected condition occurred."): Nothing {
    throw UnexpectedException(message)
}

@Throws(UnexpectedException::class)
public fun unexpectedWithLogging(
    message: String,
    logger: LoggingFunctionType<*> = L::error,
) {
    val exception = UnexpectedException(message)
    logger("Unexpected", message, exception)
    throw exception
}

@Throws(UnexpectedException::class)
public fun unexpectedWithLogging(
    tag: String,
    message: String,
    logger: LoggingFunctionType<*> = L::error,
) {
    val exception = UnexpectedException(message)
    logger(tag, message, exception)
    throw exception
}

/**
 * Indicates that a non reachable / fatal / unexpected action has occurred.
 */
public class UnexpectedException(message: String, cause: Throwable? = null) : Error(message, cause)