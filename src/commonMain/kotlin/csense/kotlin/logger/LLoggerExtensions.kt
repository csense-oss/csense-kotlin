@file:Suppress("unused")

package csense.kotlin.logger

import csense.kotlin.*
import csense.kotlin.extensions.*
import csense.kotlin.extensions.collections.*

@Suppress("MissingTestFunction")
// this requires us to take over STD out which is platform agnostic.
// This should be fixed in test plugin (csense kotlin test)
/**
 * This will add a logger to each category using the stdout (console).
 * @receiver LLogger the logger to add the formatter(s) to
 * @param formatter [Function4]<[LoggingLevel], [String], [String], [Throwable]?, [String]> formatter function
 */
public inline fun LLogger.usePrintAsLoggers(
    crossinline formatter: FunctionLoggerFormatter = { level: LoggingLevel, tag: String, message: String, exception: Throwable? ->
        "$level - [$tag] $message ${exception?.toPrettyString() ?: ""}"
    }
) {
    val createLogger: (LoggingLevel) -> LoggingFunctionType<Any> = { level: LoggingLevel ->
        { tag: String, message: String, exception: Throwable? ->
            println(formatter(level, tag, message, exception))
        }
    }
    debugLoggers.add(createLogger(LoggingLevel.Debug))
    warningLoggers.add(createLogger(LoggingLevel.Warning))
    errorLoggers.add(createLogger(LoggingLevel.Error))
    productionLoggers.add(createLogger(LoggingLevel.Production))
}

@Suppress("MissingTestFunction")
public inline fun LLogger.usePrintAsLoggersWithAnsiColor(
    crossinline formatter: FunctionLoggerFormatter = { level: LoggingLevel, tag: String, message: String, exception: Throwable? ->
        "$level - [$tag] $message ${exception?.toPrettyString() ?: ""}"
    },
    debugColor: String = "\u001B[35m", // purple
    warningColor: String = "\u001B[33m", // yellow
    ErrorColor: String = "\u001B[31m",  // red
    ProductionColor: String = "\u001B[36m" //cyan
) {
    val reset = "\u001B[0m"

    val createLogger: (level: LoggingLevel, color: String) -> LoggingFunctionType<Any> =
        { level: LoggingLevel, color: String ->
            { tag: String, message: String, exception: Throwable? ->
                println(color + formatter(level, tag, message, exception) + reset)
            }
        }
    debugLoggers.add(createLogger(LoggingLevel.Debug, debugColor))
    warningLoggers.add(createLogger(LoggingLevel.Warning, warningColor))
    errorLoggers.add(createLogger(LoggingLevel.Error, ErrorColor))
    productionLoggers.add(createLogger(LoggingLevel.Production, ProductionColor))
}

/**
 * Invokes each listener of a logging type function with a lazily computed message.
 * Skips the message if there are no loggers.
 * @receiver [Iterable]<T>
 * @param tag [String]
 * @param messageFnc [Function0R]<String>
 * @param exception [Throwable]?
 */
public inline fun <T : LoggingFunctionType<*>> Iterable<T>.invokeEachWithLoggingLazy(
    tag: String,
    messageFnc: Function0R<String>,
    exception: Throwable?
): Unit = skipIfEmptyOr {
    invokeEachWith(tag, messageFnc(), exception)
}
