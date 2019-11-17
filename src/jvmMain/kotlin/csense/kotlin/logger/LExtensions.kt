@file:Suppress("unused", "NOTHING_TO_INLINE")


package csense.kotlin.logger

import kotlin.reflect.KClass


/**
 * Uses the simple class name for the tag, otherwise just as the regular debug method
 * see L.debug
 */
inline fun LLogger.debug(kClass: KClass<*>, message: String, exception: Throwable? = null) {
    debug(kClass.java, message, exception)
}

/**
 * Uses the simple class name for the tag, otherwise just as the regular debug method
 * see L.warning
 */
inline fun LLogger.warning(kClass: KClass<*>, message: String, throwable: Throwable? = null) {
    warning(kClass.java, message, throwable)
}

/**
 * Uses the simple class name for the tag, otherwise just as the regular debug method
 * see L.error
 */
inline fun LLogger.error(kClass: KClass<*>, message: String, exception: Throwable? = null) {
    error(kClass.java, message, exception)
}

/**
 * Uses the simple class name for the tag, otherwise just as the regular debug method
 * see L.logProd
 */
inline fun LLogger.logProd(kClass: KClass<*>, message: String, throwable: Throwable? = null) {
    logProd(kClass.java, message, throwable)
}


/**
 * Uses the simple class name for the tag, otherwise just as the regular debug method
 * see L.debug
 */
inline fun LLogger.debug(cls: Class<*>, message: String, exception: Throwable? = null) {
    debug(cls.simpleName, message, exception)
}

/**
 * Uses the simple class name for the tag, otherwise just as the regular debug method
 * see L.warning
 */
inline fun LLogger.warning(cls: Class<*>, message: String, throwable: Throwable? = null) {
    warning(cls.simpleName, message, throwable)
}

/**
 * Uses the simple class name for the tag, otherwise just as the regular debug method
 * see L.error
 */
inline fun LLogger.error(cls: Class<*>, message: String, exception: Throwable? = null) {
    error(cls.simpleName, message, exception)
}

/**
 * Uses the simple class name for the tag, otherwise just as the regular debug method
 * see L.logProd
 */
inline fun LLogger.logProd(cls: Class<*>, message: String, throwable: Throwable? = null) {
    logProd(cls.simpleName, message, throwable)
}

