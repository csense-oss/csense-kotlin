package csense.kotlin.extensions

import csense.kotlin.*
import csense.kotlin.logger.*

/**
 * Tries the given operation, and if fails then it logs it and returns null
 */
inline fun <T> tryAndLog(title: String = "",
                         message: String = "",
                         throwableAction: EmptyFunctionResult<T>): T? {
    return tryAndLog(title, message, L::error, throwableAction)
}

/**
 * Tries the given operation, and if fails then it logs it and returns null
 */
inline fun <T> tryAndLog(title: String = "",
                         message: String = "",
                         logger: LoggingFunctionType<*>,
                         throwableAction: EmptyFunctionResult<T>): T? {
    return try {
        throwableAction()
    } catch (e: Throwable) {
        logger(title, message, e)
        return null
    }
}