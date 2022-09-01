package csense.kotlin.logger


public inline fun <reified T> T.logClassDebug(
    message: String,
    throwable: Throwable? = null,
    sensitivity: LogSensitivity = LogSensitivity.Sensitive
): Unit =
    logClassDebug(message = message, placeholders = emptyArray(), throwable = throwable, sensitivity = sensitivity)

public inline fun <reified T> T.logClassDebug(
    message: String,
    vararg placeholders: String,
    throwable: Throwable? = null,
    sensitivity: LogSensitivity = LogSensitivity.Sensitive
) {
    val tag = T::class.simpleName ?: "<Unknown>"
    CL.logDebug(
        tag = tag,
        message = message,
        placeholders = placeholders,
        exception = throwable,
        sensitivity = sensitivity
    )
}


public inline fun <reified T> T.logClassWarning(
    message: String,
    throwable: Throwable? = null,
    sensitivity: LogSensitivity = LogSensitivity.Sensitive
): Unit =
    logClassWarning(message = message, placeholders = emptyArray(), throwable = throwable, sensitivity = sensitivity)

public inline fun <reified T> T.logClassWarning(
    message: String,
    vararg placeholders: String,
    throwable: Throwable? = null,
    sensitivity: LogSensitivity = LogSensitivity.Sensitive
) {
    val tag = T::class.simpleName ?: "<Unknown>"
    CL.logWarning(
        tag = tag,
        message = message,
        placeholders = placeholders,
        exception = throwable,
        sensitivity = sensitivity
    )
}

public inline fun <reified T> T.logClassError(
    message: String,
    throwable: Throwable? = null,
    sensitivity: LogSensitivity = LogSensitivity.Sensitive
): Unit =
    logClassError(message = message, placeholders = emptyArray(), throwable = throwable, sensitivity = sensitivity)

public inline fun <reified T> T.logClassError(
    message: String,
    vararg placeholders: String,
    throwable: Throwable? = null,
    sensitivity: LogSensitivity = LogSensitivity.Sensitive
) {
    val tag = T::class.simpleName ?: "<Unknown>"
    CL.logError(
        tag = tag,
        message = message,
        placeholders = placeholders,
        exception = throwable,
        sensitivity = sensitivity
    )
}
