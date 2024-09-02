@file:Suppress("unused", "NOTHING_TO_INLINE")

package org.csenseoss.kotlin.logger.operators

import org.csenseoss.kotlin.*
import org.csenseoss.kotlin.logger.*
import org.csenseoss.kotlin.logger.models.*


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
    logger: CLLogFunction = CL.logError,
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