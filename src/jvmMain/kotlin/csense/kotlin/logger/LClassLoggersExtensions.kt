@file:Suppress("unused", "NOTHING_TO_INLINE")

package csense.kotlin.logger

//TODO find a way to test the L instance in a test thread safe way.until just suppress.

/**
 * logs the given message and or the exception, using the class's name as the tag
 *
 * this logs as an error
 * @receiver T
 * @param message String
 * @param exception Throwable?
 * see L.error
 */
@Suppress("MissingTestFunction")
inline fun <reified T : Any> T.logClassError(message: String, exception: Throwable? = null) {
    L.error(T::class.java.simpleName, message, exception)
}

/**
 * logs the given message and or the exception, using the class's name as the tag
 * this logs as a warning
 * @receiver T
 * @param message String
 * @param exception Throwable?
 * see L.warning
 */
@Suppress("MissingTestFunction")
inline fun <reified T : Any> T.logClassWarning(message: String, exception: Throwable? = null) {
    L.warning(T::class.java.simpleName, message, exception)
}

/**
 * logs the given message and or the exception, using the class's name as the tag
 * this logs as a debug message
 * @receiver T
 * @param message String
 * @param exception Throwable?
 * see L.debug
 */
@Suppress("MissingTestFunction")
inline fun <reified T : Any> T.logClassDebug(message: String, exception: Throwable? = null) {
    L.debug(T::class.java.simpleName, message, exception)
}

/**
 * logs the given message and or the exception, using the class's name as the tag
 * this logs as a production log
 * @receiver T
 * @param message String
 * @param exception Throwable?
 * see L.logProd
 */
@Suppress("MissingTestFunction")
inline fun <reified T : Any> T.logClassProduction(message: String, exception: Throwable? = null) {
    L.logProd(T::class.java.simpleName, message, exception)
}

/**
 * Logs the current stack to debug
 * @param tag String  the tagged name
 * @param limit Int the max length of the stacktrace
 * @param skipFirstFunctions Int how many of the first functions to skip; this allows one to skip the stacktrace function / ect.
 */
@Suppress("MissingTestFunction")
inline fun logCurrentStackTraceDebug(
        tag: String = "stack",
        limit: Int = 10,
        skipFirstFunctions: Int = 0) = logCurrentStackTrace(tag, L::debug, limit, skipFirstFunctions)

/**
 * Logs the current stack to warning
 * @param tag String  the tagged name
 * @param limit Int the max length of the stacktrace
 * @param skipFirstFunctions Int how many of the first functions to skip; this allows one to skip the stacktrace function / ect.
 */
@Suppress("MissingTestFunction")
inline fun logCurrentStackTraceWarning(
        tag: String = "stack",
        limit: Int = 10,
        skipFirstFunctions: Int = 0) = logCurrentStackTrace(tag, L::warning, limit, skipFirstFunctions)

/**
 * Logs the current stack to error
 * @param tag String  the tagged name
 * @param limit Int the max length of the stacktrace
 * @param skipFirstFunctions Int how many of the first functions to skip; this allows one to skip the stacktrace function / ect.
 */
@Suppress("MissingTestFunction")
inline fun logCurrentStackTraceError(
        tag: String = "stack",
        limit: Int = 10,
        skipFirstFunctions: Int = 0) = logCurrentStackTrace(tag, L::error, limit, skipFirstFunctions)


/**
 * Logs the current stack to Production
 * @param tag String  the tagged name
 * @param limit Int the max length of the stacktrace
 * @param skipFirstFunctions Int how many of the first functions to skip; this allows one to skip the stacktrace function / ect.
 */
@Suppress("MissingTestFunction")
inline fun logCurrentStackTraceProd(
        tag: String = "stack",
        limit: Int = 10,
        skipFirstFunctions: Int = 0) = logCurrentStackTrace(tag, L::logProd, limit, skipFirstFunctions)

/**
 * Logs the current stack to debug
 * @param tag String  the tagged name
 * @param logTo LoggingFunctionType<Unit> the function to log via.
 * @param limit Int the max length of the stacktrace
 * @param skipFirstFunctions Int how many of the first functions to skip; this allows one to skip the stacktrace function / ect.
 */
inline fun logCurrentStackTrace(
        tag: String = "stack",
        logTo: LoggingFunctionType<Unit>,
        limit: Int = 10,
        skipFirstFunctions: Int = 0) {
    val text = Thread.currentThread().stackTrace.asSequence().drop(skipFirstFunctions).take(limit).joinToString("\n")
    logTo(tag, text, null)
}
