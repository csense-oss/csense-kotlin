package csense.kotlin.logger.factory

import csense.kotlin.logger.extensions.*
import csense.kotlin.logger.models.*
import csense.kotlin.logger.operators.*

public typealias CreateLogMessageFunction = (tag: String, message: LogMessageFormat, exception: Throwable?) -> LogMessage

public class CsenseLoggerLogFactory(
    private val log: (message: LogMessage) -> Unit,
    private val isSensitiveLoggingEnabled: () -> Boolean,
    private val createLogMessage: CreateLogMessageFunction
) : CLLogFunction {
    public override operator fun invoke(
        tag: String,
        message: String,
        vararg placeholders: String,
        exception: Throwable?,
        sensitivity: LogSensitivity
    ) {
        val logMessage: LogMessageFormat = createLogMessageFormat(
            message = message,
            placeholders = placeholders,
            sensitivity = sensitivity
        )
        invoke(tag = tag, message = logMessage, exception = exception)
    }

    public operator fun invoke(
        tag: String,
        message: String,
        exception: Throwable?,
        sensitivity: LogSensitivity
    ) {
        val logMessage: LogMessageFormat = createLogMessageFormat(
            message = message,
            placeholders = emptyArray(),
            sensitivity = sensitivity
        )
        invoke(tag = tag, message = logMessage, exception = exception)
    }

    public operator fun invoke(
        tag: String,
        message: String,
        sensitivity: LogSensitivity
    ) {
        val logMessage: LogMessageFormat = createLogMessageFormat(
            message = message,
            placeholders = emptyArray(),
            sensitivity = sensitivity
        )
        invoke(tag = tag, message = logMessage, exception = null)
    }

    private fun createLogMessageFormat(
        message: String,
        placeholders: Array<out String>,
        sensitivity: LogSensitivity
    ): LogMessageFormat {
        return sensitivity.toLogMessageFormatForLogging(
            mayLogSensitive = isSensitiveLoggingEnabled(),
            message = message,
            placeholders = placeholders
        )
    }

    public operator fun invoke(
        tag: String,
        message: LogMessageFormat,
        exception: Throwable?
    ) {
        log(
            createLogMessage(
                tag,
                message,
                exception
            )
        )
    }
}
