package csense.kotlin.patterns.expected


public inline fun <Value, Error> Expected<Value, Error>.recoverCatching(
    transform: (Error) -> Value
): Expected<Value, ExpectedExceptionFailed<Error>> {
    return when (this) {
        is Expected.Success -> this
        is Expected.Failed -> try {
            Expected.Success(transform(this.error))
        } catch (e: Throwable) {
            Expected.Failed(ExpectedExceptionFailed(this, e))
        }
    }
}


public class ExpectedExceptionFailed<Error>(
    public val failed: Expected.Failed<Error>,
    public val exception: Throwable
)
