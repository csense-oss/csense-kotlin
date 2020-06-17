package csense.kotlin.logger

import csense.kotlin.Function0R
import csense.kotlin.extensions.collections.invokeEachWith
import csense.kotlin.extensions.collections.skipIfEmptyOr
import csense.kotlin.extensions.toPrettyString
import csense.kotlin.Function4

// this requires us to take over STD out which is platform agnostic.
// This should be fixed in test plugin (csense kotlin test)
/**
 * This will add a logger to each category using the stdout (console).
 * @receiver LLogger the logger to add the formatter(s) to
 * @param formatter [Function4]<[LoggingLevel], [String], [String], [Throwable]?, [String]> formatter function
 */
inline fun LLogger.usePrintAsLoggers(
        crossinline formatter: FunctionLoggerFormatter = { level: LoggingLevel, tag: String, message: String, exception: Throwable? ->
            "$level - [$tag] $message ${exception?.toPrettyString() ?: ""}"
        }
) {
    
    val debug: LoggingFunctionType<Any> = { tag: String, message: String, exception: Throwable? ->
        println(formatter(LoggingLevel.Debug, tag, message, exception))
    }
    val warning: LoggingFunctionType<Any> = { tag: String, message: String, exception: Throwable? ->
        println(formatter(LoggingLevel.Warning, tag, message, exception))
    }
    val error: LoggingFunctionType<Any> = { tag: String, message: String, exception: Throwable? ->
        println(formatter(LoggingLevel.Error, tag, message, exception))
    }
    val prod: LoggingFunctionType<Any> = { tag: String, message: String, exception: Throwable? ->
        println(formatter(LoggingLevel.Production, tag, message, exception))
    }
    debugLoggers.add(debug)
    warningLoggers.add(warning)
    errorLoggers.add(error)
    productionLoggers.add(prod)
}


/**
 * Invokes each listener of a logging type function with a lazily computed message.
 * Skips the message if there are no loggers.
 * @receiver [Iterable]<T>
 * @param tag [String]
 * @param messageFnc [Function0R]<String>
 * @param exception [Throwable]?
 */
inline fun <T : LoggingFunctionType<*>> Iterable<T>.invokeEachWithLoggingLazy(
        tag: String,
        messageFnc: Function0R<String>,
        exception: Throwable?
) = skipIfEmptyOr {
    invokeEachWith(tag, messageFnc(), exception)
}