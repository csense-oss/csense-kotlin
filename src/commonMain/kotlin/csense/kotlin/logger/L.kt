@file:Suppress("unused", "NOTHING_TO_INLINE", "MemberVisibilityCanBePrivate")

package csense.kotlin.logger

import csense.kotlin.EmptyFunction
import csense.kotlin.Function0R
import csense.kotlin.extensions.collections.invokeEachWith
import csense.kotlin.extensions.primitives.onTrue


/**
 * Logging types / levels
 * A simple enumeration over the types of logging that can happen.
 * @property stringValue [String] the textual representation (useful for tags)
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
 * The definition of a logging formatter.
 */
typealias FunctionLoggerFormatter = (level: LoggingLevel, tag: String, message: String, error: Throwable?) -> String

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
    
    //region loggers allowed controls
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
    //endregion
    
    //region loggers
    /**
     * Production level loggers; the intention here is to allow this logging in production.
     * its controlled separate from all the other logging flags.
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
    //endregion
    
    //region log error
    /**
     * An error logging channel
     * this logs messages as the level "Error", meant for "bad things", eg application / library errors,
     * logging important messages (due to issues / bugs), or alike.
     * @param tag [String] a categorization of the log, should be 20 characters or less
     * @param message [String] the message to be logged can be as long as needed, and contain control characters
     *  (newlines, tabs ect)
     * @param exception [Throwable] a stacktrace of some sort of error / exception.
     *
     * Requires the isErrorLoggingAllowed to be true to log anything
     *
     */
    fun error(tag: String, message: String, exception: Throwable?): Unit = ifMayLogError {
        errorLoggers.invokeEachWith(tag, message, exception)
    }
    
    /**
     *
     */
    fun error(tag: String, message: String): Unit =
            error(tag, message, null)
    
    /**
     *
     * @param tag [String]
     * @param messageFnc [Function0R]<[String]>
     * @param exception [Throwable]?
     */
    fun errorLazy(tag: String, messageFnc: Function0R<String>, exception: Throwable?): Unit = ifMayLogError {
        errorLoggers.invokeEachWithLoggingLazy(tag, messageFnc, exception)
    }
    
    
    fun errorLazy(tag: String, messageFnc: Function0R<String>): Unit =
            errorLazy(tag, messageFnc, null)
    //endregion
    
    //region log warning
    /**
     * A warning logging channel
     * this logs messages as the level "Warning", mean for not fatal / very bad things,
     * but things that are usually not bound to happen and should not happen.
     * @param tag [String]
     * @param message [String]
     * @param exception [Throwable]?
     */
    fun warning(tag: String, message: String, exception: Throwable?): Unit = ifMayLogWarning {
        warningLoggers.invokeEachWith(tag, message, exception)
    }
    
    
    fun warning(tag: String, message: String): Unit =
            warning(tag, message, null)
    
    /**
     *
     * @param tag [String]
     * @param messageFnc [Function0R]<[String]>
     * @param exception [Throwable]?
     *
     */
    fun warningLazy(tag: String, messageFnc: Function0R<String>, exception: Throwable?): Unit = ifMayLogWarning {
        warningLoggers.invokeEachWithLoggingLazy(tag, messageFnc, exception)
    }
    
    fun warningLazy(tag: String, messageFnc: Function0R<String>): Unit =
            warningLazy(tag, messageFnc, null)
    //endregion
    
    //region log debug
    /**
     * Debug level logging channel.
     * @param tag [String]
     * @param message [String]
     * @param exception [Throwable]?
     *
     */
    fun debug(tag: String, message: String, exception: Throwable? = null): Unit = ifMayLogDebug {
        debugLoggers.invokeEachWith(tag, message, exception)
    }
    
    fun debug(tag: String, message: String): Unit =
            debug(tag, message, null)
    
    /**
     * A lazy debug logging
     * @param tag [String]
     * @param messageFnc [Function0R]<[String]>
     * @param exception [Throwable]?
     *
     */
    fun debugLazy(tag: String, messageFnc: Function0R<String>, exception: Throwable?): Unit = ifMayLogDebug {
        debugLoggers.invokeEachWithLoggingLazy(tag, messageFnc, exception)
    }
    
    fun debugLazy(tag: String, messageFnc: Function0R<String>) =
            debugLazy(tag, messageFnc, null)
    //endregion
    
    //region log prod
    /**
     * A production logging channel.
     * purpose is to allow logging even in production, such as very specific errors,warnings, assertions,
     * and other well though logs. any regular library or none application code shall not use this,
     * as this is for application level only.
     * @param tag [String]
     * @param message [String]
     * @param exception [Throwable]?
     */
    fun logProd(tag: String, message: String, exception: Throwable?): Unit = ifMayLogProduction {
        productionLoggers.invokeEachWith(tag, message, exception)
    }
    
    fun logProd(tag: String, message: String) =
            logProd(tag, message, null)
    
    /**
     * Lazy logging where, computing the message is non "trivial" or you do not wanna pay for creating / computing
     *  it if its not going to get logged.
     * @param tag [String]
     * @param messageFnc [Function0R]<[String]>
     * @param exception [Throwable]?
     */
    fun logProdLazy(tag: String, messageFnc: Function0R<String>, exception: Throwable?): Unit = ifMayLogProduction {
        productionLoggers.invokeEachWithLoggingLazy(tag, messageFnc, exception)
    }
    
    fun logProdLazy(tag: String, messageFnc: Function0R<String>): Unit =
            logProdLazy(tag, messageFnc, null)
    //endregion
    
    
    //region Util functions
    /**
     * if production logging is allowed, runs the given action.
     * @param action [EmptyFunction]
     */
    private fun ifMayLogProduction(action: EmptyFunction) {
        isProductionLoggingAllowed.onTrue(action)
    }
    
    /**
     * if error logging is allowed, runs the given action.
     * @param action [EmptyFunction]
     */
    private fun ifMayLogError(action: EmptyFunction) {
        isErrorLoggingAllowed.onTrue(action)
    }
    
    /**
     * if warning logging is allowed, runs the given action.
     * @param action [EmptyFunction]
     */
    private fun ifMayLogWarning(action: EmptyFunction) {
        isWarningLoggingAllowed.onTrue(action)
    }
    
    /**
     * if debug logging is allowed, runs the given action.
     * @param action [EmptyFunction]
     */
    private fun ifMayLogDebug(action: EmptyFunction) {
        isDebugLoggingAllowed.onTrue(action)
    }
    //endregion
    
}


/**
 * Container for all shared logging.
 * Shared single instance.
 */
object L : LLogger()
