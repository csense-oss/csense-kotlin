@file:Suppress("unused", "NOTHING_TO_INLINE")

package org.csenseoss.kotlin.logger.operators

import org.csenseoss.kotlin.*
import org.csenseoss.kotlin.logger.*
import org.csenseoss.kotlin.logger.models.*
import org.csenseoss.kotlin.patterns.expected.*
import org.csenseoss.kotlin.patterns.expected.operators.*
import org.csenseoss.kotlin.*
import org.csenseoss.kotlin.patterns.expected.*
import org.csenseoss.kotlin.patterns.expected.operators.*


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
    logger: CLLogFunction = CL.logError,
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
    logger: CLLogFunction = CL.logError,
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