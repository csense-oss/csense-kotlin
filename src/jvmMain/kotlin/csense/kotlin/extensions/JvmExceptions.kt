@file:Suppress("unused", "INVISIBLE_MEMBER", "INVISIBLE_REFERENCE")

package csense.kotlin.extensions

import csense.kotlin.*
import csense.kotlin.logger.*
import csense.kotlin.logger.models.*
import kotlin.internal.*
import kotlin.reflect.*

/**
 * Tries the given operation, and if fails then it logs it and returns null
 * @param clazz [Class]<*>
 * @param message [String]
 * @param logger [LoggingFunctionType]<*>
 * @param throwableAction [EmptyFunctionResult]<T>
 * @return T?
 */
@LowPriorityInOverloadResolution
@Deprecated("use CL version instead")
@Suppress("DEPRECATION")
public inline fun <T> tryAndLog(
    clazz: Class<*>,
    message: String = "",
    logger: LoggingFunctionType<*> = L::error,
    throwableAction: EmptyFunctionResult<T>
): T? = tryAndLog(clazz.simpleName, message, logger, throwableAction)

/**
 * Tries the given operation, and if fails then it logs it and returns null
 * @param kClazz: [KClass]<*>
 * @param message [String]
 * @param logger [LoggingFunctionType]<*>
 * @param throwableAction [EmptyFunctionResult]<T>
 * @return T?
 */
@LowPriorityInOverloadResolution
@Deprecated("use CL version instead")
@Suppress("DEPRECATION")
public inline fun <T> tryAndLog(
    kClazz: KClass<*>,
    message: String = "",
    logger: LoggingFunctionType<*> = L::error,
    throwableAction: EmptyFunctionResult<T>
): T? = tryAndLog(kClazz.java.simpleName, message, logger, throwableAction)


/**
 * Tries the given operation, and if fails then it logs it and returns null
 * @param clazz [Class]<*>
 * @param message [String]
 * @param logger [LoggingFunctionType]<*>
 * @param throwableAction [EmptyFunctionResult]<T>
 * @return T?
 */
public inline fun <T> tryAndLog(
    clazz: Class<*>,
    message: String = "",
    placeholders: Array<String> = arrayOf(),
    logger: CLLogFunction = CL.error,
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
 * @param logger [LoggingFunctionType]<*>
 * @param throwableAction [EmptyFunctionResult]<T>
 * @return T?
 */
public inline fun <T> tryAndLog(
    kClazz: KClass<*>,
    message: String = "",
    placeholders: Array<String> = arrayOf(),
    logger: CLLogFunction = CL.error,
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
