package csense.kotlin.logger


public typealias CLLogFunction = (
    tag: String,
    message: String,
    placeholders: Array<out String>,
    exception: Throwable?,
    sensitivity: LogSensitivity
) -> Unit


public fun interface CLLogFunctionCall {
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
) : CLLogFunctionCall {

    public override operator fun invoke(
        tag: String,
        message: String,
        vararg placeholders: String,
        exception: Throwable?,
        sensitivity: LogSensitivity
    ) {
        function(tag, message, placeholders, exception, sensitivity)
    }
}
