package org.csenseoss.kotlin.logger.operators

import org.csenseoss.kotlin.logger.models.*

public fun interface CLLogFunction {
    public operator fun invoke(
        tag: String,
        message: String,
        vararg placeholders: String,
        exception: Throwable?,
        sensitivity: LogSensitivity
    )
}