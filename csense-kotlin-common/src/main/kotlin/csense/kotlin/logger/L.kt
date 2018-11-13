@file:Suppress("unused", "NOTHING_TO_INLINE")
package csense.kotlin.logger

import csense.kotlin.extensions.collections.*
import csense.kotlin.extensions.primitives.*


/**
 * A category of regular logging types.
 */
enum class LoggingType {
    /**
     * A critical error
     */
    Error,
    /**
     * A warning (akk not a stop sign, but close)
     */
    Warning,
    /**
     * A development based error (wrong assumptions ect / akk assertions).
     */
    Debug,
    /**
     * Verbosly logging information about state ect.
     */
    Verbose,
    /**
     * a varient of verbose, usually not used
     */
    Info
}

/**
 * The Basis logging function.
 */
typealias LoggingFunctionType<T> = (tag: String, message: String, throwable: Throwable?) -> T

/**
 * Container for all shared logging.
 */
object L {
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
