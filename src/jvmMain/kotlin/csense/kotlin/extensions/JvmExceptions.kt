package csense.kotlin.extensions

import csense.kotlin.EmptyFunctionResult
import csense.kotlin.logger.L
import csense.kotlin.logger.LoggingFunctionType
import kotlin.reflect.KClass

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
    logger: LoggingFunctionType<*> = L::error,
    throwableAction: EmptyFunctionResult<T>
): T? =
    tryAndLog(clazz.simpleName, message, logger, throwableAction)

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
    logger: LoggingFunctionType<*> = L::error,
    throwableAction: EmptyFunctionResult<T>
): T? =
    tryAndLog(kClazz.java.simpleName, message, logger, throwableAction)
