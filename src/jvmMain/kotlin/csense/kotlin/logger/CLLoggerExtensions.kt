@file:Suppress("NOTHING_TO_INLINE")

package csense.kotlin.logger

import csense.kotlin.logger.models.*

@JvmInline
public value class CLLoggerClassLog<T>(
    public val type: Class<T>
)

////Consider a " Log" onstead on each T, and then have "debug" etc on that (namespace it)
public inline val <reified T> T.log: CLLoggerClassLog<T>
    get() = CLLoggerClassLog(T::class.java)

public inline fun CLLoggerClassLog<Any>.debug(
    message: String,
    vararg placeholders: String = arrayOf(),
    exception: Throwable? = null,
    sensitivity: LogSensitivity = LogSensitivity.Sensitive
) {
    CL.logDebug(
        tag = type.simpleName,
        message = message,
        placeholders = placeholders,
        exception = exception,
        sensitivity = sensitivity
    )
}


public inline fun CLLoggerClassLog<Any>.warning(
    message: String,
    vararg placeholders: String = arrayOf(),
    exception: Throwable? = null,
    sensitivity: LogSensitivity = LogSensitivity.Sensitive
) {
    CL.logWarning(
        tag = type.simpleName,
        message = message,
        placeholders = placeholders,
        exception = exception,
        sensitivity = sensitivity
    )
}


public inline fun CLLoggerClassLog<Any>.error(
    message: String,
    vararg placeholders: String = arrayOf(),
    exception: Throwable? = null,
    sensitivity: LogSensitivity = LogSensitivity.Sensitive
) {
    CL.logError(
        tag = type.simpleName,
        message = message,
        placeholders = placeholders,
        exception = exception,
        sensitivity = sensitivity
    )
}



/**
 * Logs the current stack to error
 * @param tag [String]  the tagged name
 * @param limit [Int] the max length of the stacktrace
 * @param skipFirstFunctions [Int] how many of the first functions to skip; this allows one to skip the stacktrace function / ect.
 */
public inline fun CLLoggerClassLog<Any>.currentStackTraceError(
    tag: String = "stack",
    limit: Int = 10,
    skipFirstFunctions: Int = 0,
    sensitivity: LogSensitivity = LogSensitivity.Sensitive
): Unit = currentStackTrace(tag, CL::logError, limit, skipFirstFunctions, sensitivity)

/**
 * Logs the current stack to warning
 * @param tag [String]  the tagged name
 * @param limit [Int] the max length of the stacktrace
 * @param skipFirstFunctions [Int] how many of the first functions to skip; this allows one to skip the stacktrace function / ect.
 */
public inline fun CLLoggerClassLog<Any>.currentStackTraceWarning(
    tag: String = "stack",
    limit: Int = 10,
    skipFirstFunctions: Int = 0,
    sensitivity: LogSensitivity = LogSensitivity.Sensitive
): Unit = currentStackTrace(tag, CL::logWarning, limit, skipFirstFunctions, sensitivity)

/**
 * Logs the current stack to debug
 * @param tag [String]  the tagged name
 * @param limit [Int] the max length of the stacktrace
 * @param skipFirstFunctions [Int] how many of the first functions to skip; this allows one to skip the stacktrace function / ect.
 */
public inline fun CLLoggerClassLog<Any>.currentStackTraceDebug(
    tag: String = "stack",
    limit: Int = 10,
    skipFirstFunctions: Int = 0,
    sensitivity: LogSensitivity = LogSensitivity.Sensitive
): Unit = currentStackTrace(tag, CL::logDebug, limit, skipFirstFunctions, sensitivity)


/**
 * Logs the current stack to debug
 * @param tag [String] the tagged name
 * @param logTo [LoggingFunctionType]<[Unit]> the function to log via.
 * @param limit [Int] the max length of the stacktrace
 * @param skipFirstFunctions [Int] how many of the first functions to skip; this allows one to skip the stacktrace function / ect.
 */
public inline fun CLLoggerClassLog<Any>.currentStackTrace(
    tag: String = "stack",
    logTo: CLLogFunction,
    limit: Int = 10,
    skipFirstFunctions: Int = 0,
    sensitivity: LogSensitivity = LogSensitivity.Sensitive
) {
    val placeholders = arrayOf<String>()
    val exception: Throwable? = null
    val message = Thread.currentThread().stackTrace.asSequence().drop(skipFirstFunctions).take(limit).joinToString("\n")
    logTo(
        tag = tag,
        message = message,
        placeholders = placeholders,
        exception = exception,
        sensitivity = sensitivity
    )
}


//
//public inline fun <reified T : Any> T.logClassDebug(
//    message: String,
//    vararg placeholders: String = arrayOf(),
//    exception: Throwable? = null,
//    sensitivity: LogSensitivity = LogSensitivity.Sensitive
//) {
//    CL.logDebug(
//        tag = T::class.java.simpleName,
//        message = message,
//        placeholders = placeholders,
//        exception = exception,
//        sensitivity = sensitivity
//    )
//}
//
//public inline fun <reified T : Any> T.logClassWarning(
//    message: String,
//    vararg placeholders: String = arrayOf(),
//    exception: Throwable? = null,
//    sensitivity: LogSensitivity = LogSensitivity.Sensitive
//) {
//    CL.logWarning(
//        tag = T::class.java.simpleName,
//        message = message,
//        placeholders = placeholders,
//        exception = exception,
//        sensitivity = sensitivity
//    )
//}
//
//public inline fun <reified T : Any> T.logClassError(
//    message: String,
//    vararg placeholders: String = arrayOf(),
//    exception: Throwable? = null,
//    sensitivity: LogSensitivity = LogSensitivity.Sensitive
//) {
//    CL.logError(
//        tag = T::class.java.simpleName,
//        message = message,
//        placeholders = placeholders,
//        exception = exception,
//        sensitivity = sensitivity
//    )
//}
//
//
///**
// * Logs the current stack to error
// * @param tag [String]  the tagged name
// * @param limit [Int] the max length of the stacktrace
// * @param skipFirstFunctions [Int] how many of the first functions to skip; this allows one to skip the stacktrace function / ect.
// */
//public inline fun logCurrentStackTraceError(
//    tag: String = "stack",
//    limit: Int = 10,
//    skipFirstFunctions: Int = 0,
//    sensitivity: LogSensitivity = LogSensitivity.Sensitive
//): Unit = logCurrentStackTrace(tag, CL::logError, limit, skipFirstFunctions, sensitivity)
//
///**
// * Logs the current stack to warning
// * @param tag [String]  the tagged name
// * @param limit [Int] the max length of the stacktrace
// * @param skipFirstFunctions [Int] how many of the first functions to skip; this allows one to skip the stacktrace function / ect.
// */
//public inline fun logCurrentStackTraceWarning(
//    tag: String = "stack",
//    limit: Int = 10,
//    skipFirstFunctions: Int = 0,
//    sensitivity: LogSensitivity = LogSensitivity.Sensitive
//): Unit = logCurrentStackTrace(tag, CL::logWarning, limit, skipFirstFunctions, sensitivity)
//
///**
// * Logs the current stack to debug
// * @param tag [String]  the tagged name
// * @param limit [Int] the max length of the stacktrace
// * @param skipFirstFunctions [Int] how many of the first functions to skip; this allows one to skip the stacktrace function / ect.
// */
//public inline fun logCurrentStackTraceDebug(
//    tag: String = "stack",
//    limit: Int = 10,
//    skipFirstFunctions: Int = 0,
//    sensitivity: LogSensitivity = LogSensitivity.Sensitive
//): Unit = logCurrentStackTrace(tag, CL::logDebug, limit, skipFirstFunctions, sensitivity)
//
//
///**
// * Logs the current stack to debug
// * @param tag [String] the tagged name
// * @param logTo [LoggingFunctionType]<[Unit]> the function to log via.
// * @param limit [Int] the max length of the stacktrace
// * @param skipFirstFunctions [Int] how many of the first functions to skip; this allows one to skip the stacktrace function / ect.
// */
//public inline fun logCurrentStackTrace(
//    tag: String = "stack",
//    logTo: CLLogFunction,
//    limit: Int = 10,
//    skipFirstFunctions: Int = 0,
//    sensitivity: LogSensitivity = LogSensitivity.Sensitive
//) {
//    val placeholders = arrayOf<String>()
//    val exception: Throwable? = null
//    val message = Thread.currentThread().stackTrace.asSequence().drop(skipFirstFunctions).take(limit).joinToString("\n")
//    logTo(
//        tag = tag,
//        message = message,
//        placeholders = placeholders,
//        exception = exception,
//        sensitivity = sensitivity
//    )
//}
