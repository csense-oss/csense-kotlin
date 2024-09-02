package org.csenseoss.kotlin.logger.operators

import org.csenseoss.kotlin.*
import org.csenseoss.kotlin.logger.*
import org.csenseoss.kotlin.logger.models.*


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