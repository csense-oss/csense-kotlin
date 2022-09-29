@file:Suppress("unused", "NOTHING_TO_INLINE", "INVISIBLE_MEMBER", "INVISIBLE_REFERENCE")

package csense.kotlin.extensions

import csense.kotlin.*
import csense.kotlin.logger.*
import csense.kotlin.logger.models.*
import csense.kotlin.patterns.expected.*
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
@Suppress("DEPRECATION", "MissingTestFunction")
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
    placeholders: Array<String> = emptyArray(),
    logger: CLLogFunction = CL.error,
    sensitivity: LogSensitivity = LogSensitivity.Sensitive,
    throwableAction: EmptyFunctionResult<T>
): T? {
    return try {
        throwableAction()
    } catch (exception: Throwable) {
        logger(
            tag = tag,
            message = message,
            placeholders = placeholders,
            exception = exception,
            sensitivity = sensitivity
        )
        return null
    }
}

/**
 * Try's the given [throwableAction]. if it fails it will be logged (via [logger]) and the result will be an [Expected.Failed]
 * @param tag String
 * @param message String
 * @param placeholders Array<String>
 * @param logger CLLogFunction
 * @param sensitivity LogSensitivity
 * @param throwableAction Function0<T>
 * @return Expected<T, Throwable>
 */
public inline fun <T> tryAndLogExpected(
    tag: String = "",
    message: String = "",
    placeholders: Array<String> = emptyArray(),
    logger: CLLogFunction = CL.error,
    sensitivity: LogSensitivity = LogSensitivity.Sensitive,
    throwableAction: EmptyFunctionResult<T>
): Expected<T, Throwable> = expectedCatching {
    throwableAction().asSuccess()
}.applyIfFailed {
    logger(
        tag = tag,
        message = message,
        placeholders = placeholders,
        exception = this.error,
        sensitivity = sensitivity
    )
}


public inline fun <T> tryAndLogDidSucceed(
    tag: String = "",
    message: String = "",
    placeholders: Array<String> = emptyArray(),
    logger: CLLogFunction = CL.error,
    sensitivity: LogSensitivity = LogSensitivity.Sensitive,
    throwableAction: EmptyFunction
): Boolean {
    return try {
        throwableAction()
        true
    } catch (exception: Throwable) {
        logger(
            tag = tag,
            message = message,
            placeholders = placeholders,
            exception = exception,
            sensitivity = sensitivity
        )
        return false
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