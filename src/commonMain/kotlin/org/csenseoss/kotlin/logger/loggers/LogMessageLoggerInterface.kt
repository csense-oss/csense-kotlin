package org.csenseoss.kotlin.logger.loggers

import org.csenseoss.kotlin.logger.models.*
import kotlinx.coroutines.flow.*

public interface LogMessageLoggerInterface {
    public val allLoggers: Flow<LogMessage>
    public fun log(message: LogMessage)
}