@file:Suppress("unused", "MemberVisibilityCanBePrivate")

package csense.kotlin.logger

@Deprecated("use CL. it has a corresponding LogMessage that resembles this intent")
public typealias LoggingFunctionType<T> = (String, String, Throwable?) -> T

@Deprecated("use CL")
public object L {
    /**
    //     * controls whenever production logging is allowed
    //     * default is true
    //     */
    @Deprecated("use CL. There are no more any individual level control like this", level = DeprecationLevel.WARNING)
    public var isProductionLoggingAllowed: Boolean = true

    /**
     * Controls whenever error logging is allowed.
     * default is true
     */
    @Deprecated("use CL. There are no more any individual level control like this", level = DeprecationLevel.WARNING)
    public var isErrorLoggingAllowed: Boolean = true

    /**
     * controls whenever warning logging is allowed
     * default is true
     */
    @Deprecated("use CL. There are no more any individual level control like this", level = DeprecationLevel.WARNING)
    public var isWarningLoggingAllowed: Boolean = true

    /**
     * controls whenever debug logging is allowed
     * default is true
     */
    @Deprecated("use CL. There are no more any individual level control like this", level = DeprecationLevel.WARNING)
    public var isDebugLoggingAllowed: Boolean = true

    /**
     * Production level loggers; the intention here is to allow this logging in production.
     * its controlled separate from all the other logging flags.
     */
    @Deprecated("is not in CL. ", level = DeprecationLevel.WARNING)
    @Suppress("DEPRECATION", "MissingTestFunction")
    public var productionLoggers: MutableList<LoggingFunctionType<Any>> = mutableListOf()

    /**
     *
     */
    @Deprecated(
        "use CL's errorLoggers", level = DeprecationLevel.WARNING,
        replaceWith = ReplaceWith(
            "CL.errorLoggers.collect{}"
        )
    )
    @Suppress("DEPRECATION", "MissingTestFunction")
    public var errorLoggers: MutableList<LoggingFunctionType<Any>> = mutableListOf()

    /**
     *
     */
    @Deprecated(
        "use CL's warningLoggers", level = DeprecationLevel.WARNING,
        replaceWith = ReplaceWith(
            "CL.warningLoggers.collect{}"
        )
    )
    @Suppress("DEPRECATION", "MissingTestFunction")
    public var warningLoggers: MutableList<LoggingFunctionType<Any>> = mutableListOf()

    /**
     *
     */
    @Deprecated(
        "use CL's debugLoggers", level = DeprecationLevel.WARNING,
        replaceWith = ReplaceWith(
            "CL.debugLoggers.collect{}"
        )
    )
    @Suppress("DEPRECATION", "MissingTestFunction")
    public var debugLoggers: MutableList<LoggingFunctionType<Any>> = mutableListOf()


    /**
     * Controls whenever logging is allowed.
     * This turns on all the other logging features
     * INCLUDING productionLogging (so if you want to turn off all logs, except production logging
     * the advice is to set isAllowedLogging(false); and then explicit enable productionLogging.)
     */
    @Deprecated("use CL. There are no more any individual level control like this", level = DeprecationLevel.WARNING)
    @Suppress("DEPRECATION", "MissingTestFunction")
    public fun isLoggingAllowed(enable: Boolean) {
        isProductionLoggingAllowed = enable
        isErrorLoggingAllowed = enable
        isWarningLoggingAllowed = enable
        isDebugLoggingAllowed = enable
    }

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
    @Deprecated(
        "Use CL",
        level = DeprecationLevel.WARNING,
        replaceWith = ReplaceWith(
            "CL.logError(" +
                    "tag = tag," +
                    "message =  message," +
                    " placeholders = arrayOf()," +
                    " exception = exception," +
                    " sensitivity = LogSensitivity.Sensitive" +
                    ")"
        )
    )
    @Suppress("DEPRECATION", "MissingTestFunction")
    public fun error(tag: String, message: String, exception: Throwable? = null): Unit = CL.logError(
        tag,
        message,
        placeholders = arrayOf(),
        exception = exception,
        sensitivity = LogSensitivity.Sensitive
    )

    /**
     * A warning logging channel
     * this logs messages as the level "Warning", mean for not fatal / very bad things,
     * but things that are usually not bound to happen and should not happen.
     * @param tag [String]
     * @param message [String]
     * @param exception [Throwable]?
     */
    @Deprecated(
        "Use CL",
        level = DeprecationLevel.WARNING,
        replaceWith = ReplaceWith(
            "CL.logWarning(" +
                    "tag = tag," +
                    "message =  message," +
                    " placeholders = arrayOf()," +
                    " exception = exception," +
                    " sensitivity = LogSensitivity.Sensitive" +
                    ")"
        )
    )
    @Suppress("DEPRECATION", "MissingTestFunction")
    public fun warning(tag: String, message: String, exception: Throwable? = null): Unit = CL.logWarning(
        tag = tag,
        message = message,
        placeholders = arrayOf(),
        exception = exception,
        sensitivity = LogSensitivity.Sensitive
    )

    /**
     * Debug level logging channel.
     * @param tag [String]
     * @param message [String]
     * @param exception [Throwable]?
     *
     */
    @Deprecated(
        "Use CL",
        level = DeprecationLevel.WARNING,
        replaceWith = ReplaceWith(
            "CL.logDebug(" +
                    "tag = tag," +
                    "message =  message," +
                    " placeholders = arrayOf()," +
                    " exception = exception," +
                    " sensitivity = LogSensitivity.Sensitive" +
                    ")"
        )
    )
    @Suppress("DEPRECATION", "MissingTestFunction")
    public fun debug(tag: String, message: String, exception: Throwable? = null): Unit = CL.logDebug(
        tag = tag,
        message = message,
        placeholders = arrayOf(),
        exception = exception,
        sensitivity = LogSensitivity.Sensitive
    )

}