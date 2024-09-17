package org.csenseoss.kotlin.logger.loggers

import kotlinx.coroutines.flow.*
import org.csenseoss.kotlin.logger.models.*

public interface LogMessageLoggerInterface {
    public val allLoggers: Flow<LogMessage>
    public fun log(message: LogMessage)
}