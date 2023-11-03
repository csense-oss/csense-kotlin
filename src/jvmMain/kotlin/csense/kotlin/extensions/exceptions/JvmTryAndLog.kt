@file:Suppress("unused", "INVISIBLE_MEMBER", "INVISIBLE_REFERENCE")

package csense.kotlin.extensions.exceptions

import csense.kotlin.*
import csense.kotlin.logger.*
import csense.kotlin.logger.models.*
import csense.kotlin.logger.operators.*
import kotlin.reflect.*

/**
 * Tries the given operation, and if fails then it logs it and returns null
 * @param clazz [Class]<*>
 * @param message [String]
 * @param logger [CLLogFunction]<*>
 * @param sensitivity [LogSensitivity]
 * @param throwableAction [EmptyFunctionResult]<T>
 * @return T?
 */
public inline fun <T> tryAndLog(
    clazz: Class<*>,
    message: String = "",
    placeholders: Array<String> = arrayOf(),
    logger: CLLogFunction = CL.logError,
    sensitivity: LogSensitivity = LogSensitivity.Sensitive,
    throwableAction: EmptyFunctionResult<T>
): T? = tryAndLog(
    tag = clazz.simpleName,
    message = message,
    placeholders = placeholders,
    logger = logger,
    sensitivity = sensitivity,
    throwableAction = throwableAction
)

/**
 * Tries the given operation, and if fails then it logs it and returns null
 * @param kClazz: [KClass]<*>
 * @param message [String]
 * @param logger [CLLogFunction]<*>
 * @param sensitivity [LogSensitivity]
 * @param throwableAction [EmptyFunctionResult]<T>
 * @return T?
 */
public inline fun <T> tryAndLog(
    kClazz: KClass<*>,
    message: String = "",
    placeholders: Array<String> = arrayOf(),
    logger: CLLogFunction = CL.logError,
    sensitivity: LogSensitivity = LogSensitivity.Sensitive,
    throwableAction: EmptyFunctionResult<T>
): T? = tryAndLog(
    tag = kClazz.java.simpleName,
    message = message,
    placeholders = placeholders,
    logger = logger,
    sensitivity = sensitivity,
    throwableAction = throwableAction
)
