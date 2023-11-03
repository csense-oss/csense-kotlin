package csense.kotlin.logger.models

import csense.kotlin.logger.extensions.*


public abstract class LogMessage(
    public val tag: String,
    public val message: LogMessageFormat,
    public val exception: Throwable?
) {
    public class Debug(
        tag: String,
        message: LogMessageFormat,
        exception: Throwable?
    ) : LogMessage(tag, message, exception)

    public class Warning(
        tag: String,
        message: LogMessageFormat,
        exception: Throwable?
    ) : LogMessage(tag, message, exception)

    public class Error(
        tag: String,
        message: LogMessageFormat,
        exception: Throwable?
    ) : LogMessage(tag, message, exception)

    public override fun toString(): String {
        return toString(logSensitivity = message.sensitivity)
    }

    public fun toString(logSensitivity: LogSensitivity): String {
        val exceptionText: String = logSensitivity.stackTraceOfOrNull(exception) ?: ""
        return "[$tag] - $message $exceptionText"
    }
}
