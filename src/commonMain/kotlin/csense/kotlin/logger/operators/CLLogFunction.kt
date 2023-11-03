package csense.kotlin.logger.operators

import csense.kotlin.logger.models.*

public fun interface CLLogFunction {
    public operator fun invoke(
        tag: String,
        message: String,
        vararg placeholders: String,
        exception: Throwable?,
        sensitivity: LogSensitivity
    )
}