package csense.kotlin.logger

public fun interface CLLogFunction {
    public operator fun invoke(
        tag: String,
        message: String,
        vararg placeholders: String,
        exception: Throwable?,
        sensitivity: LogSensitivity
    )
}

public class CLLogFunctionCallToMethod(
    private val function: CLLogFunction
) : CLLogFunction {

    public override operator fun invoke(
        tag: String,
        message: String,
        vararg placeholders: String,
        exception: Throwable?,
        sensitivity: LogSensitivity
    ) {
        function(
            tag = tag,
            message = message,
            placeholders = placeholders,
            exception = exception,
            sensitivity = sensitivity
        )
    }
}
