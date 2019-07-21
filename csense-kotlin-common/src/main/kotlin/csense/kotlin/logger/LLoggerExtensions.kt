package csense.kotlin.logger

import csense.kotlin.extensions.*

/**
 * This will add a logger to each category using the stdout (console).
 */
fun LLogger.usePrintAsLoggers(
        formatter: FunctionLoggerFormatter = { level: LoggingLevel, tag: String, message: String, exception: Throwable? ->
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
