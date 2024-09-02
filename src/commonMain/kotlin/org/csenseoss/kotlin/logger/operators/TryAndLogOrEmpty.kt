package org.csenseoss.kotlin.logger.operators

import org.csenseoss.kotlin.*
import org.csenseoss.kotlin.logger.*
import org.csenseoss.kotlin.logger.models.*


public inline fun <T> tryAndLogOrEmpty(
    tag: String = "",
    message: String = "",
    placeholders: Array<String> = emptyArray(),
    logger: CLLogFunction = CL.logError,
    sensitivity: LogSensitivity = LogSensitivity.Sensitive,
    throwableAction: EmptyFunctionResult<List<T>>
): List<T> = tryAndLog(
    tag = tag,
    message = message,
    placeholders = placeholders,
    logger = logger,
    sensitivity = sensitivity,
    throwableAction = throwableAction
) ?: emptyList()