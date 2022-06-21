@file:Suppress("unused", "NOTHING_TO_INLINE", "INVISIBLE_MEMBER", "INVISIBLE_REFERENCE")

package csense.kotlin.extensions

import csense.kotlin.*
import csense.kotlin.logger.*
import kotlin.internal.*


/**
 * Tries the given operation, and if fails then it logs it and returns null
 * @param title [String]
 * @param message [String]
 * @param logger [LoggingFunctionType]<*>
 * @param throwableAction [EmptyFunctionResult]<T>
 * @return T?
 */
@LowPriorityInOverloadResolution
@Deprecated("use CL version instead")
public inline fun <T> tryAndLog(
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
 * Tries the given operation, and if fails then it logs it and returns null
 * @param tag [String]
 * @param message [String]
 * @param placeholders [Array<String>]
 * @param logger [CLLogFunction]
 * @param sensitivity [LogSensitivity]
 * @param throwableAction [EmptyFunctionResult]<T>
 * @return T?
 */
public inline fun <T> tryAndLog(
    tag: String = "",
    message: String = "",
    placeholders: Array<String> = arrayOf(),
    logger: CLLogFunction = CL::logError,
    sensitivity: LogSensitivity = LogSensitivity.Sensitive,
    throwableAction: EmptyFunctionResult<T>
): T? {
    return try {
        throwableAction()
    } catch (exception: Throwable) {
        logger(tag, message, placeholders, exception, sensitivity)
        return null
    }
}

/**
 *
 * @receiver [Throwable]
 * @param lineSeparator [String]
 * @param indentation [String]
 */
@Deprecated("Misleading name. does not what it states nor does it a nice job at it either.")
public inline fun Throwable.messagesToPrettyString(
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