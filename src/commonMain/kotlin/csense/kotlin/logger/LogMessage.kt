package csense.kotlin.logger


public sealed class LogMessage {
    public abstract val tag: String
    public abstract val message: LogMessageFormat
    public abstract val throwable: Throwable?

    public class Debug(
        override val tag: String,
        override val message: LogMessageFormat,
        override val throwable: Throwable? = null
    ) : LogMessage()

    public class Warning(
        override val tag: String,
        override val message: LogMessageFormat,
        override val throwable: Throwable? = null
    ) : LogMessage()

    public class Error(
        override val tag: String,
        override val message: LogMessageFormat,
        override val throwable: Throwable? = null
    ) : LogMessage()

    override fun toString(): String {
        return toFullLogString()
    }

    public fun toFullLogString(): String {
        //TODO sensitive stacktrace here...
        val exception = throwable?.stackTraceToString() ?: ""
        return "[$tag] - $message $exception"
    }
}
