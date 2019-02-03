@file:Suppress("unused", "NOTHING_TO_INLINE")


package csense.kotlin.logger

import kotlin.reflect.*


/**
 * Uses the simple class name for the tag, otherwise just as the regular debug method
 * @see L.debug
 */
inline fun L.debug(kClass: KClass<*>, message: String, exception: Throwable? = null) {
    debug(kClass.java.simpleName, message, exception)
}

/**
 * Uses the simple class name for the tag, otherwise just as the regular debug method
 * @see L.warning
 */
inline fun L.warning(kClass: KClass<*>, message: String, throwable: Throwable? = null) {
    warning(kClass.java.simpleName, message, throwable)
}

/**
 * Uses the simple class name for the tag, otherwise just as the regular debug method
 * @see L.error
 */
inline fun L.error(kClass: KClass<*>, message: String, exception: Throwable? = null) {
    error(kClass.java.simpleName, message, exception)
}

/**
 * Uses the simple class name for the tag, otherwise just as the regular debug method
 * @see L.logProd
 */
inline fun L.logProd(kClass: KClass<*>, message: String, throwable: Throwable? = null) {
    logProd(kClass.java.simpleName, message, throwable)
}
