package csense.kotlin.logger.models


public open class LogMessage(
    public val tag: String,
    public val message: LogMessageFormat,
    public val throwable: Throwable?
) {
    public class Debug(
        tag: String,
        message: LogMessageFormat,
        throwable: Throwable?
    ) : LogMessage(tag, message, throwable)

    public class Warning(
        tag: String,
        message: LogMessageFormat,
        throwable: Throwable?
    ) : LogMessage(tag, message, throwable)

    public class Error(
        tag: String,
        message: LogMessageFormat,
        throwable: Throwable?
    ) : LogMessage(tag, message, throwable)

    override fun toString(): String {
        return toFullLogString()
    }

    public fun toFullLogString(): String {
        //TODO sensitive stacktrace here...
        val exception = throwable?.stackTraceToString() ?: ""
        return "[$tag] - $message $exception"
    }
}
