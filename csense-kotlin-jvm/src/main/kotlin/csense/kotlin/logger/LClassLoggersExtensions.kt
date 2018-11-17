package csense.kotlin.logger


/**
 * logs the given message and or the exception, using the class's name as the tag
 *
 * this logs as an error
 * @receiver T
 * @param message String
 * @param exception Throwable?
 * @see L.error
 */
inline fun <reified T : Any> T.logClassError(message: String, exception: Throwable? = null) {
    L.error(T::class, message, exception)
}

/**
 * logs the given message and or the exception, using the class's name as the tag
 * this logs as a warning
 * @receiver T
 * @param message String
 * @param exception Throwable?
 * @see L.warning
 */
inline fun <reified T : Any> T.logClassWarning(message: String, exception: Throwable? = null) {
    L.warning(T::class, message, exception)
}

/**
 * logs the given message and or the exception, using the class's name as the tag
 * this logs as a debug message
 * @receiver T
 * @param message String
 * @param exception Throwable?
 * @see L.debug
 */
inline fun <reified T : Any> T.logClassDebug(message: String, exception: Throwable? = null) {
    L.debug(T::class, message, exception)
}

/**
 * logs the given message and or the exception, using the class's name as the tag
 * this logs as a production log
 * @receiver T
 * @param message String
 * @param exception Throwable?
 * @see L.logProd
 */
inline fun <reified T : Any> T.logClassProduction(message: String, exception: Throwable? = null) {
    L.logProd(T::class, message, exception)
}
