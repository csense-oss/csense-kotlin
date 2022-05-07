@file:Suppress("unused", "NOTHING_TO_INLINE", "MemberVisibilityCanBePrivate")

package csense.kotlin.logger

import csense.kotlin.*
import csense.kotlin.extensions.collections.*
import csense.kotlin.extensions.primitives.*


/**
 * Logging types / levels
 * A simple enumeration over the types of logging that can happen.
 * @property stringValue [String] the textual representation (useful for tags)
 */
public enum class LoggingLevel(public val stringValue: String) {
    Production("Production"),
    Error("Error"),
    Warning("Warning"),
    Debug("Debug");

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
public typealias LoggingFunctionType<T> = (tag: String, message: String, exception: Throwable?) -> T

/**
 * The definition of a logging formatter.
 */
public typealias FunctionLoggerFormatter = (level: LoggingLevel, tag: String, message: String, exception: Throwable?) -> String

/**
 * The L logger, capable of being instantiated, and inherited.
 * Contains all necessary logging features. [LoggingLevel] channels, each capable of being enabled / disabled.
 * and a list of listeners for that logging level.
 * The main instance is called "L"
 */
public open class LLogger {


    /**
     * controls whenever production logging is allowed
     * default is true
     */
    public var isProductionLoggingAllowed: Boolean = true
    
    /**
     * Controls whenever error logging is allowed.
     * default is true
     */
    public var isErrorLoggingAllowed: Boolean = true

    /**
     * controls whenever warning logging is allowed
     * default is true
     */
    public var isWarningLoggingAllowed: Boolean = true

    /**
     * controls whenever debug logging is allowed
     * default is true
     */
    public var isDebugLoggingAllowed: Boolean = true

    /**
     * Production level loggers; the intention here is to allow this logging in production.
     * its controlled separate from all the other logging flags.
     */
    public var productionLoggers: MutableList<LoggingFunctionType<Any>> = mutableListOf()

    /**
     *
     */
    public var errorLoggers: MutableList<LoggingFunctionType<Any>> = mutableListOf()

    /**
     *
     */
    public var warningLoggers: MutableList<LoggingFunctionType<Any>> = mutableListOf()

    /**
     *
     */
    public var debugLoggers: MutableList<LoggingFunctionType<Any>> = mutableListOf()


    /**
     * Controls whenever logging is allowed.
     * This turns on all the other logging features
     * INCLUDING productionLogging (so if you want to turn off all logs, except production logging
     * the advice is to set isAllowedLogging(false); and then explicit enable productionLogging.)
     */
    public fun isLoggingAllowed(enable: Boolean) {
        isProductionLoggingAllowed = enable
        isErrorLoggingAllowed = enable
        isWarningLoggingAllowed = enable
        isDebugLoggingAllowed = enable
    }

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
    public fun logProd(tag: String, message: String, exception: Throwable?): Unit = ifMayLogProduction {
        productionLoggers.invokeEachWith(tag, message, exception)
    }

    public fun logProd(tag: String, message: String): Unit =
        logProd(tag, message, null)

    /**
     * Lazy logging where, computing the message is non "trivial" or you do not want to pay for creating / computing
     *  it if it's not going to get logged.
     * @param tag [String]
     * @param messageFnc [Function0R]<[String]>
     * @param exception [Throwable]?
     */
    public fun logProdLazy(tag: String, messageFnc: Function0R<String>, exception: Throwable?): Unit =
        ifMayLogProduction {
            productionLoggers.invokeEachWithLoggingLazy(tag, messageFnc, exception)
        }

    public fun logProdLazy(tag: String, messageFnc: Function0R<String>): Unit =
        logProdLazy(tag, messageFnc, null)
    //endregion
    
    //region log error
    /**
     * An error logging channel
     * this logs messages as the level "Error", meant for "bad things", eg application / library errors,
     * logging important messages (due to issues / bugs), or alike.
     * @param tag [String] a categorization of the log, should be 20 characters or fewer
     * @param message [String] the message to be logged can be as long as needed, and contain control characters
     *  (newlines, tabs ect)
     * @param exception [Throwable] a stacktrace of some sort of error / exception.
     *
     * Requires the isErrorLoggingAllowed to be true to log anything
     *
     */
    public fun error(tag: String, message: String, exception: Throwable?): Unit = ifMayLogError {
        errorLoggers.invokeEachWith(tag, message, exception)
    }

    
    public fun error(tag: String, message: String): Unit =
        error(tag, message, null)

    /**
     *
     * @param tag [String]
     * @param messageFnc [Function0R]<[String]>
     * @param exception [Throwable]?
     */
    public fun errorLazy(tag: String, messageFnc: Function0R<String>, exception: Throwable?): Unit = ifMayLogError {
        errorLoggers.invokeEachWithLoggingLazy(tag, messageFnc, exception)
    }


    public fun errorLazy(tag: String, messageFnc: Function0R<String>): Unit =
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
    public fun warning(tag: String, message: String, exception: Throwable?): Unit = ifMayLogWarning {
        warningLoggers.invokeEachWith(tag, message, exception)
    }


    public fun warning(tag: String, message: String): Unit =
        warning(tag, message, null)

    /**
     *
     * @param tag [String]
     * @param messageFnc [Function0R]<[String]>
     * @param exception [Throwable]?
     *
     */
    public fun warningLazy(tag: String, messageFnc: Function0R<String>, exception: Throwable?): Unit = ifMayLogWarning {
        warningLoggers.invokeEachWithLoggingLazy(tag, messageFnc, exception)
    }

    public fun warningLazy(tag: String, messageFnc: Function0R<String>): Unit =
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
    public fun debug(tag: String, message: String, exception: Throwable? = null): Unit = ifMayLogDebug {
        debugLoggers.invokeEachWith(tag, message, exception)
    }
    
    public fun debug(tag: String, message: String): Unit =
        debug(tag, message, null)

    /**
     * A lazy debug logging
     * @param tag [String]
     * @param messageFnc [Function0R]<[String]>
     * @param exception [Throwable]?
     *
     */
    public fun debugLazy(tag: String, messageFnc: Function0R<String>, exception: Throwable?): Unit = ifMayLogDebug {
        debugLoggers.invokeEachWithLoggingLazy(tag, messageFnc, exception)
    }

    public fun debugLazy(tag: String, messageFnc: Function0R<String>): Unit =
        debugLazy(tag, messageFnc, null)
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


/**
 * Container for all shared logging.
 * Shared single instance.
 */
public object L : LLogger()
