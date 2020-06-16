@file:Suppress("unused", "NOTHING_TO_INLINE")

package csense.kotlin.extensions

import csense.kotlin.EmptyFunctionResult
import csense.kotlin.logger.L
import csense.kotlin.logger.LoggingFunctionType


/**
 * Tries the given operation, and if fails then it logs it and returns null
 * @param title [String]
 * @param message [String]
 * @param logger [LoggingFunctionType]<*>
 * @param throwableAction [EmptyFunctionResult]<T>
 * @return T?
 */
inline fun <T> tryAndLog(
        title: String = "",
        message: String = "",
        logger: LoggingFunctionType<*> = L::error,
        throwableAction: EmptyFunctionResult<T>
): T? {
    return try {
        throwableAction()
    } catch (e: Throwable) {
        logger(title, message, e)
        return null
    }
}

/**
 *
 * @receiver [Throwable]
 * @param lineSeparator [String]
 * @param indentation [String]
 */
inline fun Throwable.toPrettyString(
        lineSeparator: String = "\n",
        indentation: String = "\t"
): String {
    val builder = StringBuilder()
            .append(message ?: "No message", lineSeparator)

    var currentException = cause
    while (currentException != null) {
        builder.append(indentation, currentException.message, lineSeparator)
        currentException = currentException.cause
    }
    return builder.append(lineSeparator).toString()
}