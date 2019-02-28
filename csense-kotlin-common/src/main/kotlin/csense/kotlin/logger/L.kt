@file:Suppress("unused", "NOTHING_TO_INLINE")

package csense.kotlin.logger

import csense.kotlin.extensions.*
import csense.kotlin.extensions.collections.*
import csense.kotlin.extensions.primitives.*


/**
 * Logging types / levels
 * A simple enumeration over the types of logging that can happen.
 * @property stringValue String the textual representation (useful for tags)
 */
enum class LoggingLevel(val stringValue: String) {
    Debug("Debug"),
    Warning("Warning"),
    Error("Error"),
    Production("Production");

    /**
     * Gets the string representation.
     * @return String
     */
    override fun toString(): String {
        return stringValue
    }
}

/**
 * The Basis logging function.
 */
typealias LoggingFunctionType<T> = (tag: String, message: String, throwable: Throwable?) -> T

/**
 * The L logger, capable of being instantiated, and inherited.
 * Contains all necessary logging features. [LoggingLevel] channels, each capable of beeing enabled / disabled.
 * and a list of listeners for that logging level.
 * The main instance is called "L"
 */
open class LLogger {
    /**
     * Controls whenever logging is allowed.
     * This turns on all the other logging features
     * INCLUDING productionLogging (so if you want to turn off all logs, except production logging
     * the advice is to set isAllowedLogging(false); and then explicit enable productionLogging.
     */
    fun isLoggingAllowed(value: Boolean) {
        isDebugLoggingAllowed = value
        isWarningLoggingAllowed = value
        isProductionLoggingAllowed = value
        isErrorLoggingAllowed = value
    }

    /**
     * Controls whenever error logging is allowed.
     * default is true
     */
    var isErrorLoggingAllowed = true
    /**
     * controls whenever warning logging is allowed
     * default is true
     */
    var isWarningLoggingAllowed = true
    /**
     * controls whenever debug logging is allowed
     * default is true
     */
    var isDebugLoggingAllowed = true
    /**
     * controls whenever production logging is allowed
     * default is true
     */
    var isProductionLoggingAllowed = true

    /**
     * Production level loggers; the intention here is to allow this logging in production.
     * its controlled separate from all the other logging flags.
     *
     * default is the android warning logger
     */
    var productionLoggers: MutableList<LoggingFunctionType<Any>> = mutableListOf()
    /**
     *
     */
    var debugLoggers: MutableList<LoggingFunctionType<Any>> = mutableListOf()
    /**
     *
     */
    var warningLoggers: MutableList<LoggingFunctionType<Any>> = mutableListOf()
    /**
     *
     */
    var errorLoggers: MutableList<LoggingFunctionType<Any>> = mutableListOf()

    /**
     * An error logging channel
     * this logs messages as the level "Error", meant for "bad things", eg application / library errors,
     * logging important messages (due to issues / bugs), or alike.
     * @param tag a categorization of the log, should be 20 characters or less
     * @param msg the message to be logged can be as long as needed, and contain control characters
     *  (newlines, tabs ect)
     * @param exception a stacktrace of some sort of error / exception.
     *
     * Requires the isErrorLoggingAllowed to be true to log anything
     *
     */
    fun error(tag: String, msg: String, exception: Throwable? = null) {
        isErrorLoggingAllowed.onTrue { errorLoggers.invokeEachWith(tag, msg, exception) }
    }

    /**
     * A warning logging channel
     * this logs messages as the level "Warning", mean for not fatal / very bad things,
     * but things that are usually not bound to happen and should not happen.
     *
     */
    fun warning(tag: String, message: String, exception: Throwable? = null) {
        isWarningLoggingAllowed.onTrue { warningLoggers.invokeEachWith(tag, message, exception) }
    }

    /**
     * A Debug logging
     */
    fun debug(tag: String, message: String, exception: Throwable? = null) {
        isDebugLoggingAllowed.onTrue { debugLoggers.invokeEachWith(tag, message, exception) }
    }

    /**
     * A production logging channel.
     * purpose is to allow logging even in production, such as very specific errors,warnings, assertions,
     * and other well though logs. any regular library or none application code shall not use this,
     * as this is for application level only.
     *
     */
    fun logProd(tag: String, message: String, exception: Throwable? = null) {
        isProductionLoggingAllowed.onTrue { productionLoggers.invokeEachWith(tag, message, exception) }
    }
}

/**
 * Container for all shared logging.
 * Shared single instance.
 */
object L : LLogger()
/**
 * The definition of a logging formatter.
 *
 */
typealias FunctionLoggerFormatter = (level: LoggingLevel, tag: String, message: String, error: Throwable?) -> String

/**
 * This will add a logger to each category using the stdout (console).
 * @receiver L
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
